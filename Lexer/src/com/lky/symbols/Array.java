package com.lky.symbols;

import com.lky.tag.Tag;

/**
 * @auther likeyu
 * @create 2019-06-15-11:36
 **/

public class Array extends Type {

    public Type of;//数组的元素类型
    public int size = 1;//元素的个数(默认)

    public Array(int sz, Type tp) {
        super("[]", Tag.INDEX, sz * tp.width);
        size = sz;
        of = tp;
    }

    @Override
    public String toString() {
        return "Array{" +
                "size=" + size +
                '}';
    }
}
