package com.lky.inter.stmt;

import com.lky.inter.expr.Expr;
import com.lky.symbols.Type;

/**
 * @auther likeyu
 * @create 2019-06-17-23:51
 **/
public class Do extends Stmt {
    Expr expr;
    Stmt stmt;

    public Do() {
        expr = null;
        stmt = null;
    }

    public void init(Expr e, Stmt s) {
        expr = e;
        stmt = s;
        if (expr.type != Type.Bool)
            expr.error("boolean required in do");
    }
}