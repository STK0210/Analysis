package com.lky.inter.stmt;

import com.lky.inter.expr.Expr;
import com.lky.symbols.Type;

/**
 * @auther likeyu
 * @create 2019-06-17-23:09
 **/

public class If extends Stmt {
    Expr expr;
    Stmt stmt;

    public If(Expr e, Stmt s) {
        expr = e;
        stmt = s;
        if (expr.type != Type.Bool)
            expr.error("boolean required in if");
    }
    
}
