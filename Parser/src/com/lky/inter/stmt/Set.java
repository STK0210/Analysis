package com.lky.inter.stmt;

import com.lky.inter.expr.Expr;
import com.lky.inter.expr.Id;
import com.lky.symbols.Type;

/**
 * @auther likeyu
 * @create 2019-06-18-0:04
 **/

public class Set extends Stmt {
    public Id id;
    public Expr expr;

    public Set(Id i, Expr e) {
        id = i;
        expr = e;
        if (check(id.type, expr.type) == null)
            error("type error");
    }

    public Type check(Type p1, Type p2) {
        if (Type.numeric(p1) && Type.numeric(p2))
            return p2;
        else if (p1 == Type.Bool && p2 == Type.Bool)
            return p2;
        else
            return null;
    }

}
