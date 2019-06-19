package com.lky.parser;

import com.lky.inter.Node;
import com.lky.lexer.Lexer;
import com.lky.tag.Tag;
import com.lky.token.Token;

import java.io.IOException;
import java.util.List;

/**
 * @auther likeyu
 * @create 2019-06-17-18:24
 **/

public class Parser {

    private Lexer lex;
    private List<Token> tokens;
    int index = 0;

    private Token look;

    public Node root;

    public Parser(Lexer lex) throws IOException {
        this.lex = lex;
        this.tokens = lex.getTokens();
        move();
    }

    void move() {
        if (index < tokens.size()) {
            look = tokens.get(index++);
        }
    }

    void error(String err) {
        throw new Error("near line " + lex.line + ": " + err);
    }

    Node match(int tag) {
        if (look.tag == tag) {
            String nodeName = look.lexeme;
            move();
            return new Node(nodeName);
        } else {
            error("syntax error");
            return null;
        }
    }

    //<editor-fold desc="总程序">
    public void Program() {//Program ::= ProgramHead DeclarePart ProgramBody .
        root = new Node("Program", ProgramHead(), DeclarePart(), ProgramBody(), match('.'));
    }
    //</editor-fold>

    //<editor-fold desc="程序头">
    private Node ProgramHead() {
        return new Node("ProgramHead", match(Tag.PROGRAM), ProgramName());
    }

    private Node ProgramName() {
        return new Node("ProgramName", match(Tag.ID));
    }
    //</editor-fold>

    //<editor-fold desc="程序声明">
    private Node DeclarePart() {
        return new Node("DeclarePart", TypeDecpart(), VarDecpart(), ProcDecpart());
    }
    //</editor-fold>

    //<editor-fold desc="类型声明">
    private Node TypeDecpart() {
        if (look.tag == Tag.TYPE) {
            return new Node("TypeDecpart", TypeDec());
        } else {
            return new Node("TypeDecpart");
        }
    }

    private Node TypeDec() {
        return new Node("TypeDec", match(Tag.TYPE), TypeDecList());
    }

    private Node TypeDecList() {
        return new Node("TypeDecList", TypeId(), match('='), TypeDef(), match(';'), TypeDecMore());
    }

    private Node TypeDecMore() {
        if (look.tag == Tag.ID) {
            return new Node("TypeDecMore", TypeDecList());
        } else {
            return new Node("TypeDecMore");
        }
    }

    private Node TypeId() {
        return new Node("TypeId", match(Tag.ID));
    }
    //</editor-fold>

    //<editor-fold desc="类型">

    private Node TypeDef() {
        if (look.tag == Tag.INTEGER || look.tag == Tag.CHAR) {
            return new Node("TypeDef", BaseType());
        } else if (look.tag == Tag.ARRAY || look.tag == Tag.RECORD)
            return new Node("TypeDef", StructureType());
        else {
            return new Node("TypeDef", match(Tag.ID));
        }
    }

    private Node BaseType() {
        if (look.tag == Tag.INTEGER)
            return new Node("BaseType", match(Tag.INTEGER));
        else
            return new Node("BaseType", match(Tag.CHAR));
    }

    private Node StructureType() {
        if (look.tag == Tag.ARRAY)
            return new Node("StructureType", ArrayType());
        else
            return new Node("StructureType", RecType());
    }

    private Node ArrayType() {
        return new Node("ArrayType", match(Tag.ARRAY), match('['), Low(), match(Tag.INDEX), Top(), match(']'), match(Tag.OF), BaseType());
    }

    private Node Low() {
        return new Node("Low", match(Tag.INTEGER));
    }

    private Node Top() {
        return new Node("Top", match(Tag.INTEGER));
    }

    public Node RecType() {
        return new Node("RecType", match(Tag.RECORD), FieldDecList(), match(Tag.END));
    }

    public Node FieldDecList() {
        if (look.tag == Tag.INTEGER || look.tag == Tag.CHAR) {
            return new Node("FiledDecList", BaseType(), IdList(), match(';'), FiledDecMore());
        } else {
            return new Node("FiledDecList", ArrayType(), IdList(), match(';'), FiledDecMore());
        }
    }

    public Node FiledDecMore() {
        if (look.tag == Tag.INTEGER || look.tag == Tag.CHAR || look.tag == Tag.ARRAY)
            return new Node("FiledDecMore", FieldDecList());
        else {
            return new Node("FiledDecMore");
        }
    }

    public Node IdList() {
        return new Node("IdList", match(Tag.ID), IdMore());
    }

    public Node IdMore() {
        if (look.tag == ',')
            return new Node("IdMore", match(','), IdList());
        else {
            return new Node("IdMore");
        }
    }
    //</editor-fold>

