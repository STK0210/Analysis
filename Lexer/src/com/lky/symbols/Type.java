package com.lky.symbols;

import com.lky.tag.Tag;
import com.lky.token.word.Word;

/**
 * @auther likeyu
 * @create 2019-06-15-10:52
 **/
public class Type extends Word {

    public int width = 0;//用于存储时分配

    public Type(String s, int tag, int w) {
        super(s, tag);
        width = w;
    }

    public static final Type

            Int = new Type("int", Tag.BASIC, 4),
            Float = new Type("float", Tag.BASIC, 8),
            Char = new Type("char", Tag.BASIC, 1),
            Bool = new Type("bool", Tag.BASIC, 1);

    /**
     * 与boolean类型进行转换
     *
     * @param tp
     * @return true or false
     */
    public static boolean numeric(Type tp) {
        if (tp == Type.Char || tp == Type.Int || tp == Type.Float)
            return true;
        else
            return false;
    }

    /**
     * 数字之间运算时，应用两种类型的max值
     *
     * @param tp1
     * @param tp2
     * @return
     */
    public static Type max(Type tp1, Type tp2) {
        if (!numeric(tp1) || !numeric(tp2))
            return null;
        else if (tp1 == Type.Float || tp2 == Type.Float)
            return Type.Float;
        else if (tp1 == Type.Int || tp2 == Type.Int)
            return Type.Int;
        else
            return Type.Char;
    }
}
