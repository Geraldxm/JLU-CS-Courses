/***** 该代码文件所包含头文件 *****/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
/* 宏定义常量TRUE为1 */
#ifndef TRUE
#define TRUE 1
#endif
/* 宏定义常量FALSE为0 */
#ifndef FALSE
#define FALSE 0
#endif


/***************** 常量 *******************/

/* 为大型程序扩展,指令存储区大小,定义为1024 */
#define   IADDR_SIZE  1024 
/* 为大型程序扩展,数据存储区大小,定义为1024 */
#define   DADDR_SIZE  1024 
/* 寄存器数量,定义为8 */
#define   NO_REGS 8
/* PC寄存器,定义为7 */
#define   PC_REG  7
/* 目标代码行大小,定义为121 */
#define   LINESIZE  121
/* 字大小,定义为20 */
#define   WORDSIZE  20


/**************** 类型 *******************/

/* 指令寻址模式类型 */
typedef enum 
{
   opclRR,		/* 寄存器寻址模式类型,操作数使用寄存器r,s,t */
   opclRM,		/* 寄存器-内存寻址模式类型,操作数使用寄存器r,内存地址d+s */
   opclRA		/* 寄存器-立即数寻址模式类型,操作数使用寄存器r,立即数值d+s */
} OPCLASS;

/* 操作指令标识码类型 */
typedef enum opcode{

   /************* 寄存器寻址模式指令标识码 ***************/

   opHALT,	/* 停止指令:结束程序执行,忽略操作数 */
   
   opIN,	/* 输入指令:将外部变量读入寄存器,使用第r个寄存器,忽略s,t参数 */

   opOUT,	/* 输出指令:将寄存器的值输出,使用第r个寄存器,忽略s,t参数 */

   opADD,   /* 加法指令:寄存器r的值赋为寄存器s的值与寄存器t的值的和 */

   opSUB,   /* 减法指令:寄存器r的值赋为寄存器s的值与寄存器t的值的差 */

   opMUL,   /* 乘法指令:寄存器r的值赋为寄存器s的值与寄存器t的值的积 */

   opDIV,   /* 除法指令:寄存器r的值赋为寄存器s的值与寄存器t的值的商 */

   /* 寄存器寻址模式指令限制标志,							*
    * 操作标识码枚举值小于opRRLim的指令均为寄存器寻址模式指令	*/
   opRRLim,  

   /************** 寄存器-内存寻址模式指令标识码 ****************/

   opLD,	/* 载入指令:寄存器r的值赋为地址为d+reg(s)的内存单元的值 */

   opST,    /* 设置指令:将地址为d+reg(s)的内存单元值赋为寄存器r的值 */

   /* 寄存器-内存寻址模式指令限制标志										*
    * 操作标识码枚举值小于opRMLim且大于opRRLim的均为寄存器-内存寻址模式指令 */
   opRMLim, 

  
   /************* 寄存器-立即数寻址模式指令标识码 ****************/

   opLDA,	/* 载入指令:将寄存器r的值赋为立即数d与寄存器s的值的和 */

   opLDC,   /* 载入指令:将寄存器r的值赋为立即数d,参数s被忽略 */

   opJLT,   /* 如果寄存器r的值小于0,将第7个寄存器的值赋为d+reg(s)	*
			 * 第7个寄存器为pc程序计数寄存器						*/

   opJLE,   /* 如果寄存器r的值小于等于0,将pc寄存器的值赋为d+reg(s) */

   opJGT,   /* 如果寄存器r的值大于0,将pc寄存器的值赋为d+reg(s) */

   opJGE,   /* 如果寄存器r的值大于等于0,将pc寄存器的值赋为d+reg(s) */

   opJEQ,   /* 如果寄存器r的值等于0,将pc寄存器的值赋为d+reg(s) */

   opJNE,   /* 如果寄存器r的值不等于0,将pc寄存器的值赋为d+reg(s) */

   /* 寄存器-立即数寻址模式指令限制标志
    * 操作标识码枚举值小于opRALim且大于opRMLim的均为寄存器-立即数寻址模式指令 */
   opRALim    

   } OPCODE;						

/************ 指令单步执行结果类型 ************/
typedef enum {
   srOKAY,			/* 正常 */

   srHALT,			/* 停止 */

   srIMEM_ERR,		/* 指令存储错 */

   srDMEM_ERR,		/* 数据存储错 */

   srZERODIVIDE		/* 除数为零错 */

   } STEPRESULT;

/* 指令结构类型:操作码,操作数1,操作数2,操作数3 */
typedef struct {
      int iop  ;
      int iarg1  ;
      int iarg2  ;
      int iarg3  ;
   } INSTRUCTION;


/******** 变量 ********/

int iloc = 0 ;			/* 指令存储计数指针,初始为0 */

int dloc = 0 ;			/* 数据存储计数指针,初始为0 */

int traceflag = FALSE;	/* 指令执行追踪标志,初始为FALSE */

int icountflag = FALSE;	/* 指令执行计数标志,初始为FALSE */

