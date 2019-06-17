package com.lky.token;

/**
 * @auther likeyu
 * @create 2019-06-14-23:47
 **/

public class Token {

    public final int tag;

    public Token(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tag=" + tag +
                " , TAG='" + (char) tag + "'" +
                '}';
    }
}
