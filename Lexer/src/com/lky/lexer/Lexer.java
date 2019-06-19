package com.lky.lexer;

import com.lky.tag.Tag;
import com.lky.token.Integer;
import com.lky.token.Real;
import com.lky.token.Token;
import com.lky.token.word.Word;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther likeyu
 * @create 2019-06-15-11:51
 **/

public class Lexer {

    public static int line = 1;
    char peek = ' ';
    Hashtable words = new Hashtable();

    char[] in;
    int index = 0;

    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    public Lexer(String sourses) {

        reserve(new Word("program", Tag.PROGRAM));
        reserve(new Word("procedure", Tag.PROCEDURE));
        reserve(new Word("return", Tag.RETURN));

        reserve(new Word("begin", Tag.BEGIN));
        reserve(new Word("record", Tag.RECORD));
        reserve(new Word("end", Tag.END));

        reserve(new Word("type", Tag.TYPE));
        reserve(new Word("var", Tag.VAR));

        reserve(new Word("array", Tag.ARRAY));
        reserve(new Word("of", Tag.OF));

        reserve(new Word("INTC", Tag.INTC));
        reserve(new Word("integer", Tag.INTEGER));

        reserve(new Word("if", Tag.IF));
        reserve(new Word("then", Tag.THEN));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("fi", Tag.FI));

        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("endwh", Tag.ENDWH));
        reserve(new Word("break", Tag.BREAK));

        in = sourses.toCharArray();
    }

    /**
     * 读取一个字符
     *
     * @throws IOException
     */
    void readch() throws IOException {
        if (index < in.length) {
            peek = in[index++];
        } else {
            peek = (char) -1;
        }
    }

    /**
     * 读取下一个字符判断是否是指定的字符c
     *
     * @param c
     * @return 下一个字符是指定字符或不是指定字符
     * @throws IOException
     */
    boolean readch(char c) throws IOException {
        readch();
        if (peek != c)
            return false;
        peek = ' ';
        return true;
    }

    public Token scan() throws IOException {
        //跳过所有的空白字符
        for (; ; readch()) {
            if (peek == ' ' || peek == '\t')
                continue;
            else if (peek == '\n')
                line++;
            else
                break;
        }
        //识别复合的词法单元
        switch (peek) {
            case ':':
                if (readch('='))
                    return Word.assign;
                else
                    return new Token(':', ":");
            case '.':
                if (readch('.'))
                    return Word.index;
                else
                    return new Token('.', ".");
            case '&':
                if (readch('&'))
                    return Word.and;
                else
                    return new Token('&', "&");
            case '|':
                if (readch('|'))
                    return Word.or;
                else
                    return new Token('|', "|");
            case '=':
                if (readch('='))
                    return Word.eq;
                else
                    return new Token('=', "=");
            case '!':
                if (readch('='))
                    return Word.ne;
                else
                    return new Token('!', "!");
            case '<':
                if (readch('='))
                    return Word.le;
                else
                    return new Token('<', "<");
            case '>':
                if (readch('='))
                    return Word.ge;
                else
                    return new Token('>', ">");
        }
        //识别数字
        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                readch();
            } while (Character.isDigit(peek));
            return new Integer(v);
//            float x = v;
//            float d = 10;
//            for (; ; ) {
//                readch();
//                if (!Character.isDigit(peek))
//                    break;
//                x = x + Character.digit(peek, 10) / d;
//                d = d * 10;
//            }
//            return new Real(x);
        }
        //识别字符串
        if (Character.isLetter(peek)) {
            StringBuffer buffer = new StringBuffer();
            do {
                buffer.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek));
            String str = buffer.toString();
            Word word = (Word) words.get(str);
            if (word != null)//判断是否为关键字
                return word;
            word = new Word(str, Tag.ID);
            words.put(str, word);
            return word;
        }
        //最后未识别的单字作为一个词法单元返回(ASCII)
        Token tok = new Token(peek, Character.toString(peek));
        peek = ' ';
        return tok;
    }

    public List<Token> getTokens() throws IOException {

        List<Token> list = new LinkedList<>();
        Token token = scan();
        while (token.tag != 65535) {
            list.add(token);
            token = scan();
        }
        return list;
    }
}