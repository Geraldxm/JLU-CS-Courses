
//hashfile.c
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdio.h>
#include<stdlib.h>
#include<memory.h>
#include"HashFile.h"

int hashfile_creat(const char* filename, mode_t mode, int reclen, int total_rec_num)
{
	struct HashFileHeader hfh;//用于存储文件的头部信息
	int fd;//用于存储文件描述符
	int rtn;//用于存储写入文件的返回值
	char* buf;//用于分配记录空间的缓冲区
	int i = 0;
	hfh.sig = 31415926;//设置文件的印鉴，用于校验文件的正确性
	hfh.reclen = reclen;//设置记录的长度
	hfh.total_rec_num = total_rec_num;//设置总记录数
	hfh.current_rec_num = 0;// 将当前记录数初始化为 0
	//fd = open(filename,mode);
	fd = creat(filename, mode);//调用 creat 函数创建文件，并将返回的文件描述符存储在 fd 中
	if (fd != -1)
	{//如果文件创建成功，则写入文件头部信息到文件中
		rtn = write(fd, &hfh, sizeof(struct HashFileHeader));
		//lseek(fd,sizeof(struct HashFileHeader),SEEK_SET);
		if (rtn != -1)
		{//如果头部信息写入成功，则分配记录空间的缓冲区 buf
			buf = (char*)malloc((reclen + sizeof(struct CFTag)) * total_rec_num);
			memset(buf, 0, (reclen + sizeof(struct CFTag)) * total_rec_num);//将记录空间的缓冲区初始化为零
			rtn = write(fd, buf, (reclen + sizeof(struct CFTag)) * total_rec_num);//函数将初始化的记录空间写入文件
			free(buf);//释放之前分配的内存空间
		}
		close(fd);//关闭文件
		return rtn;//返回写入文件的返回值
	}
	else {
		close(fd);
		return -1;
	}
}

int hashfile_open(const char* filename, int flags, mode_t mode)
//该函数的目的是打开一个已存在的哈希文件，并验证文件的完整性，以便后续的哈希文件操作可以在有效的文件上进行
{
	int fd = open(filename, flags, mode);//打开指定文件并将返回的文件描述符存储在 fd 中
	struct HashFileHeader hfh;
	if (read(fd, &hfh, sizeof(struct HashFileHeader)) != -1)//使用 read 函数从文件中读取头部信息，并将其存储在变量 hfh 中
	{
		lseek(fd, 0, SEEK_SET);//文件指针设置为文件开头
		if (hfh.sig == 31415926)//如果印鉴值等于预设的值（31415926），则返回文件描述符 fd，表示文件打开成功
			return fd;
		else
			return -1;
	}
	else return -1;
}

int hashfile_close(int fd)
{
	return close(fd);//调用close函数，关闭文件描述符为fd的文件
}

int hashfile_read(int fd, int keyoffset, int keylen, void* buf)
{
	struct HashFileHeader hfh;
	readHashFileHeader(fd, &hfh);//将文件头部信息读取到变量 hfh 中
	int offset = hashfile_findrec(fd, keyoffset, keylen, buf);
	//根据键值在哈希文件中查找记录的偏移量，并将结果存储在变量 offset 中
	if (offset != -1)
	{
		lseek(fd, offset + sizeof(struct CFTag), SEEK_SET);//文件指针定位到记录的位置，跳过标记信息
		return read(fd, buf, hfh.reclen);//从哈希文件中读取记录的内容，并将其存储在缓冲区 buf 中
	}
	else
	{//未找到有效记录
		return -1;
	}
}

int hashfile_write(int fd, int keyoffset, int keylen, void* buf)
{
	return hashfile_saverec(fd, keyoffset, keylen, buf);
	//return -1;
}

