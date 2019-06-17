package com.lky.tag;

/**
 * @auther likeyu
 * @create 2019-06-14-22:30
 **/

/**
 * 定义各个词法单元所对应的常量
 * 具体分类查看com.lky.lexer.tag.TagUtils的备注
 */
public class Tag {

    public final static int
            PROGRAM = 256, PROCEDURE = 257, ID = 258, RETURN = 259, BEGIN = 260, RECORD = 261, END = 262, TYPE = 263, VAR = 264, INTC = 265, INTEGER = 266, REAL = 267, CHAR = 268, ARRAY = 269, OF = 270, INDEX = 271, READ = 272, WRITE = 273, AND = 274, OR = 275, EQ = 276, NE = 277, LE = 278, GE = 279, IF = 280, THEN = 281, ELSE = 282, FI = 283, WHILE = 284, DO = 285, ENDWH = 286, BREAK = 287, BASIC = 288, NUM = 289, TRUE = 290, FALSE = 291, TEMP = 292;
}