    //<editor-fold desc="变量声明">
    private Node VarDecpart() {
        if (look.tag == Tag.VAR)
            return new Node("VarDecpart", VarDec());
        else {
            return new Node("VarDecpart");
        }
    }

    private Node VarDec() {
        return new Node("VarDec", match(Tag.VAR), VarDecList());
    }

    private Node VarDecList() {
        return new Node("VarDecList", TypeDef(), VarIdList(), match(';'), VarDecMore());
    }

    private Node VarDecMore() {
        if (look.tag == Tag.INTEGER || look.tag == Tag.CHAR || look.tag == Tag.ARRAY || look.tag == Tag.RECORD || look.tag == Tag.ID)
            return new Node("VarDecMore", VarDecList());
        else {
            return new Node("VarDecMore");
        }
    }

    private Node VarIdList() {
        return new Node("VarIdList", match(Tag.ID), VarIdMore());
    }

    private Node VarIdMore() {
        if (look.tag == ',')
            return new Node("VarIdMore", match(','), VarIdList());
        else {
            return new Node("VarIdMore");
        }
    }
    //</editor-fold>

    //<editor-fold desc="过程声明">
    private Node ProcDecpart() {
        if (look.tag == Tag.PROCEDURE)
            return new Node("ProcDecpart", ProcDec());
        else {
            return new Node("Prodecpart");
        }
    }

    private Node ProcDec() {
        return new Node("ProcDec", match(Tag.PROCEDURE), ProcName(), match('('), ParamList(), match(')'), match(';'), ProcDecPart(), ProcBody(), ProcDecMore());
    }

    private Node ProcDecMore() {
        if (look.tag == Tag.PROCEDURE)
            return new Node("ProcDecMore", ProcDec());
        else {
            return new Node("ProcDecMore");
        }
    }

    private Node ProcName() {
        return new Node("ProcName", match(Tag.ID));
    }
    //</editor-fold>

    //<editor-fold desc="参数声明">
    private Node ParamList() {
        if (look.tag == Tag.VAR || look.tag == Tag.ID || look.tag == Tag.INTEGER || look.tag == Tag.CHAR || look.tag == Tag.ARRAY || look.tag == Tag.RECORD)
            return new Node("ParamList", ParamDecList());
        else {
            return new Node("ParamList");
        }
    }

    private Node ParamDecList() {
        return new Node("ParamDecList", Param(), ParamMore());
    }

    private Node ParamMore() {
        if (look.tag == ';')
            return new Node("ParamMore", match(';'), ParamDecList());
        else {
            return new Node("ParamMore");
        }
    }

    private Node Param() {
        if (look.tag == Tag.VAR)
            return new Node("Param", match(Tag.VAR), TypeDef(), FormList());
        else {
            return new Node("Patam", TypeDef(), FormList());
        }
    }

    private Node FormList() {
        return new Node("FormList", match(Tag.ID), FidMore());
    }

    private Node FidMore() {
        if (look.tag == ',')
            return new Node("FidMore", match(','), FormList());
        else {
            return new Node("FidMore");
        }
    }
    //</editor-fold>

    //<editor-fold desc="过程中的声明部分">
    private Node ProcDecPart() {
        return new Node("ProcDecPart", DeclarePart());
    }
    //</editor-fold>

    //<editor-fold desc="过程体">
    private Node ProcBody() {
        return new Node("ProcBody", ProgramBody());
    }
    //</editor-fold>

    //<editor-fold desc="主程序体">
    private Node ProgramBody() {
        return new Node("ProgramBody", match(Tag.BEGIN), StmList(), match(Tag.END));
    }
    //</editor-fold>

    //<editor-fold desc="语句序列">
    private Node StmList() {
        return new Node("StmList", Stm(), StmMore());
    }

    private Node StmMore() {
        if (look.tag == ';')
            return new Node("StmMore", match(';'), StmList());
        else {
            return new Node("StmMore");
        }
    }
    //</editor-fold>

    //<editor-fold desc="语句">
    private Node Stm() {
        if (look.tag == Tag.IF)
            return new Node("Stm", ConditionalStm());
        else if (look.tag == Tag.WHILE)
            return new Node("Stm", LoopStm());
        else if (look.tag == Tag.READ)
            return new Node("Stm", InputStm());
        else if (look.tag == Tag.WRITE)
            return new Node("Stm", OutputStm());
        else if (look.tag == Tag.RETURN)
            return new Node("Stm", ReturnStm());
        else
            return new Node("Stm", match(Tag.ID), AssCall());
    }

