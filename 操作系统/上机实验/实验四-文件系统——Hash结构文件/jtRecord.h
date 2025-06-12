
//jtRecord.h
#define RECORDLEN 32

struct jtRecord
{
	int key;
	char other[RECORDLEN - sizeof(int)];
};

#ifdef HAVE_CONFIG_H
#include<config.h>
#endif
