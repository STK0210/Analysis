package com.lky.token.word;

import com.lky.tag.Tag;
import com.lky.token.Token;

/**
 * @auther likeyu
 * @create 2019-06-15-10:32
 * <p>
 * 管理保留字、标识符和类似&&这样复合词法单元的词素
 **/

public class Word extends Token {

    public String lexeme = "";

    public Word(String s, int tag) {
        super(tag);
        lexeme = s;
    }

    @Override
    public String toString() {
        return "Word{" +
                "lexeme='" + lexeme + '\'' +
                '}';
    }

    public static final Word
            and = new Word("&&", Tag.AND),
            or = new Word("||", Tag.OR),
            eq = new Word("==", Tag.EQ),
            ne = new Word("!=", Tag.NE),
            le = new Word("<=", Tag.LE),
            ge = new Word(">=", Tag.GE),
            True = new Word("true", Tag.TRUE),
            False = new Word("false", Tag.FALSE),
            temp = new Word("temp", Tag.TEMP);
}
