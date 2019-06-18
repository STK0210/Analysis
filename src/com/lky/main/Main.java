package com.lky.main;

import com.lky.lexer.Lexer;
import com.lky.parser.Parser;

import java.io.IOException;

/**
 * @auther likeyu
 * @create 2019-06-14-19:58
 **/
public class Main {

    public static void main(String[] args) throws IOException {

        String sourses = "program p\n" +
                "type t1 = integer;\n" +
                "var integer v1,v2;\n" +
                "procedure\n" +
                "q(integer i);\n" +
                "var integer a;\n" +
                "begin\n" +
                "a:=i;\n" +
                "write(a)\n" +
                "end\n" +
                "begin\n" +
                "read(v1);\n" +
                "if v1<10\n" +
                "then v1:=v1+10\n" +
                "else v1:=v1-10\n" +
                "fi;\n" +
                "q(v1)\n" +
                "end.";
        Lexer lex = new Lexer(sourses);
        Parser parser = new Parser(lex);
        parser.Program();
        System.out.println(parser.root.list);
    }
}
