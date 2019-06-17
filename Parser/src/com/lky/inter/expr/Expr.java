package com.lky.inter.expr;

import com.lky.inter.Node;
import com.lky.symbols.Type;
import com.lky.token.Token;

/**
 * @auther likeyu
 * @create 2019-06-17-22:43
 **/

public class Expr extends Node {

    public Token op;
    public Type type;

    public Expr(Token tok, Type tp) {
        op = tok;
        type = tp;
    }

    public Expr gen() {
        return this;
    }

    public Expr reduce() {
        return this;
    }

}