/* iMem用于指令存储,为1024长的指令结构数组 */
INSTRUCTION iMem [IADDR_SIZE];				

/* dMem用于数据存储,为1024长的整数类型数组 */
int dMem [DADDR_SIZE];						

/* reg用于寄存器存储,为8长的整数类型数组 */
int reg [NO_REGS];							


/* 指令操作码表,对应寻址模式分为三类 */
char * opCodeTab[]

        = {"HALT","IN","OUT","ADD","SUB","MUL","DIV","????",
          /* 寄存器寻址模式指令类型 */

           "LD","ST","????", 
		  /* 寄存器-内存寻址模式指令类型 */

           "LDA","LDC","JLT","JLE","JGT","JGE","JEQ","JNE","????"
          /* 寄存器-立即数寻址模式指令类型 */
          };

/** 单步执行结果状态表 **/
char * stepResultTab[]

        = {"OK","Halted","Instruction Memory Fault",
           "Data Memory Fault","Division by 0"
          };

char pgmName[20];			/* 用于存储程序文件名 */

FILE *pgm  ;				/* 程序文件指针 */

char in_Line[LINESIZE] ;	/* 用于存储一行代码,为121长的字符数组 */

int lineLen ;				/* in_Line中行结尾字符位置 */

int inCol  ;				/* 用于指出在in_Line中的当前字符位置 */

int num  ;					/* 用于存储当前所得数值 */

char word[WORDSIZE] ;		/* 用于存储当前的字,为20长的字符数组 */

char ch  ;					/* 当前代码行中当前位置上的字符 */

int done  ;



/****************************************************/
/* 函数名 opClass									*/
/* 功  能 指令寻址模式分类函数						*/
/* 说  明 该函数对给定的指令操作码枚举值c进行分类	*/
/*        返回指令所属寻址模式						*/
/****************************************************/
int opClass( int c )

{ 
  /* 如果枚举值c小于opRRLim,则指令为寄存器寻址模式指令类型 */
  if      ( c <= opRRLim) return ( opclRR );

  /* 如果枚举值c小于opRMLim,则指令为寄存器-内存寻址模式指令类型 */
  else if ( c <= opRMLim) return ( opclRM );

  /* 为寄存器-立即数寻址模式指令类型 */
  else                    return ( opclRA );

} 

/********************************************************/
/* 函数名 writeInstruction								*/
/* 功  能 指令输出函数									*/
/* 说  明 该函数将指令存储区中指令以指定格式输出到屏幕	*/
/********************************************************/
void writeInstruction ( int loc )

{  
  /* loc为所要输出的指令在指令存储区中地址,输出到屏幕 */
  printf( "%5d: ", loc) ;

  /* 输出指令地址loc在0-1023有效的指令存储区地址范围之内 */
  if ( (loc >= 0) && (loc < IADDR_SIZE) )

  { 
    /* 输出地址为loc上的指令操作码值iMem[loc].iop和第一操作数iMem[loc].iarg1 */
	printf("%6s%3d,", opCodeTab[iMem[loc].iop], iMem[loc].iarg1);

	/* 根据指令的寻址模式分类处理 */
    switch ( opClass(iMem[loc].iop) )

    { 
      /* 输出指令为寄存器寻址模式指令,以给定形式输出操作数2,操作数3 */
	  case opclRR: printf("%1d,%1d", iMem[loc].iarg2, iMem[loc].iarg3);
                   break;		

      /* 输出指令为寄存器-立即数寻址模式指令,和寄存器-内存寻址模式指令	*
	   * 以给定形式输出操作数2,操作数3									*/
      case opclRM: 
      case opclRA: printf("%3d(%1d)", iMem[loc].iarg2, iMem[loc].iarg3);
                   break;	
    }
   	/* 向屏幕输出换行符 */
	printf ("\n") ;
  }
} /* writeInstruction */

/****************************************************/
/* 函数名 getCh										*/
/* 功  能 字符获取函数								*/
/* 说  明 如果当前行中字符未读完,则函数返回当前字符	*/
/*		  否则,函数返回空格字符						*/
/****************************************************/
void getCh (void)

{ 
  /* 在当前代码行in_Line中,当前字符列数inCol未超过代码行实际长度lineLen *
   * 取得当前行中当前位置的字符,送入ch									*/
  if (++inCol < lineLen)
  ch = in_Line[inCol] ;

  /* 如果inCol超出当前代码行长度范围,则ch赋为空格 */
  else ch = ' ' ;

} /* getCh */



/********************************************************/
/* 函数名 nonBlank										*/
/* 功  能 非空字符获取函数								*/
/* 说  明 如果成功从当前行中取得非空字符,函数返回TRUE	*/
/*		  否则,函数返回FALSE							*/
/********************************************************/
int nonBlank (void)

