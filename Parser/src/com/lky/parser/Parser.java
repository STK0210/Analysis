package com.lky.parser;

import com.lky.lexer.Lexer;
import com.lky.token.Token;

import java.io.IOException;

/**
 * @auther likeyu
 * @create 2019-06-17-18:24
 **/

public class Parser {

    private Lexer lex;
    private Token look;

    public Parser(Lexer lex) throws IOException {
        this.lex = lex;
        move();
    }

    void move() throws IOException {
        look = lex.scan();
    }

    void error(String err) {
        throw new Error("near line " + lex.line);
    }

    void match(int tag) throws IOException {
        if (look.tag == tag)
            move();
        else
            error("syntax error");
    }

    public void Program() throws IOException {//Program ::= ProgramHead DeclarePart ProgramBody .
        ProgramHead();
        DeclarePart();
        ProgramBody();
        match('.');
    }

    private void ProgramHead() {

    }

    private void DeclarePart() {

    }

    private void ProgramBody() {

    }

}
