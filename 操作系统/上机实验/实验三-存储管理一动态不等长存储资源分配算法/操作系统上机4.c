#ifdef HAVE_CONFIG_H
#include<config.h>
#endif
#include <stdio.h>
#include <stdlib.h>

#define MAPSIZE 100

struct map {
	int m_addr; //首地址
	int m_size; //剩余大小
};

struct map map[MAPSIZE];


struct map* pp = map;//循环查找指针，用于NF算法





// FF存储分配函数
int FF_malloc(struct map* mp, int size) {
	register int a, s;
	register struct map* bp, * bpp;

	for (bp = mp; bp->m_size; bp++) {
		if (bp->m_size >= size) {
			a = bp->m_addr;
			s = bp->m_size;

			//选出第一个，最先适应

			bp->m_addr += size;

			if ((bp->m_size -= size) == 0) {
				do {
					bp++;
					(bp - 1)->m_addr = bp->m_addr;
				} while ((bp - 1)->m_size = bp->m_size);
			}

			return a;
		}
	}

	return -1;
}

// NF存储分配函数
int NF_malloc(struct map* mp, int size) {
	register int a, s;
	register struct map* bp, * bpp;

	for (bp = pp; bp->m_size; bp++) {//从pp开始选出第一个
		if (bp->m_size >= size) {
			a = bp->m_addr;
			s = bp->m_size;

			bp->m_addr += size;

			if ((bp->m_size -= size) == 0) {
				do {
					bp++;
					(bp - 1)->m_addr = bp->m_addr;
				} while ((bp - 1)->m_size = bp->m_size);
			}

			return a;
		}
	}

	return -1;
}

// BF存储分配函数
int BF_malloc(struct map* mp, int size) {
	register int a, s;
	register struct map* bp, * bpp;

	for (bp = mp; bp->m_size; bp++) {
		if (bp->m_size >= size) {
			a = bp->m_addr;
			s = bp->m_size;

			for (bpp = bp; bpp->m_size; bpp++) {
				// 最佳适应
				if (bpp->m_size >= size && bpp->m_size < s) {
					a = bpp->m_addr;
					s = bpp->m_size;
					bp = bpp;
				}
			}

			bp->m_addr += size;

			if ((bp->m_size -= size) == 0) {
				do {
					bp++;
					(bp - 1)->m_addr = bp->m_addr;
				} while ((bp - 1)->m_size = bp->m_size);
			}

			return a;
		}
	}

	return -1;
}

// WF存储分配函数
int WF_malloc(struct map* mp, int size) {
	register int a, s;
	register struct map* bp, * bpp;

	for (bp = mp; bp->m_size; bp++) {
		if (bp->m_size >= size) {
			a = bp->m_addr;
			s = bp->m_size;

			for (bpp = bp; bpp->m_size; bpp++) {
				// 最坏适应
				if (bpp->m_size > s) {
					a = bpp->m_addr;
					s = bpp->m_size;
					bp = bpp;
				}
			}

			bp->m_addr += size;

			if ((bp->m_size -= size) == 0) {
				do {
					bp++;
					(bp - 1)->m_addr = bp->m_addr;
				} while ((bp - 1)->m_size = bp->m_size);
			}

			return a;
		}
	}

	return -1;
}

void mfree(struct map* mp, int aa, int size) {//改进，防止重复释放
	register struct map* bp;
	register int t;
	register int a;

	a = aa;

	int sizeleft=size;
	printf("%d ", sizeleft);

	for (bp = mp; bp->m_size != 0; bp++) {
		if (bp->m_addr <= a && bp->m_addr + bp->m_size > a) {//如果有前面重复部分则a起始地址向后移
			//size = bp->m_addr + bp->m_size - a;
			printf("%d ", size);
			a = bp->m_addr + bp->m_size;
			printf("%d\n", a);
			//printf("Cannot free!");
			//return -1;
		}
		if (a<bp->m_addr && a + size>bp->m_addr+ bp->m_size) {//如果中间有重复的就缩小释放空间
			size = bp->m_addr - a;
			//printf("Cannot free!");
			//return -1;
		}
		if (a<bp->m_addr && a + size>bp->m_addr) {//如果后面有重复的就缩小释放空间
			size = bp->m_addr - a;
			//printf("Cannot free!");
			//return -1;
		}
	}
	//每次只会选出最前面的一整片空间释放
	sizeleft -= size;//更新剩余的未分配空间量

	for (bp = mp; bp->m_addr < a && bp->m_size != 0; bp++);

	if (bp > mp && (bp - 1)->m_addr + (bp - 1)->m_size == a) {
		// 与前合并
		(bp - 1)->m_size += size;

		if (a + size == bp->m_addr) {
			// 前后合并
			(bp - 1)->m_size += bp->m_size;
			while (bp->m_size) {
				bp++;
				(bp - 1)->m_addr = bp->m_addr;
				(bp - 1)->m_size = bp->m_size;
			}
		}
	}
	else {
		if (a + size == bp->m_addr && bp->m_size) {
			// 与后合并
			bp->m_addr -= size;
			bp->m_size += size;
		}
		else if (size) {
			// 无合并
			do {
				t = bp->m_addr;
				bp->m_addr = a;
				a = t;
				t = bp->m_size;
				bp->m_size = size;
				bp++;
			} while (size = t);
		}
	}

	if (sizeleft <= 0) {
		return;
	}
	else {
		mfree(map, a, sizeleft);//递归释放下一个整片空间，直到达到需要释放的大小为止
	}
}

