package com.lky.lexer.token;

import com.lky.lexer.tag.Tag;

/**
 * @auther likeyu
 * @create 2019-06-14-23:47
 **/

public class Token {

    public final Tag tag;

    public Token(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tag=" + tag +
                '}';
    }
}
