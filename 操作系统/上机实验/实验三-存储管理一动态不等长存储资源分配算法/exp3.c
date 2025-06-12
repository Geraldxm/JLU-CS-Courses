#include <stdio.h>
#include <stdlib.h>

#define MAPSIZE 100

struct map {
    int m_addr; //首地址
    int m_size; //剩余大小
};

struct map map[MAPSIZE];

// BF存储分配函数
int BF_malloc(struct map *mp, int size) {
    register int a, s;
    register struct map *bp, *bpp;

    for (bp = mp; bp->m_size; bp++) {
        //中间的bp->m_size条件，即当未到表尾m_size==0
        if (bp->m_size >= size) {
            a = bp->m_addr;
            s = bp->m_size;

            for (bpp = bp; bpp->m_size; bpp++) {
                // 最佳适应

                //从bp开始扫描到表尾，当bpp能放下size，且比bp小，
                //则将bp更新为bpp
                if (bpp->m_size >= size && bpp->m_size < s) {
                    a = bpp->m_addr;
                    s = bpp->m_size;
                    bp = bpp;
                }
            }

            bp->m_addr += size; //更新首地址

            if ((bp->m_size -= size) == 0) 
            //如果分配完后，长度为0，即全部被分配
            //那么把该数组全部往前移一位
            {
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
int WF_malloc(struct map *mp, int size) {
    register int a, s;
    register struct map *bp, *bpp;

    for (bp = mp; bp->m_size; bp++) {
        if (bp->m_size >= size) {
            a = bp->m_addr;
            s = bp->m_size;

            //与上面类似，但是最坏适应
            for (bpp = bp; bpp->m_size; bpp++) {
                // 最坏适应
                if (bpp->m_size > s) {
                    a = bpp->m_addr;
                    s = bpp->m_size;
                    bp = bpp;
                }
            }

            bp->m_addr += size;

            //空，同样前移
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

void mfree(struct map *mp, int aa, int size) {
    register struct map *bp;
    register int t;
    register int a;

    a = aa;

    for (bp = mp; bp->m_addr < a && bp->m_size != 0; bp++);
    //循环，找到第一个首地址大于等于a的

    //判断是否存在前一块与释放的存储块相邻且可以合并。
    //如果前一块存在且其首地址加上大小等于要释放的存储块的首地址，
    //说明可以与前一块进行合并。执行以下操作：
        //将前一块的大小增加释放块的大小。
        //如果要释放的存储块的末尾与后一块的首地址相邻，
        //说明也可以与后一块进行合并。
        //继续循环合并，直到无法合并为止。
    //合并的过程是将后续的存储块向前移动，覆盖合并的块。

    if (bp > mp && (bp - 1)->m_addr + (bp - 1)->m_size == a) 
    //若前一块存在且其首地址加上大小等于要释放的存储块的首地址
    {
        // 与前合并
        //前一块的大小加上
        (bp - 1)->m_size += size;
        if (a + size == bp->m_addr) 
        {
            //如果要释放的存储块的末尾与后一块的首地址相邻
            // 前后合并
            (bp - 1)->m_size += bp->m_size;
            //前移一位
            while (bp->m_size) {
                bp++;
                (bp - 1)->m_addr = bp->m_addr;
                (bp - 1)->m_size = bp->m_size;
            }
        }
    } else {
        if (a + size == bp->m_addr && bp->m_size) {
        //如果无法与前一块合并，则判断是否可以与后一块合并。
        //如果要释放的存储块的末尾与后一块的首地址相邻且后一块不为空，
        //则可以与后一块合并。执行以下操作：
            //将后一块的首地址减少释放块的大小。
            //将后一块的大小增加释放块的大小。
            
            // 与后合并
            bp->m_addr -= size;
            bp->m_size += size;
        } else if (size) {
            // 无合并
            do {
                //将释放块插入数组中，其它元素后移一位
                t = bp->m_addr;
                bp->m_addr = a;
                a = t;
                t = bp->m_size;
                bp->m_size = size;
                bp++;
            } while (size = t);
        }
    }
}

void init() {
    struct map *bp;
    int addr, size;
    int i = 0;
    bp = map;
    printf("Please input starting addr and total size:");
    scanf("%d,%d", &addr, &size);
    //bp指向map数组的第一个map对象
    //赋值第一个map对象的首地址和长度
    bp->m_addr = addr;
    bp->m_size = size;
    //第二个map对象的长度设为0，表示到达表尾，初始化完毕
    (++bp)->m_size = 0; //表尾
}

void show_map() {
    int i = 0;
    struct map *bp;
    bp = map;
    printf("\nCurrent memory map...\n");
    printf("Address\tSize\n");
    //当map对象的长度不为0时，也就是没到表尾时
    while (bp->m_size != 0) {
        printf("<%d\t%d>\n", bp->m_addr, bp->m_size);
        bp++;
    }
    printf("\n");
}

int main(int argc, char const *argv[]) {
    int a, s;
    char ch;
    int c;
    int i;

    init();

    printf("Please input, b for BF, w for WF:");
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
                int bb=(int)'b';
                int ww=(int)'w';
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
