package com.lky.inter.expr;

import com.lky.symbols.Type;
import com.lky.token.word.Word;

/**
 * @auther likeyu
 * @create 2019-06-18-0:06
 **/

public class Id extends Expr {
    public int offset;

    public Id(Word id, Type tp, int b) {
        super(id, tp);
        offset = b;
    }
}
