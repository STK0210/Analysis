package com.lky.main;

import com.lky.UI.PrintTree;
import com.lky.inter.Node;
import com.lky.lexer.Lexer;
import com.lky.parser.Parser;
import com.lky.token.Token;

import java.io.IOException;
import java.util.List;

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
        String sourses2 = "program  bubble\n" +
                "var  integer  i,j,num;\n" +
                "     array [1..20] of integer  a;\n" +
                "procedure  q(integer num);\n" +
                "var  integer i,j,k;\n" +
                "     integer t;\n" +
                "begin\n" +
                "  i:=1;\n" +
                "   while i < num do\n" +
                "     j:=num-i+1;\n" +
                "     k:=1;\n" +
                "     while k<j  do\n" +
                "    \tif a[k+1] < a[k]  \n" +
                "then   \n" +
                "\tt:=a[k];\n" +
                "\t\ta[k]:=a[k+1];\n" +
                "\t\ta[k+1]:=t\n" +
                "         else  temp:=0\n" +
                "         fi;   \n" +
                "     k:=k+1\n" +
                "     endwh;\n" +
                "  i:=i+1\n" +
                "  endwh\n" +
                "end\n" +
                "\n" +
                "begin\n" +
                "   read(num);\n" +
                "   i:=1;\n" +
                "   while i<(num+1)  do\n" +
                "     read(j);\n" +
                "     a[i]:=j;\n" +
                "     i:=i+1\n" +
                "   endwh;\n" +
                "   q(num);\n" +
                "   i:=1;\n" +
                "   while  i<(num+1) do \n" +
                "       write(a[i]);\n" +
                "       i:=i+1\n" +
                "   endwh\n" +
                "end.";
        Lexer lex = new Lexer(sourses);
        Parser parser = new Parser(lex);
        parser.Program();
        PrintTree.printTree(parser.root, 1, 0);

    }
}
