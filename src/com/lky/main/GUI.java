package com.lky.main;

/**
 * @auther likeyu
 * @create 2019-06-20-11:59
 **/

import com.lky.UI.PrintTree;
import com.lky.lexer.Lexer;
import com.lky.parser.Parser;
import com.lky.token.Token;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;

public class GUI {

    private JFrame frame;

    Lexer lex;
    Parser parser;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("编译原理分析器");
        frame.setBounds(100, 100, 1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel code = new JLabel("代码区");
        code.setBounds(139, 13, 45, 18);
        panel.add(code);

        JLabel lexer = new JLabel("词法表");
        lexer.setBounds(363, 13, 72, 18);
        panel.add(lexer);

        JLabel parser = new JLabel("语法树");
        parser.setBounds(701, 13, 50, 18);
        panel.add(parser);

        JScrollPane scrollPane_code = new JScrollPane();
        scrollPane_code.setBounds(14, 37, 290, 663);
        panel.add(scrollPane_code);

        JTextArea textArea_code = new JTextArea();
        scrollPane_code.setViewportView(textArea_code);

        JScrollPane scrollPane_lexer = new JScrollPane();
        scrollPane_lexer.setBounds(318, 37, 133, 663);
        panel.add(scrollPane_lexer);

        JTextArea textArea_lexer = new JTextArea();
        scrollPane_lexer.setViewportView(textArea_lexer);

        JScrollPane scrollPane_parser = new JScrollPane();
        scrollPane_parser.setBounds(465, 37, 503, 663);
        panel.add(scrollPane_parser);

        JTextArea textArea_parserTree = new JTextArea();
        scrollPane_parser.setViewportView(textArea_parserTree);

        JButton button_clear = new JButton("清空");
        button_clear.setBounds(121, 713, 63, 27);
        panel.add(button_clear);

        JButton button_lexer = new JButton("运行词法分析");
        button_lexer.setBounds(324, 713, 123, 27);
        panel.add(button_lexer);

        JButton button_parser = new JButton("运行语法分析");
        button_parser.setBounds(660, 713, 123, 27);
        panel.add(button_parser);

        button_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_clear) {
                    textArea_code.setText("");
                }
            }
        });

        button_lexer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_lexer) {
                    String sourse = textArea_code.getText();
                    if (!sourse.equals("")) {
                        lex = new Lexer(sourse);
                        List<Token> list = null;
                        try {
                            list = lex.getTokens();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        textArea_parserTree.setText("");
                        textArea_lexer.append("Tag" + "\t" + "Lexeme\t\n");
                        textArea_lexer.append("==========================\n");
                        for (Token token : list) {
                            textArea_lexer.append(token.tag + "\t" + token.lexeme + "\t\n");
                        }
                    } else {
                        textArea_lexer.setText("");
                    }

                }
            }
        });

        button_parser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_parser) {
                    String sourse = textArea_code.getText();
                    if (!sourse.equals("")) {
                        lex = new Lexer(sourse);
                        Parser parser = null;
                        try {
                            parser = new Parser(lex);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        parser.Program();
                        PrintTree tree = new PrintTree();
                        tree.printTree(parser.root, 1, 0);
                        textArea_parserTree.setText("");
                        textArea_parserTree.setText(tree.result);
                    } else {
                        textArea_parserTree.setText("");
                    }
                }
            }
        });

    }
}