{ 
  /* 在当前代码行in_Line中,当前字符位置inCol中为空格字符	*  
   * 在当前代码行in_Line中,当前字符位置inCol下移,略过空格	*/
  while ((inCol < lineLen)
         && (in_Line[inCol] == ' ') )
		 inCol++ ;

  /* 在当前代码行in_Line中,遇到非空字符 */
  if (inCol < lineLen)

  { 
    /* 取当前字符位置inCol中的字符送入ch,		*
	 * 函数返回TRUE(已定义为1),ch中得到非空字符	*/
	ch = in_Line[inCol] ;
    return TRUE ; }

  /* 当前代码行已经读完,将当前字符ch 赋为空格,	*
   * 函数返回FALSE(已定义为0),ch中为空格字符	*/
  else
  { ch = ' ' ;
    return FALSE ; }
} /* nonBlank */


/****************************************************************/
/* 函数名 getNum												*/
/* 功  能 数值获取函数											*/
/* 说  明 将代码行中连续出现的有加减运算的数term合并计数,		*/
/*        所的数值送入为num.如果成功得到数值,则函数返回TRUE;	*/
/*        否则,函数返回FALSE									*/
/****************************************************************/
int getNum (void)

{ int sign;				/* 符号因子 */

  int term;				/* 用于记录当前录入的局部数值 */

  int temp = FALSE;		/* 记录函数返回值,初始为假 */

  num = 0 ;				/* 用于记录所有加减运算后的最终数值结果 */

  do
  { sign = 1;			/* 符号因子初始为1 */

    /* 调用函数nonBlank()略过当前位置的空格后,			*
     * 所得到的当前非空字符ch为+或-.(+/-的连续出现处理)	*/
    while ( nonBlank() && ((ch == '+') || (ch == '-')) )

    { temp = FALSE ;

	  /* 当前字符ch为"-"时,符号因子sign设为-1 */
	  if (ch == '-')  sign = - sign ;

	  /* 取当前代码行中下一字符到当前字符ch中 */
      getCh();
    }

    term = 0 ;		/* 当前录入的局部数值初始为0 */

    nonBlank();		/* 略过当前位置上的空格 */

	/* 当前字符ch为数字,局部数值的循环处理 */
    while (isdigit(ch))				

    { temp = TRUE ;		/* 函数返回值设为TRUE,成功得到数字 */

	  /* 将字符序列转化为数值形式,进行进位累加 */
      term = term * 10 + ( ch - '0' ) ;

      getCh();			/* 取当前代码行中下一字符到当前字符ch中 */

    }
	/* 将局部数值带符号累加,得到最终数值num */
    num = num + (term * sign) ;

  } while ( (nonBlank()) && ((ch == '+') || (ch == '-')) ) ;
  return temp;
} /* getNum */


/****************************************************/
/* 函数名 getWord									*/
/* 功  能 单词获取函数								*/
/* 说  明 函数从当前代码行中获取单词.如果得到字符,	*/
/*		  则函数返回TRUE;否则,函数返回FALSE			*/
/****************************************************/
int getWord (void)

{ 
	
  int temp = FALSE;			/* 函数返回值初始为FALSE */

  int length = 0;			/* 单词长度初始为0 */

  /* 在当前代码行中成功获取非空字符ch */
  if (nonBlank ())

  {
    /* 当前非空字符ch为字母或数字 */
	while (isalnum(ch))

    {
      /* 当前单词word未超过规定字长WORDSIZE-1(为单词结束字符留一空位)	*
	   * 将当前字符ch读入到单词末尾										*/
	  if (length < WORDSIZE-1) word [length++] =  ch ;

      getCh() ;			/* 取当前代码行中下一字符 */
    }

	/* 给当前单词word加入结束字符 */
    word[length] = '\0';

	/* 设置函数返回值,当读入字word非空的时候为TRUE */
    temp = (length != 0);

  }
  return temp;
} /* getWord */


/************************************************************/
/* 函数名 skipCh											*/
/* 功  能 字符空过函数										*/
/* 说  明 如果当前位置上字符为函数指定的字符,则空过该字符,	*/
/*		  函数返回TRUE;否则函数返回FALSE					*/
/************************************************************/
int skipCh ( char c  )

{ int temp = FALSE;

  /* 当前位置上字符为函数指定字符c */
  if ( nonBlank() && (ch == c) )

  { getCh();		/* 空过当前字符c,取下一字符 */

    temp = TRUE;	/* 空过指定字符c,函数返回TRUE */
  }
  return temp;
} /* skipCh */


/************************************/
/* 函数名 atEOL						*/
/* 功  能 行结束判断函数			*/
/* 说  明 当前行是否结束的判断函数	*/
/************************************/	
int atEOL(void)

{ return ( ! nonBlank ());	/* 如果当前行中没有非空字符,则函数返回TRUE */
} /* atEOL */


/****************************************************/
/* 函数名 error										*/
/* 功  能 错误处理函数								*/
/* 说  明 函数输出错误行号,指令地址标号和错误信息	*/
/****************************************************/
int error( char * msg, int lineNo, int instNo)

{ 
  /* 输出错误出现位置行号lineNo */
  printf("Line %d",lineNo);

  /* 输出错误指令地址标号instNo */
  if (instNo >= 0) printf(" (Instruction %d)",instNo);

  /* 输出错误信息msg */
  printf("   %s\n",msg);

  return FALSE;
} /* error */


