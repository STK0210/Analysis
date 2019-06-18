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
            PROGRAM = 256, PROCEDURE = 257, ID = 258, RETURN = 259, BEGIN = 260, RECORD = 261, END = 262, TYPE = 263, VAR = 264, INTC = 265, INTEGER = 266, REAL = 267, CHAR = 268, ARRAY = 269, OF = 270, INDEX = 271, READ = 272, WRITE = 273, ASSIGN = 274, AND = 275, OR = 276, EQ = 277, NE = 278, LE = 279, GE = 280, IF = 281, THEN = 282, ELSE = 283, FI = 284, WHILE = 285, DO = 286, ENDWH = 287, BREAK = 288, BASIC = 289, NUM = 290, TRUE = 291, FALSE = 292, TEMP = 293;
}