    private Node AssCall() {
        if (look.tag == '(')
            return new Node("AssCall", CallStmRest());
        else {
            return new Node("AssCall", AssignmentRest());
        }
    }
    //</editor-fold>

    //<editor-fold desc="赋值">
    private Node AssignmentRest() {
        return new Node("AssignmentRest", VariMore(), match(Tag.ASSIGN), Exp());
    }
    //</editor-fold>

    //<editor-fold desc="条件">
    private Node ConditionalStm() {
        return new Node("ConditionalStm", match(Tag.IF), RelExp(), match(Tag.THEN), StmList(), match(Tag.ELSE), StmList(), match(Tag.FI));
    }
    //</editor-fold>

    //<editor-fold desc="循环">
    private Node LoopStm() {
        return new Node("LoopStm", match(Tag.WHILE), RelExp(), match(Tag.DO), StmList(), match(Tag.ENDWH));
    }
    //</editor-fold>

    //<editor-fold desc="输入">
    private Node InputStm() {
        return new Node("InputStm", match(Tag.READ), match('('), Invar(), match(')'));
    }

    private Node OutputStm() {
        return new Node("OutputStm", match(Tag.WRITE), match('('), Exp(), match(')'));
    }

    //</editor-fold>

    //<editor-fold desc="输出">
    private Node Invar() {
        return new Node("Invar", match(Tag.ID));
    }
    //</editor-fold>

    //<editor-fold desc="返回">
    private Node ReturnStm() {
        return new Node("ReturnStm", match(Tag.RETURN));
    }
    //</editor-fold>

    //<editor-fold desc="过程调用">
    private Node CallStmRest() {
        return new Node("CallStmRest", match('('), ActParamList(), match(')'));
    }

    private Node ActParamList() {
        if (look.tag == '(' || look.tag == Tag.INTEGER || look.tag == Tag.ID)
            return new Node("ActParamList", Exp(), ActParamMore());
        else {
            return new Node("ActParamList");
        }
    }

    private Node ActParamMore() {
        if (look.tag == ',')
            return new Node("ActParamMore", match(','), ActParamList());
        else {
            return new Node("ActParamMore");
        }
    }
    //</editor-fold>

    //<editor-fold desc="条件表达式">
    private Node RelExp() {
        return new Node("RelExp", Exp(), OtherRelE());
    }

    private Node OtherRelE() {
        return new Node("OtherRelE", CmpOp(), Exp());
    }
    //</editor-fold>

    //<editor-fold desc="算术表达式">
    private Node Exp() {
        return new Node("Exp", Term(), OtherTerm());
    }

    private Node OtherTerm() {
        if (look.tag == '+' || look.tag == '-')
            return new Node("OtherTerm", AddOp(), Exp());
        else {
            return new Node("OtherTerm");
        }
    }
    //</editor-fold>

    //<editor-fold desc="项">
    private Node Term() {
        return new Node("Term", Factor(), OtherFactor());
    }

    private Node OtherFactor() {
        if (look.tag == '*' || look.tag == '/')
            return new Node("OtherFactor", MultOp(), Term());
        else
            return new Node("OtherFactor");
    }
    //</editor-fold>

    //<editor-fold desc="因子">
    private Node Factor() {
        if (look.tag == '(')
            return new Node("Factor", match('('), Exp(), match(')'));
        else if (look.tag == Tag.INTEGER) {
            return new Node("Factor", match(Tag.INTEGER));
        } else {
            return new Node("Factor", Variable());
        }
    }

    private Node Variable() {
        return new Node("Variable", match(Tag.ID), VariMore());
    }

    private Node VariMore() {
        if (look.tag == '[')
            return new Node("VariMore", match('['), Exp(), match(']'));
        else if (look.tag == '.') {
            return new Node("VariMore", match('.'), FieldVar());
        } else {
            return new Node("VariMore");
        }
    }

    private Node FieldVar() {
        return new Node("FieldVar", match(Tag.ID), FieldVarMore());
    }

    private Node FieldVarMore() {
        if (look.tag == '[')
            return new Node("FieldVarMore", match('['), Exp(), match(']'));
        else {
            return new Node("FieldVarMore");
        }
    }

    private Node CmpOp() {
        if (look.tag == '<')
            return new Node("CmpOp", match('<'));
        else {
            return new Node("CmpOp", match('='));
        }
    }

    private Node AddOp() {
        if (look.tag == '+')
            return new Node("AddOp", match('+'));
        else {
            return new Node("AddOp", match('-'));
        }
    }

    private Node MultOp() {
        if (look.tag == '*')
            return new Node("MultOp", match('*'));
        else {
            return new Node("MultOp", match('/'));
        }
    }
    //</editor-fold>
}
