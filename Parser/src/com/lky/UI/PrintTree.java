package com.lky.UI;

import com.lky.inter.Node;

import java.util.*;

/**
 * @auther likeyu
 * @create 2019-06-19-9:58
 **/

public class PrintTree {

    public final static int space = 2;

    public static Map<Integer, Integer> levelMap = new HashMap<Integer, Integer>();

    public static void Pri(Node root) {
//        if (root == null)
//            return;
//        System.out.print(root.NodeName);
//        if (root.list.size() > 0) {
//            Print(root.list.get(0), "");
//        }
//        if (root.list.size() > 1) {
//            for (int i = 1; i < root.list.size(); i++) {
//                Print(root.list.get(i), "        |");
//            }
//        }
        Print(root, "");
    }

    static void Print(Node node, String indent) {
//        System.out.print("========" + node.NodeName);
//        if (node.list.size() > 0) {
//            Print(node.list.get(0), "--------");
//        }
//        System.out.print("\n");
//        if (node.list.size() > 1) {
//            for (int i = 1; i < node.list.size(); i++) {
//                Print(node.list.get(i), indent+"       \\");
//            }
//        }
        if (node.list.size() == 0) {
            System.out.print(indent + node.NodeName + "\n");
            return;
        }
        System.out.print(indent + node.NodeName + "-->");
        if (node.list.size() > 0) {
            for (int i = 0; i < node.list.size(); i++) {
                Print(node.list.get(i), indent);
            }
        }

    }


    private static void treeLevelCount(Node node, int level) {
        System.out.printf("visit, node:%s, level:%d\n", node.NodeName, level);

        // 更新计数
        Integer levelCount = levelMap.get(level);
        if (levelCount == null) {
            levelCount = 1;
        } else {
            levelCount++;
        }
        levelMap.put(level, levelCount);
        // 对每个子节点进行递归调用
        for (Node subNode : node.list) {
            treeLevelCount(subNode, level + 1);
        }
    }

    private static void printLevelCount() {
        System.out.printf("------------------- level count\n");
        for (Map.Entry<Integer, Integer> entry : levelMap.entrySet()) {
            System.out.printf("level:%d, count:%d\n", entry.getKey(), entry.getValue());
        }
    }

    //层数及结点统计
    public static void test(Node root) {
        treeLevelCount(root, 1);
        printLevelCount();
    }

    //单纯缩进版本
    public static void displayTree(Node node, int level) {
        String preStr = "";     // 打印前缀
        for (int i = 0; i < level; i++) {
            preStr += "    ";
        }
        for (int i = 0; i < node.list.size(); i++) {
            Node son = node.list.get(i);
            System.out.println(preStr + son.NodeName);//preStr + "-" + t.NodeName
            if (!son.list.isEmpty()) {
                displayTree(son, level + 1);
            }
        }
    }


}