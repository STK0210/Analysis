package com.lky.inter;

import com.lky.lexer.Lexer;

/**
 * @auther likeyu
 * @create 2019-06-17-22:04
 **/

public class Node {
    int lexline = 0;//行号

    public Node() {
        lexline = Lexer.line;
    }

    public void error(String err) {
        throw new Error("near line " + lexline + ": " + err);
    }

}
