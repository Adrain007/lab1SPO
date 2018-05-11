package Lexer;

import java.util.regex.Pattern;

public enum LexemType {
    CYCLE("CYCLE",Pattern.compile("^while|if")),
    PRINT("PRINT",Pattern.compile("^print")),
    DIGIT("DIGIT", Pattern.compile("^(0|[1-9][0-9]*)")),
    VAR("VAR", Pattern.compile("^([a-zA-Z]+[0-9]*[a-zA-Z]*)")),
    COMP_OP("COMP_OP",Pattern.compile("^(==|<|>)")),
    ASSIGN_OP("ASSIGN_OP", Pattern.compile("^(=)")),
    OP("OP", Pattern.compile("^\\+|-|\\*|/")),
    SPACE("SPACE",Pattern.compile("^\\s")),
    L_B("L_B",Pattern.compile("^[(]")),
    R_B("R_B",Pattern.compile("^[)]")),
    L_F_B("L_F_B",Pattern.compile("^[{]")),
    R_F_B("R_F_B",Pattern.compile("^[}]")),
    END("END",Pattern.compile("^[;]")),
    EOF("EOF",Pattern.compile("^"));

    public String type;
    public Pattern pattern;

    LexemType(String type, Pattern pattern){
        this.type = type;
        this.pattern = pattern;
    }
    public String getType(){
        return type;
    }
}
