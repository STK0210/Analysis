package com.lky.inter;

import com.lky.lexer.Lexer;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther likeyu
 * @create 2019-06-17-22:04
 **/

public class Node {

    public final String NodeName;

    public final List<Node> list = new LinkedList<>();

    int lexline = 0;//行号

    public Node(String nodeName, Node... nodes) {
        NodeName = nodeName;
        lexline = Lexer.line;
        for (int i = 0; i < nodes.length; i++) {
            list.add(nodes[i]);
        }
    }

    public void error(String err) {
        throw new Error("near line " + lexline + ": " + err);
    }

}