int hashfile_delrec(int fd, int keyoffset, int keylen, void* buf)
{
	int offset;
	offset = hashfile_findrec(fd, keyoffset, keylen, buf);//查找具有指定键值（要删除）的记录，并将记录的偏移量存储在 offset 变量中
	if (offset != -1)//找到了匹配的记录
	{
		struct CFTag tag;
		read(fd, &tag, sizeof(struct CFTag));//从文件中读取与 offset 对应的记录的标签信息，并将其存储在 tag 变量中
		tag.free = 0; //将记录的空闲标志置为 0，表示该记录现在是空闲状态 
		lseek(fd, offset, SEEK_SET);//将文件指针移动到 offset 对应的位置，即要删除的记录的位置
		write(fd, &tag, sizeof(struct CFTag));//将更新后的标签信息写回文件，表示该记录现在是空闲状态
		struct HashFileHeader hfh;
		readHashFileHeader(fd, &hfh);//读取哈希文件的头信息，并将其存储在 hfh 变量中
		int addr = hash(keyoffset, keylen, buf, hfh.total_rec_num);//计算指定键值在哈希表中的地址
		offset = sizeof(struct HashFileHeader) + addr * (hfh.reclen + sizeof(struct CFTag));//计算指定键值的记录在文件中的偏移量
		if (lseek(fd, offset, SEEK_SET) == -1)//将文件指针移动到记录的偏移量所在的位置
			return -1;
		read(fd, &tag, sizeof(struct CFTag));//读取与记录偏移量对应的标签信息，并将其存储在 tag 变量中
		tag.collision--; //冲突计数减1，表示减少了一个冲突
		lseek(fd, offset, SEEK_SET);// 将文件指针移动到记录偏移量所在的位置
		write(fd, &tag, sizeof(struct CFTag));// 将更新后的标签信息写回文件
		hfh.current_rec_num--; //当前记录数减1
		lseek(fd, 0, SEEK_SET);//将文件指针移动到哈希文件的开头
		write(fd, &hfh, sizeof(struct HashFileHeader));//将更新后的哈希文件头信息写回文件
	}
	else {
		return -1;
	}
}

int hashfile_findrec(int fd, int keyoffset, int keylen, void* buf)
{
	struct HashFileHeader hfh;
	readHashFileHeader(fd, &hfh);//读取Hash文件的头信息到hfh结构体变量
	int addr = hash(keyoffset, keylen, buf, hfh.total_rec_num);//计算给定键值在哈希表中的地址
	int offset = sizeof(struct HashFileHeader) + addr * (hfh.reclen + sizeof(struct CFTag));//计算记录在文件中的偏移量
	if (lseek(fd, offset, SEEK_SET) == -1)//将文件指针定位到记录的位置
		return -1;
	struct CFTag tag;
	read(fd, &tag, sizeof(struct CFTag));//读取记录的CFTag信息到tag结构体变量
	char count = tag.collision;//将冲突计数保存到count
	if (count == 0)//如果冲突计数为0，表示该记录不存在，函数返回-1
		return -1; //不存在
recfree://标签，用于跳转到记录释放的步骤
	if (tag.free == 0)//检查当前记录是否为空闲状态
	{//如果为空
		offset += hfh.reclen + sizeof(struct CFTag);//更新偏移量以指向下一个记录的位置
		if (lseek(fd, offset, SEEK_SET) == -1)//将文件指针定位到下一个记录的起始位置
			return -1;
		read(fd, &tag, sizeof(struct CFTag));//读取下一个记录的CFTag信息到tag结构体变量
		goto recfree;
	}
	else {//如果非空
		char* p = (char*)malloc(hfh.reclen * sizeof(char));//分配一个缓冲区p，用于存储读取的记录数据
		read(fd, p, hfh.reclen);//读取记录数据到缓冲区p
		//printf("Record is {%d , %s}\n",((struct jtRecord *)p)->key,((struct jtRecord *p)->other);
		char* p1, * p2;
		p1 = (char*)buf + keyoffset;
		p2 = p + keyoffset;
		//初始化两个指针，分别指向给定键值和读取的记录数据中对应位置的字符
		int j = 0;//初始化计数器j
		while ((*p1 == *p2) && (j < keylen))
		{//循环比较两个指针所指的字符是否相同，且未达到给定键值长度
			p1++;
			p2++;
			j++;
		}
		if (j == keylen)
		{
			free(p);//释放缓冲区p的内存
			p = NULL;
			return(offset);//并返回找到的记录的偏移量
		}
		else {
			if (addr == hash(keyoffset, keylen, p, hfh.total_rec_num))//检查当前记录的地址是否与重新计算的地址相同
			{//若相同
				count--;//冲突计数减1
				if (count == 0)//检查是否变为0
				{//若变为0，表示已找完该哈希桶内所有元素
					free(p);//释放缓冲区p的内存
					p = NULL;
					return -1;//返回-1，表示不存在，表示未找到匹配的记录
				}
			}
			//若不同
			free(p);//释放缓冲区p的内存
			p = NULL;
			offset += hfh.reclen + sizeof(struct CFTag);//更新偏移量以指向下一个记录的位置
			if (lseek(fd, offset, SEEK_SET) == -1)//将文件指针定位到下一个记录的起始位置
				return -1;
			read(fd, &tag, sizeof(struct CFTag));//读取下一个记录的CFTag信息到tag结构体变量
			goto recfree;//跳转到recfree标签处，继续进行记录释放的判断
		}
	}
}

