package com.lky.token;

/**
 * @auther likeyu
 * @create 2019-06-14-23:47
 **/

public class Token {

    public final int tag;
    public final String lexeme;

    public Token(int tag, String lexeme) {
        this.tag = tag;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tag=" + tag +
                ", lexeme='" + lexeme + '\'' +
                '}';
    }
}
