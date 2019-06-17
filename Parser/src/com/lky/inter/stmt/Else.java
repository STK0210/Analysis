package com.lky.inter.stmt;

import com.lky.inter.expr.Expr;
import com.lky.symbols.Type;

/**
 * @auther likeyu
 * @create 2019-06-17-23:19
 **/

public class Else extends Stmt {
    Expr expr;
    Stmt stmt1, stmt2;

    public Else(Expr x, Stmt s1, Stmt s2) {
        expr = x;
        stmt1 = s1;
        stmt2 = s2;
        if (expr.type != Type.Bool)
            expr.error("boolean required in if");
    }
}
