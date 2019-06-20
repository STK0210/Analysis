package com.lky.UI;

import com.lky.inter.Node;

import javax.xml.transform.Result;
import java.util.*;

/**
 * @auther likeyu
 * @create 2019-06-19-9:58
 **/

public class PrintTree {

    public final static int MAXDEPTH = 128;

    public final static String BLANK = "  ";
    public final static String VERTICAL = "│";
    public final static String HORIZON = "─";
    public final static String BRANCH = "├";
    public final static String BRANCHEND = "└";

    public final static int BLANK_FLAG = 0;
    public final static int VERTICAL_FLAG = 1;
    public final static int HORIZON_FLAG = 2;
    public final static int BRANCH_FLAG = 3;
    public final static int BRANCHEND_FLAG = 4;

    public final static int space = 2;

    public static int[] state = new int[MAXDEPTH + 1];
    public static Map<Integer, Integer> levelMap = new HashMap<Integer, Integer>();

    public static String result = "";

    /**
     * 打印一颗多叉树
     *
     * @param root  根节点
     * @param depth 层数
     * @param flag  标记
     */
    public static void printTree(Node root, int depth, int flag) {

        int i, tmp;
        int newflag;
        for (i = 0; i < depth; ++i) {
            switch (state[i]) {
                case BLANK_FLAG:
                    result += BLANK;
                    System.out.print(BLANK);
                    break;
                case VERTICAL_FLAG:
                    result += VERTICAL;
                    System.out.print(VERTICAL);
                    break;
                case BRANCH_FLAG:
                    result += BRANCH;
                    System.out.print(BRANCH);
                    break;
                case BRANCHEND_FLAG:
                    result += BRANCHEND;
                    System.out.print(BRANCHEND);
                    break;
                case HORIZON_FLAG:
                    result += HORIZON;
                    System.out.print(HORIZON);
                    break;
                default:
                    result += "error";
                    System.out.println("error");
            }
            if (i < depth - 1) {
                result += BLANK;
                System.out.print(BLANK);
            } else {
                result += HORIZON;
                System.out.print(HORIZON);
            }
        }
        result += root.NodeName + "\n";
        System.out.print(root.NodeName + "\n");

        if (depth > 0) {
            if (flag == 1) state[depth - 1] = BLANK_FLAG;
            else if (flag == 2) state[depth - 1] = VERTICAL_FLAG;
        }

        if (root.list.size() == 0)
            return;
        for (i = 0; i < root.list.size(); ++i) {
            newflag = 0;
            if (i == 0)
                newflag |= 2;
            if (i == root.list.size() - 1) {
                newflag |= 1;
                state[depth] = BRANCHEND_FLAG;
            } else state[depth] = BRANCH_FLAG;

            tmp = state[depth - 1];
            if (state[depth - 1] != BLANK_FLAG && state[depth - 1] != VERTICAL_FLAG)
                state[depth - 1] = VERTICAL_FLAG;
            printTree(root.list.get(i), depth + 1, newflag);
            state[depth - 1] = tmp;
        }
    }

    /**
     * 层数及结点统计
     *
     * @param root 根节点
     */
    public static void showLevelAndNode(Node root) {
        treeLevelCount(root, 1);
        printLevelCount();
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

    //单纯缩进版本
    public static void showTree(Node node, int level) {
        String preStr = "";     // 打印前缀
        for (int i = 0; i < level; i++) {
            preStr += "    ";
        }
        for (int i = 0; i < node.list.size(); i++) {
            Node son = node.list.get(i);
            System.out.println(preStr + "└" + node.NodeName);//preStr + "-" + t.NodeName
            if (!son.list.isEmpty()) {
                showTree(son, level + 1);
            }
        }
    }

    //<editor-fold desc="思路保留，以后更改">
    //基础默认版，方便更改
//    public static void Pri(Node root) {
////        if (root == null)
////            return;
////        System.out.print(root.NodeName + "--");
////        if (root.list.size() > 0) {
////            Print(root.list.get(0), "|  ");
////        }
////        if (root.list.size() > 1) {
////            for (int i = 1; i < root.list.size(); i++) {
////                Print(root.list.get(i), "\\--");
////            }
////        }
//
//
//        Print(root, "", 1);
//    }
//
//    static void Print(Node node, String indent, int level) {
////        if (node.list.size() == 0) {
////            System.out.print(node.NodeName);
////            System.out.println();
////            return;
////        }
//        if (node.list.size() == 0) {
//            System.out.print(indent + node.NodeName + "层数：" + level + "\n");
//            return;
//        }
//        System.out.print(indent + node.NodeName + "-->");
//        if (node.list.size() > 0) {
//            for (int i = 0; i < node.list.size(); i++) {
//                if (i == 0)
//                    Print(node.list.get(i), indent, level++);
//                else {
//                    if (node.list.get(i).list.size() == 0) {
//                        Print(node.list.get(i), "\\--" + indent, level++);
//                    } else {
//                        Print(node.list.get(i), indent, level++);
//                    }
//                }
//            }
//        }
//
//    }


    //瑕疵版，且算法不更新不可修复
//    public static void Pri(Node root) {
//        if (root == null)
//            return;
//        if (root.list.size() > 0) {
//            Print(root.list.get(0), true, "", 1);
//        }
//        System.out.println(root.NodeName);
//        if (root.list.size() > 1) {
//            for (int i = 1; i < root.list.size(); i++) {
//                Print(root.list.get(i), false, "", 1);
//            }
//        }
//    }
//
//    public static void Print(Node node, boolean isFirst, String indent, int layer) {
//        if (node.list.size() > 0) {
//            Print(node.list.get(0), true, indent + (isFirst ? "   " : "|  "), layer + 1);
//        }
//        System.out.print(indent);
//        System.out.print(isFirst ? "/" : "\\");
//        System.out.print("->");
//        System.out.println(node.NodeName);
//        String space = "";
//        if (node.list.size() > 1) {
//            for (int j = 0; j <= layer; j++) {
//                space += " ";
//            }
//            for (int i = 1; i < node.list.size(); i++) {
//                isFirst = false;
//                Print(node.list.get(i), false, (isFirst ? space + "|  " : "  |" + space), layer + 1);
//            }
//        }
//    }

//    public static void Pri(Node root) {
//        Print(root, "", true, 0);
//    }
//
//    public static void Print(Node node, String indent, boolean isFirst, int layer) {
//        if (node.list.size() == 0) {
//            if (isFirst) {
//                System.out.println(indent + node.NodeName);
//                return;
//            } else {
//                String temp = "";
//                for (int i = 0; i < layer - 1; i++) {
//                    temp = temp + "|  ";
//                }
//                System.out.println(temp + indent + node.NodeName);
//            }
//        }
//        if (node.list.get(0).list.size() == 0) {
//            Print(node.list.get(0), node.NodeName + "->", true, layer + 1);
//            String temp = "\\";
//            for (int i = 0; i < node.NodeName.length() - 1; i++) {
//                temp = temp + "-";
//            }
//            for (int i = 1; i < node.list.size(); i++) {
//                Print(node.list.get(i), temp + "->", false, layer + 1);
//            }
//            return;
//        }
//        if (node.list.size() > 0) {
//            Print(node.list.get(0), node.NodeName + "--", true, layer + 1);
//            for (int i = 1; i < node.list.size(); i++) {
//                Print(node.list.get(i), "\\--", false, layer + 1);
//            }
//        }
//    }
    //</editor-fold>
}