/********************************************************/
/* 函数名 readInstructions								*/
/* 功  能 指令文件读入函数								*/
/* 说  明 将指令文件中的指令逐条读入到指令存储区iMem	*/
/********************************************************/
int readInstructions (void)

{ 
  OPCODE op;				/* 当前指令操作码 */

  int arg1, arg2, arg3;		/* 当前指令操作数 */

  int loc, regNo, lineNo;

  /* 将8个寄存器内容初始化为0 */
  for (regNo = 0 ; regNo < NO_REGS ; regNo++)
      reg[regNo] = 0 ;						

  /* dMem为数据存储区,0地址单元dMem[0]的值赋为数据存储区高端地址1023	*
   * 此数值将在目标程序运行时由程序的先驱指令读入到mp寄存器中			*/
  dMem[0] = DADDR_SIZE - 1 ;				

  /* 将数据存储数区内除0地址单元外的各单元初始化为0 */
  for (loc = 1 ; loc < DADDR_SIZE ; loc++)
      dMem[loc] = 0 ;

  /* 将指令存储区中各单元初始化为指令;HALT 0,0,0 */
  for (loc = 0 ; loc < IADDR_SIZE ; loc++)
  { iMem[loc].iop = opHALT ;
    iMem[loc].iarg1 = 0 ;
    iMem[loc].iarg2 = 0 ;
    iMem[loc].iarg3 = 0 ;
  }

  lineNo = 0 ;		/* lineNo用于记录当前代码指令行号 */

  /* 不是指定文件pgm的文件结尾 */
  while (! feof(pgm))				

  { 
    /* 从指定文件pgm中读入119个字符到当前代码行in_Line */
	fgets( in_Line, LINESIZE-2, pgm  ) ;

    inCol = 0 ;		/* 当前代码行in_Line中当前字符位置inCol初始为0 */

    lineNo++;		/* 当前代码行行号加1 */

	/* 当前代码行in_Line行结尾字符位置赋给lineLen */
    lineLen = strlen(in_Line)-1 ;

	/* 如果源行结束字符是'\n',则更换行结束字符为'\0' */
    if (in_Line[lineLen]=='\n') in_Line[lineLen] = '\0' ;

	/* 源行没有行结束字符,加上结束字符'\0' */
    else in_Line[++lineLen] = '\0';

	/* 当前字符不是"*",即不是注释语句,应该是指令语句 */
    if ( (nonBlank()) && (in_Line[inCol] != '*') )

    {
	  /* 当前字符不是数字,报地址错,并给出行号lineNo */
	  if (! getNum())

        return error("Bad location", lineNo,-1);

	  /* 将所得数值赋给当前代码地址标号loc */
	  loc = num;

	  /* 代码地址标号loc超出指令存储区地址IADDR_SIZE,报错 */
      if (loc > IADDR_SIZE)
        return error("Location too large",lineNo,loc);

	  /* 代码地址标号loc后面缺少冒号,报缺少冒号错 */
      if (! skipCh(':'))
        return error("Missing colon", lineNo,loc);
 
	  /* 当前位置不是单词,报缺少指令操作码错 */
      if (! getWord ())
        return error("Missing opcode", lineNo,loc);

	  /* 初始查表指针op,op指向操作码表表首,值为opHALT */
      op = opHALT ;

	  /* 查操作码表opCodeTab,只比较当前字word中的前四个字符	*
	   * 因为所有定义的操作码中,最长操作码HALT长度只为四	*/
      while ((op < opRALim)
             && (strncmp(opCodeTab[op], word, 4) != 0) )
          op = (enum opcode)(op+1);

	  /* 当前单词word中指定的操作码不在操作码表opCodeTab中,报非法操作码错误 */
      if (strncmp(opCodeTab[op], word, 4) != 0)
          return error("Illegal opcode", lineNo,loc);

	  /* 对查表得到的操作码值op的寻址模式,进行分类处理 */
      switch ( opClass(op) )

      { case opclRR :			/* 寄存器寻址模式操作码 */

	    /* 第一寄存器操作数错,非0-7之间数字,		*
         * 输出错误信息,行号lineNo,代码地址标号loc	*/
        if ( (! getNum ()) || (num < 0) || (num >= NO_REGS) )
            return error("Bad first register", lineNo,loc);

		/* 将第一操作数arg1赋值为当前数值num */
        arg1 = num;

		/* 第一操作数后漏掉","分隔符,报错 */
        if ( ! skipCh(','))
            return error("Missing comma", lineNo, loc);

		/* 第二寄存器操作数错,非0-7之间数字,		*
		 * 输出错误信息,行号lineNo,代码地址标号loc	*/
        if ( (! getNum ()) || (num < 0) || (num >= NO_REGS) )
            return error("Bad second register", lineNo, loc);

		/* 将第二个操作数arg2赋值为当前数值num */
		arg2 = num;

		/* 第二操作数后漏掉","分隔符,报错 */
        if ( ! skipCh(',')) 
            return error("Missing comma", lineNo,loc);

		/* 第三寄存器操作数错,非0-7之间数字,报错 */
        if ( (! getNum ()) || (num < 0) || (num >= NO_REGS) )
            return error("Bad third register", lineNo,loc);

		/* 将第三操作数arg3赋值为当前数值num */
        arg3 = num;
        break;


		/* 寄存器-内存寻址模式		*
		 * 寄存器-立即数寻址模式	*/
        case opclRM :						
        case opclRA :

		/* 第一寄存器操作数错,非0-7之间数字,报错 */
        if ( (! getNum ()) || (num < 0) || (num >= NO_REGS) )
            return error("Bad first register", lineNo,loc);

 		/* 将第一操作数arg1赋值为当前数值num */
		arg1 = num;

		/* 第一操作数后面漏掉","分隔符,报错 */
        if ( ! skipCh(','))
            return error("Missing comma", lineNo,loc);

		/* 第二偏移地址操作数错误,非数字偏移地址,报错 */
        if (! getNum ())
            return error("Bad displacement", lineNo,loc);

		/* 将第二偏移地址操作数arg2赋值为当前地址num */
        arg2 = num;

		/* 第二偏移地址操作数后漏掉"(",或者是","分隔符,报错 */
        if ( ! skipCh('(') && ! skipCh(',') )
            return error("Missing LParen", lineNo,loc);

		/* 第二寄存器操作数错,非0-7之间数字,报错 */
		if ( (! getNum ()) || (num < 0) || (num >= NO_REGS))
            return error("Bad second register", lineNo,loc);

		/* 将第三操作数arg3赋值为当前数值num */
        arg3 = num;
        break;
        }

	  /* 按代码地址标号loc将指令存储到指令存储区iMem */
      iMem[loc].iop = op;
      iMem[loc].iarg1 = arg1;
      iMem[loc].iarg2 = arg2;
      iMem[loc].iarg3 = arg3;
    }
  }
  return TRUE;
} /* readInstructions */



