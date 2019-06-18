package com.lky.token;

import com.lky.tag.Tag;

/**
 * @auther likeyu
 * @create 2019-06-15-0:01
 **/

public class Real extends Token {

    public final int tag;
    public final float value;

    public Real(float v) {
        super(Tag.REAL, Float.toString(v));
        this.tag = Tag.REAL;
        this.value = v;
    }

    @Override
    public String toString() {
        return "Real{" +
                "tag=" + tag +
                ", value=" + value +
                '}';
    }

}
