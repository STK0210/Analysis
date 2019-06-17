package com.lky.parser;

import com.lky.lexer.token.Token;

import java.io.IOException;
import java.util.List;

/**
 * @auther likeyu
 * @create 2019-06-17-18:24
 **/

public class Parser {

    private List<Token> list;
    private Token look;

    private int index = 0;

    public Parser(List<Token> sourse) throws IOException {
        list = sourse;
        move();
    }

    void move() {
        look = list.get(index++);
    }

    void error(String str) {


    }

}