void mfreeB(struct map* mp, int aa, int size) {//修改mfree，实现从大到小排序
	register struct map* bp, * bpp;
	register int t;
	register int a;

	a = aa;

	for (bp = mp; bp->m_addr < a && bp->m_size != 0; bp++) {
		if (bp > mp && (bp - 1)->m_addr + (bp - 1)->m_size == a) {
			// 与前合并
			(bp - 1)->m_size += size;

			a = (bp - 1)->m_addr;
			size = (bp - 1)->m_size;

			bpp = bp - 1;
			while ((bpp + 1)->m_size) {//后面的往前窜一个，填补空隙，相当于将空间合并后拿出来
				bpp->m_size = (bpp + 1)->m_size;
				bpp->m_addr = (bpp + 1)->m_addr;
			}
			bpp->m_size = (bpp + 1)->m_size;
			bpp->m_addr = (bpp + 1)->m_addr;//多执行一次，把空的也往前提一个，确保最后一个的后面是空，不重复
		}

		if (a + size == bp->m_addr && bp->m_size) {
			// 与后合并
			bp->m_addr -= size;
			bp->m_size += size;

			a = bp->m_addr;
			size = bp->m_size;

			bpp = bp;
			while ((bpp + 1)->m_size) {//后面的往前窜一个，填补空隙，相当于将空间合并后拿出来
				bpp->m_size = (bpp + 1)->m_size;
				bpp->m_addr = (bpp + 1)->m_addr;
			}
			bpp->m_size = (bpp + 1)->m_size;
			bpp->m_addr = (bpp + 1)->m_addr;//多执行一次，把空的也往前提一个，确保最后一个的后面是空，不重复
		}
	}

	for (bp = mp; bp->m_size >= size; bp++);//在递减序列中找到第一个比size小的
	do {//插入，并且所有的空间向后窜一个位置
		t = bp->m_addr;
		bp->m_addr = a;
		a = t;
		t = bp->m_size;
		bp->m_size = size;
		bp++;
	} while (size = t);
}


void init() {
	struct map* bp;
	int addr, size;
	int i = 0;
	bp = map;
	printf("Please input starting addr and total size:");
	scanf("%d,%d", &addr, &size);
	bp->m_addr = addr;
	bp->m_size = size;
	(++bp)->m_size = 0; //表尾
}

void show_map() {
	int i = 0;
	struct map* bp;
	bp = map;
	printf("\nCurrent memory map...\n");
	printf("Address\tSize\n");
	while (bp->m_size != 0) {
		printf("<%d\t%d>\n", bp->m_addr, bp->m_size);
		bp++;
	}
	printf("\n");
}

int main(int argc, char const* argv[]) {
	int a, s;
	char ch;
	int c;
	int i;

	init();

	printf("Please input, b for BF, w for WF, f for FF, n for NF:");
	//在linux系统中，需要先getchar()一次
	getchar();
	ch = getchar();
	c = ch;
	//printf("c=%d c=%c",c,c);
	do {


		printf("Please input, 1 for request, 2 for release, 0 for exit:");
		scanf("%d", &i);

		switch (i) {
		case 1:
			printf("Please input size:");
			scanf("%d", &s);
			int bb = (int)'b';
			int ww = (int)'w';
			int FF = (int)'f';
			int NN = (int)'n';
			//printf("bb=%d ww=%d",bb,ww);
			if (c == bb)
			{
				printf("BF_malloc\n");
				a = BF_malloc(map, s);
			}
			else if (c == ww)
			{
				printf("WF_malloc\n");
				a = WF_malloc(map, s);
			}
			else if (c == FF)
			{
				printf("FF_malloc\n");
				a = WF_malloc(map, s);
			}
			else if (c == NN)
			{
				printf("NF_malloc\n");
				a = WF_malloc(map, s);
			}
			if (a == -1)
				printf("request cannot be satisfied\n");
			else
				printf("alloc memory at addr:%d,size:%d\n", a, s);

			break;

		case 2:
			printf("Please input addr and size:");
			scanf("%d,%d", &a, &s);
			mfree(map, a, s);
			break;

		case 0:
			exit(0);
		}

		show_map(); //显示存储资源表
	} while (1);

	return 0;
}