/************************************************/
/* 函数名 stepTM								*/
/* 功  能 TM机单步执行函数						*/
/* 说  明 函数为一条指令解释执行,完成指令动作.	*/
/************************************************/
STEPRESULT stepTM (void)

{ 
  /* currentinstruction 用于存储当前将执行的指令 */
  INSTRUCTION currentinstruction  ;		

  int pc  ;			/* 程序计数器 */

  int r,s,t,m  ;	/* 指令操作数 */
  
  int ok ;			

  /* pc设置为第7个寄存器reg[7]的值,为程序计数器 */
  pc = reg[PC_REG] ;						

  if ( (pc < 0) || (pc > IADDR_SIZE)  )
  /* pc的值不是指令存储区的有效地址,报指令存储错,函数返回srIMEM_ERR */
      return srIMEM_ERR ;

  /* pc的值为有效指令地址,将程序计数器reg[PC_REG]的值加1 */
  reg[PC_REG] = pc + 1 ;

  /* 从指令存储区iMem之中取出当前指令 */
  currentinstruction = iMem[ pc ] ;

  /* 对取出的指令的寻址模式分类处理,初始化各个指令操作数变量 */
  switch (opClass(currentinstruction.iop) )

  { case opclRR :		/* 寄存器寻址模式 */

	  r = currentinstruction.iarg1 ;
      s = currentinstruction.iarg2 ;
      t = currentinstruction.iarg3 ;
      break;

    case opclRM :		/* 寄存器-内存寻址模式 */

	  r = currentinstruction.iarg1 ;
      s = currentinstruction.iarg3 ;
      m = currentinstruction.iarg2 + reg[s] ;
      
	  /* 操作数m非数据存储区有效地址,报数据存储错,函数返回srDMEM_ERR */
	  if ( (m < 0) || (m > DADDR_SIZE))
         return srDMEM_ERR ;
      break;

    case opclRA :		/* 寄存器-立即数寻址模式 */

      r = currentinstruction.iarg1 ;
      s = currentinstruction.iarg3 ;
      m = currentinstruction.iarg2 + reg[s] ;
      break;
  } /* case */

  /* 对将执行指令的操作码值进行分类处理,输出指令信息,	*
   * 完成指令动作,返回相应结果状态						*/
  switch ( currentinstruction.iop)

  {
	/******************** RR指令 ******************/

    /* 格式化屏幕显示HALT(停止)指令,返回状态srHALT(停止) */
    case opHALT :
      printf("HALT: %1d,%1d,%1d\n",r,s,t);
      return srHALT ;
    /**********************************************/
	  
    case opIN :
      do
      { 
		/* 屏幕显示用户提示信息,提示用户为指令输入数值 */
		printf("Enter value for IN instruction: ") ;

		/* 刷新标准输入stdin流和标准输出stdout流 */
        fflush (stdin);
        fflush (stdout);

		/* 从标准输入流stdin取得用户输入的数值,送入缓冲器in_Line */
        //gets(in_Line);
        gets_s(in_Line,sizeof(in_Line));

		/* 取得用户输入数值的实际长度lineLen */
        lineLen = strlen(in_Line) ;

        inCol = 0;

		/* 如从缓冲器in_Line中成功取得数字,则ok为TRUE */
        ok = getNum();

		/* 用户输入的不是数字,报非法IN指令输入错误 */
        if ( ! ok ) printf ("Illegal value\n");

		/* 将输入的数值num送入IN指令操作数r指定的寄存器reg[r] */
        else reg[r] = num;
      }

	  /* 如果用户输入不合法,重复要求用户输入,直到输入合法数值 */
      while (! ok);
      break;
    /**********************************************/

	/* 屏幕显示OUT指令执行的结果信息 */
    case opOUT :  
      printf ("OUT instruction prints: %d\n", reg[r] ) ;
      break;
    /**********************************************/

	/* 完成ADD指令操作 */
    case opADD :  reg[r] = reg[s] + reg[t] ;  break;
    /**********************************************/

	/* 完成SUB指令操作 */
    case opSUB :  reg[r] = reg[s] - reg[t] ;  break;
    /**********************************************/

	/* 完成MUL指令操作 */
    case opMUL :  reg[r] = reg[s] * reg[t] ;  break;
    /**********************************************/

    case opDIV :

	  /* 对于除法指令,若除数为0,则报除零错误, *
	   * 并返回srZERODIVIDE;否则,完成除法操作 */
	  if ( reg[t] != 0 ) reg[r] = reg[s] / reg[t];
      else return srZERODIVIDE ;
      break;

    /***************** RM 指令 ********************/

	/* 将数据存储区dMem中的数据载入到寄存器reg[r] */
    case opLD :    reg[r] = dMem[m] ;  break;

    /**********************************************/

	/* 将寄存器reg[r]中的数据写入到数据存储区dMem */
    case opST :    dMem[m] = reg[r] ;  break;
		

    /***************** RA 指令 ********************/

	/* 将寄存器reg[r]赋值为操作数m的值 */
    case opLDA :    reg[r] = m ; break;
    /**********************************************/

	/* 将寄存器reg[r]赋值为当前指令的第二操作数的值 */
    case opLDC :    reg[r] = currentinstruction.iarg2 ;   break;

    /**********************************************/

	/* 如果寄存器reg[r]的值小于0,则将程序计数器reg[PC_REG]的值	*
	 * 赋值为立即数m,产生小于条件跳转							*/
    case opJLT :    if ( reg[r] <  0 ) reg[PC_REG] = m ; break;

    /**********************************************/

	/* 如果寄存器reg[r]的值小于等于0,则将程序计数器reg[PC_REG]的值	*
	 * 赋值为立即数m,产生小于等于条件跳转							*/
    case opJLE :    if ( reg[r] <=  0 ) reg[PC_REG] = m ; break;

    /**********************************************/

	/* 如果寄存器reg[r]的值大于0,则将程序计数器reg[PC_REG]的值	*
	 * 赋值为立即数m,产生大于条件跳转							*/
    case opJGT :    if ( reg[r] >  0 ) reg[PC_REG] = m ; break;

    /**********************************************/

	/* 如果寄存器reg[r]的值大于等于0,则将程序计数器reg[PC_REG]的值	*
	 * 赋值为立即数m,产生大于等于跳转								*/
    case opJGE :    if ( reg[r] >=  0 ) reg[PC_REG] = m ; break;

    /**********************************************/

	/* 如果寄存器reg[r]的值等于0,则将程序计数器reg[PC_REG]的值	*
	 * 赋值为立即数m,产生等于条件跳转							*/
    case opJEQ :    if ( reg[r] == 0 ) reg[PC_REG] = m ; break;

    /**********************************************/

	/* 如果寄存器reg[r]的值不等于0,则将程序计数器reg[PC_REG]的值	*
	 * 赋值为立即数m,产生不等于条件跳转								*/
    case opJNE :    if ( reg[r] != 0 ) reg[PC_REG] = m ; break;
  } 
  /* case */

  /* 所有正常结束指令,返回正常结果状态 */
  return srOKAY ;

} /* stepTM */


