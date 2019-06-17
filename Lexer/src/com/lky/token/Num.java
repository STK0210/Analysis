package com.lky.token;

import com.lky.tag.Tag;

/**
 * @auther likeyu
 * @create 2019-06-14-23:51
 **/

public class Num extends Token {

    public final int value;

    public Num(int v) {
        super(Tag.NUM);
        value = v;
    }

    @Override
    public String toString() {
        return "Num{" +
                "value=" + value +
                '}';
    }
}
