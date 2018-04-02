import java.util.regex.Pattern;

public enum LexemType {
    DIGIT("DIGIT", Pattern.compile("(0|[1-9]{1}[0-9]*)")),
    VAR("VAR", Pattern.compile("([a-zA-Z]+[0-9]*)")),
    COMP_OP("COMP_OP",Pattern.compile("(\\==|\\<|\\>)")),
    ASSIGN_OP("ASSIGN_OP", Pattern.compile("^(=)")),
    OP("OP", Pattern.compile("(\\+|\\-|\\*|\\/)")),
    SPACE("SPACE",Pattern.compile("(\\s)"));
    public String type;
    public Pattern pattern;
    LexemType(String type, Pattern pattern){
        this.type = type;
        this.pattern = pattern;
    }
}