int hashfile_saverec(int fd, int keyoffset, int keylen, void* buf)
{
	if (checkHashFileFull(fd))
	{//如果哈希文件已满，则返回 -1，表示写入操作无法执行
		return -1;
	}
	struct HashFileHeader hfh;
	readHashFileHeader(fd, &hfh);//从文件中读取头部信息，并将其存储在 hfh 变量中
	int addr = hash(keyoffset, keylen, buf, hfh.total_rec_num);
	//调用 hash 函数，根据键值的偏移量、长度和总记录数计算哈希地址
	int offset = sizeof(struct HashFileHeader) + addr * (hfh.reclen + sizeof(struct CFTag));
	//根据哈希地址计算记录在文件中的偏移量
	if (lseek(fd, offset, SEEK_SET) == -1)//将文件指针移动到记录的偏移量处
		return -1;
	struct CFTag tag;
	read(fd, &tag, sizeof(struct CFTag));//从文件中读取当前记录的冲突标记
	tag.collision++;//将冲突计数加1，表示发生了冲突
	lseek(fd, sizeof(struct CFTag) * (-1), SEEK_CUR);//将文件指针回溯到冲突标记的位置
	write(fd, &tag, sizeof(struct CFTag));//更新冲突计数
	while (tag.free != 0) 
	{//使用顺序探查的方式处理冲突，即逐个检查下一个记录，直到找到空闲的记录位置
		offset += hfh.reclen + sizeof(struct CFTag);//将偏移量增加一个记录的长度和一个冲突标记的长度
		if (offset >= lseek(fd, 0, SEEK_END))//检查是否超过了文件末尾
			offset = sizeof(struct HashFileHeader); //如果超过了文件末尾，则将偏移量回溯到文件的起始位置
		if (lseek(fd, offset, SEEK_SET) == -1)//更新文件指针位置
			return -1;
		read(fd, &tag, sizeof(struct CFTag));//重新读取新位置的冲突标志
	}
	//标记找到的空闲记录
	tag.free = -1;//将找到的空闲记录的标志设置为 -1，表示已被占用
	lseek(fd, sizeof(struct CFTag) * (-1), SEEK_CUR);//回溯到冲突标记的位置
	write(fd, &tag, sizeof(struct CFTag));//更新记录的冲突标记
	write(fd, buf, hfh.reclen);//将记录内容写入文件
	hfh.current_rec_num++;//将当前记录数加1，表示写入了一个新记录
	lseek(fd, 0, SEEK_SET);//将文件指针移动到文件的起始位置
	return write(fd, &hfh, sizeof(struct HashFileHeader));  //将更新后的文件头部信息写入文件，并返回写入结果 
}

int hash(int keyoffset, int keylen, void* buf, int total_rec_num)
{
	int i = 0;
	char* p = (char*)buf + keyoffset;
	//声明一个字符型指针 p，它指向记录缓冲区中关键字的起始位置。
	//通过将 buf 转换为字符型指针，然后加上 keyoffset 的偏移量，得到了指向关键字的指针
	int addr = 0;//用于存储计算得到的哈希地址
	for (i = 0; i < keylen; i++)//将关键字的每个字符的整数值相加
	{
		addr += (int)(*p);
		p++;
	}
	return addr % (int)(total_rec_num * COLLISIONFACTOR);
	//计算得到的哈希地址 addr 除以总记录数乘以冲突因子 (COLLISIONFACTOR)，然后取余数，得到最终的哈希地址
}

int checkHashFileFull(int fd)
{
	struct HashFileHeader hfh;
	readHashFileHeader(fd, &hfh);//将文件描述符 fd 所指向的哈希文件的头部信息读取到 hfh 变量中
	if (hfh.current_rec_num < hfh.total_rec_num)
		return 0;// 如果哈希文件未满，返回 0，表示哈希文件未满
	else
		return 1;//如果哈希文件已满，返回 1，表示哈希文件已满
}

int readHashFileHeader(int fd, struct HashFileHeader* hfh)
{
	lseek(fd, 0, SEEK_SET);//函数将文件指针定位到文件的开头，确保从文件的开头开始读取头部信息
	return read(fd, hfh, sizeof(struct HashFileHeader));
	//字节的数据，并将读取的数据存储到 hfh 指向的结构体变量中。函数的返回值是实际读取的字节数
}
