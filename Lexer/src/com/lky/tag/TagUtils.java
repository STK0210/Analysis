package com.lky.tag;

import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * @auther likeyu
 * @create 2019-06-15-15:24
 **/
public class TagUtils {

    @Test
    public void createTag() {

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        String[] tag = {
                "PROGRAM",//程序头
                "PROCEDURE",//过程头
                "ID",//名字
                "RETURN",//返回

                "BEGIN",
                "RECORD",
                "END",

                "TYPE",
                "VAR",
                "INTC",//无符号整数
                "INTEGER",//整数
                "REAL",//实数
                "CHAR",
                "ARRAY",
                "OF",
                "INDEX",

                "READ",
                "WRITE",

                "ASSIGN",//:=
                "AND",//&&
                "OR",//||
                "EQ",//==
                "NE",//!=
                "LE",//<=
                "GE",//>=

                "IF",
                "THEN",
                "ELSE",
                "FI",

                "WHILE",
                "DO",
                "ENDWH",
                "BREAK",

                "BASIC",
                "NUM",

                "TRUE",
                "FALSE",
                "TEMP"
        };

        for (int i = 0; i < tag.length; i++) {
            map.put(tag[i], i + 256);
        }

        System.out.println(map);
    }
}