/****************************************************/
/* 函数名 doCommand									*/
/* 功  能 TM机交互命令处理函数						*/
/* 说  明 函数处理用户输入的TM操作命令,完成相应动作	*/
/****************************************************/
int doCommand (void)

{ char cmd;				/* 用户输入命令简称 */

  int stepcnt=0, i;
  int printcnt;
  int stepResult;
  int regNo, loc;
  do
  { 
    /* 屏幕显示提示信息,提示用户输入TM命令 */
	printf ("Enter command: ");

	/* 刷新标准输入输出流 */
    fflush (stdin);
    fflush (stdout);

	/* 从标准输入流中取得用户输入的命令 */
    //gets(in_Line);
    gets_s(in_Line,sizeof(in_Line));

    lineLen = strlen(in_Line);
    inCol = 0;
  }
  /* 重复请求用户输入命令名,直到得到文字输入 */
  while (! getWord ());

  cmd = word[0] ;		/* 取输入命令名中的第一个字符给cmd */

  switch ( cmd )
  { 
 	/* 该命令用于设置指令执行追踪标志,追踪指令执行 */
    case 't' :

      traceflag = ! traceflag ;		/* 取反设置追踪标志traceflag */

	  /* 输出TM机t命令执行结果信息 */
      printf("Tracing now ");
      if ( traceflag ) printf("on.\n"); else printf("off.\n");
      break;
    /**************************************************************/

    /* 该命令输出帮助信息列表,显示各种命令及其功能 */
    case 'h' :

      printf("Commands are:\n");

	  /* 按步执行(step)命令:可输入"s(tep <n>"来执行,	*
	   * 可执行n(默认为1)条tm指令.						*/
      printf("   s(tep <n>      "\
             "Execute n (default 1) TM instructions\n");

	  /* 执行到结束(go)命令:可输入"g(o"来执行,	*
	   * 顺序执行tm指令直到遇到HALT指令			*/
      printf("   g(o            "\
             "Execute TM instructions until HALT\n");

	  /* 显示寄存器(regs)命令:可输入"r(egs"来执行,	*
	   * 显示各寄存器的内容							*/
      printf("   r(egs          "\
             "Print the contents of the registers\n");

	  /* 输出指令(iMem)命令:可输入"i(Mem <b<n>>"来执行,	*
	   * 从地址b处输出n条指令							*/
      printf("   i(Mem <b <n>>  "\
             "Print n iMem locations starting at b\n");

	  /* 输出数据(dMem)命令:可输入"d(Mem<b<n>>"来执行,	*
	   * 从地址b处输出n跳数据							*/
      printf("   d(Mem <b <n>>  "\
             "Print n dMem locations starting at b\n");

	  /* 跟踪(trace)命令:可输入"t(race"来执行,		*
	   * 反置追踪标志traceflag,如果traceflag为TRUE,	*
	   * 则执行每条指令时候显示指令					*/
      printf("   t(race         "\
             "Toggle instruction trace\n");

	  /* 显示执行指令数量(print)命令:可输入"p(rint)"来执行,	*
	   * 反置追踪标志icountflag,如果icountflag为TRUE,		*
	   * 则显示已经执行过的指令数量.只在执行"go"命令时有效	*/
      printf("   p(rint         "\
             "Toggle print of total instructions executed"\
             " ('go' only)\n");

	  /* 重置tm机用(clear)命令:可输入"c(lear"来执行,	*
	   * 重新设置tm虚拟机,用以执行新的程序.				*/
      printf("   c(lear         "\
             "Reset simulator for new execution of program\n");

	  /* 帮助(help)命令:可输入"h(elp"来执行,显示命令列表 */
      printf("   h(elp          "\
             "Cause this list of commands to be printed\n");

	  /* 终止(quit)命令,可输入"q(uit"来执行,结束虚拟机的执行 */
      printf("   q(uit          "\
             "Terminate the simulation\n");

      break;
    /**************************************************************/

	/* 跟踪显示所有执行过指令的p命令 */
    case 'p' :

      icountflag = ! icountflag ;		/* 设置执行指令计数标志 */

	  /* 输出p命令执行的结果信息 */
      printf("Printing instruction count now ");
      if ( icountflag ) printf("on.\n"); else printf("off.\n");
      break;
    /**************************************************************/

	/* 按步执行s命令 */
    case 's' :

	  /* 缺省的命令模式,不带命令参数,单步执行 */
      if ( atEOL ())  stepcnt = 1;

	  /* 带有命令参数的命令模式,取得参数stepcnt */
      else if ( getNum ())  stepcnt = abs(num);

	  /* 输出未知命令执行步数信息 */
      else   printf("Step count?\n");
      break;
    /**************************************************************/


	/* 执行到结束g命令 */
    case 'g' :   stepcnt = 1 ;     break;
    /**************************************************************/

    /* 显示寄存器内容(regs)命令 */
    case 'r' :

  	  /* 格式化显示所有寄存器内容 */
      for (i = 0; i < NO_REGS; i++)
      { printf("%1d: %4d    ", i,reg[i]);
        if ( (i % 4) == 3 ) printf ("\n");
      }
      break;
    /**************************************************************/

	/* 输出指令存储区iMem中指令的i命令 */
    case 'i' :

	  /* 初始化输出指令数printcnt为1 */
	  printcnt = 1 ;

      if ( getNum ())
      { 
		/* 得到命令的第一个执行参数,iloc指定输出指令的开始地址 */
		iloc = num ;
		
		/* 得到命令的第二个执行参数,printcnt指定输出指令的数量 */
        if ( getNum ()) printcnt = num ;
      }

	  /* 未给定指令开始地址和输出指令数量 */
      if ( ! atEOL ())
        printf ("Instruction locations?\n");

      else
      { 
		/* 指令地址iloc在指令存储区iMem地址范围中,						*
		 * 且指令输出数量printcnt大于0,从iloc指定地址输出指定数量指令	*/
	    while ((iloc >= 0) && (iloc < IADDR_SIZE)
                && (printcnt > 0) )
        { writeInstruction(iloc);
          iloc++ ;
          printcnt-- ;
        }
      }
     break;
    /**************************************************************/

	/* 输出数据存储区dMem中的数据的d命令 */
    case 'd' :

	  printcnt = 1 ;
      if ( getNum  ())
      { 
		/* 取得命令的第一执行参数,数据存储的开始地址dloc */
		dloc = num ;

		/* 取得命令的第二执行参数,输出数据的数量printcnt */
        if ( getNum ()) printcnt = num ;
      }

	  /* 未给定数据存储区中的数据开始地址和数量 */
      if ( ! atEOL ())
        printf("Data locations?\n");

      else
      {
  	    /* 给定数据地址dloc在数据存储区dMen地址范围内,					*
		 * 且数据输出数量printcnt大于0,从dloc指定地址输出指定数量的数据 */
		while ((dloc >= 0) && (dloc < DADDR_SIZE)
                  && (printcnt > 0))
        { printf("%5d: %5d\n",dloc,dMem[dloc]);
          dloc++;
          printcnt--;
        }
      }
      break;
    /**************************************************************/

    /* 重置tm机用以执行新的程序(clear)指令 */
    case 'c' :

      iloc = 0;			/* 指令存储地址,初始为0 */

      dloc = 0;			/* 数据存储地址,初始为0 */

      stepcnt = 0;		/* 指令执行步数,初始为0 */

	  /* 初始化各寄存器reg[]为0 */
      for (regNo = 0;  regNo < NO_REGS ; regNo++)
            reg[regNo] = 0 ;			

	  /* 数据存储区0地址单元用于记录数据存储区dMem的高端地址 */
      dMem[0] = DADDR_SIZE - 1 ;

	  /* 初始化其它数据存储区单元为0 */
      for (loc = 1 ; loc < DADDR_SIZE ; loc++)
            dMem[loc] = 0 ;				
      break;
    /**************************************************************/

    case 'q' : return FALSE;		/* 停止执行并退出命令 */
    /**************************************************************/

	/* 其它未定义命令,输出错误信息 */
    default : printf("Command %c unknown.\n", cmd); break;

  }  /* case */

  /******************** 命令的后续处理 **********************/

  stepResult = srOKAY;		/* 命令执行结果为srOKAY */

  if ( stepcnt > 0 )
  { if ( cmd == 'g' )
    { stepcnt = 0;			/* 此处stepcnt作为已经执行过的指令数目 */

      while (stepResult == srOKAY)
      { 
		/* 根据执行指令追踪标志traceflag,将当前地址iloc上指令输出到屏幕 */
		iloc = reg[PC_REG] ;
        if ( traceflag ) writeInstruction( iloc ) ;

		/* 单步执行当前指令,结果返回stepResult */
        stepResult = stepTM ();
		
		/* 执行过指令计数stepcnt加1 */
        stepcnt++;

      }

	  /* 根据执行执行数量追踪标志icountflag,显示已经执行过的指令数量 */
      if ( icountflag )
        printf("Number of instructions executed = %d\n",stepcnt);
    }

    else
    { 
	  /* 在其它命令中stepcnt作为将执行,输出的指令或数据的数量 */	
	  while ((stepcnt > 0) && (stepResult == srOKAY))

      { 
		/* 取得程序计数器reg[PC_REG]中当前指令地址 */
		iloc = reg[PC_REG] ;

		/* 根据执行指令追踪标志traceflag,将当前指令地址iloc上指令输出到屏幕 */
        if ( traceflag ) writeInstruction( iloc ) ;

		/* 执行当前指令,结果返回stepResult */
        stepResult = stepTM ();

		/* stepcnt此时用于记录将要执行,输出的指令或数据的数量,自减 */
        stepcnt-- ;
      }
    }
	/* 根据执行结果的枚举值,查执行结果状态表,显示结果状态 */
    printf( "%s\n",stepResultTab[stepResult] );
  }
  return TRUE;
} /* doCommand */



