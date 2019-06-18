package com.lky.token;

import com.lky.tag.Tag;

/**
 * @auther likeyu
 * @create 2019-06-14-23:51
 **/

public class Integer extends Token {

    public final int tag;
    public final int value;

    public Integer(int v) {
        super(Tag.INTEGER, java.lang.Integer.toString(v));
        this.tag = Tag.INTEGER;
        this.value = v;
    }


}
