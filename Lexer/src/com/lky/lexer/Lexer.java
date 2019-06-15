package com.lky.lexer;

import com.lky.lexer.tag.Tag;
import com.lky.lexer.token.Token;
import com.lky.lexer.token.word.Word;
import com.lky.lexer.token.word.type.Type;

import java.io.IOException;
import java.util.Hashtable;

/**
 * @auther likeyu
 * @create 2019-06-15-11:51
 **/

public class Lexer {

    public static int line = 1;
    char peek = ' ';
    Hashtable words = new Hashtable();

    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    public Lexer() {

        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);

    }

    void readch() throws IOException {
        peek = (char) System.in.read();
    }

    boolean readch(char c) throws IOException {
        readch();
        if (peek != c)
            return false;
        peek = ' ';
        return true;
    }

    public Token scan() throws IOException {
        for (; ; readch()) {
            if (peek == ' ' || peek == '\t')
                continue;
            else if (peek == '\n')
                line++;
            else break;
        }
        switch (peek) {
            case '&':
                if (readch('&'))
                    return Word.and;
                else
                    return new Token(Tag.AND);
            case '|':
                if (readch('|'))
                    return Word.or;
                else
                    return new Token(Tag.OR);
        }


        return null;
    }


}