/********************************************/
/* 函数名 tmain								*/
/* 功  能 tm机主执行函数					*/
/* 说  明 函数完成tm机的命令处理,			*/
/*		  并解释执行目标指令				*/
/********************************************/ 
void tmain(char * codefile)

{ 
   //char pgmName[120];
   /*提示输入源代码文件目录名*/
   //printf("input program names:\n");   
   /*存储文件目录名到pgm中*/
   //scanf("%s",pgmName);  
	printf("codefile:%s\npress enter to continue...",codefile);
   getchar();
   
   pgm = fopen(codefile/*pgmName*/,"r");	

  /* 未能成功打开目标代码文件pgmName,输出错误信息 */
  if (pgm == NULL)
  { printf("file '%s' not found\n",codefile);
    exit(1);
  }								

  /* 读入指令:将指令存储区iMem清空并从指定的文件中写入指令序列 */
  if ( ! readInstructions ())
         exit(1) ;

  /* 准备执行TM虚拟机命令,输出提示信息 */
  printf("TM  simulation (enter h for help)...\n");

  /* 交互执行,处理用户输入的TM命令,对已经输入到iMem中的指令进行操作 */
  do
     done = ! doCommand ();	
  while (! done );

  /* 虚拟机命令执行完毕 */
  printf("Simulation done.\n");

}
