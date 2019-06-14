package com.lky.lexer.tag;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @auther likeyu
 * @create 2019-06-14-22:30
 **/

public enum Tag {

    //头部(程序头部、过程头部)
    PROGRAM,
    PROCEDURE,

    //程序体结构
    BEGIN,
    END,

    //声明
    VAR,

    //类型
    TYPE,

    NUM,
    REAL,
    CHAR,
    ARRAY, OF, INTC,

    //符号
    SEMICOLON,//  ;
    COMMA,//  ,
    UNDERSCORE,//  _
    ADD, SUB, MUL, DIV,//  +  -  *  ÷
    EQ, NE, LT, GT, LE, GE,//  =  !=  <  >  <=  >=
    LEFT_BRACES, RIGHT_BRACES,//  {  }
    LEFT_SQUAREBRACKET, RIGHT_SQUAREBRACKET,//  [  ]
    LEFT_ANGLEBRACKET, RIGHT_ANGLEBRACKET,//  <  >
    LEFT_ANGLEQUOTES, RIGHT_ANGLEQUOTES,//  《  》
    LEFT_ROUNDBRACKET, RIGHT_ROUNDBRACKET,//  (  )
    DOT, EOF, SPACE,//  .  文件结束符  空格
    COLON_EQUAL, APOSTROPHE, TWO_DOT,//  :=  ...  ..

    //记录
    RECORD,//以END结尾

    //条件语句
    IF,
    THEN,
    ELSE,
    FI,

    //循环语句
    WHILE,
    DO,
    ENDWH,

    //输入输出
    READ,
    WRITE,

    //返回
    RETURN,

    //拷贝的SNL词法表(未分类)
    UNSIGNEDNUMBER,
    IDENTIFIERS;
}
