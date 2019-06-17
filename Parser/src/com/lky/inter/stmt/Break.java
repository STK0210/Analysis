package com.lky.inter.stmt;

/**
 * @auther likeyu
 * @create 2019-06-18-0:17
 **/

public class Break extends Stmt {
    Stmt stmt;

    public Break() {
        if (Stmt.Enclosing == Stmt.Null)
            error("unenclosed break");
        stmt = Stmt.Enclosing;
    }

}
