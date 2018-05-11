package Lexer;
public class Token {

    private LexemType type;
    private String value;

    Token(LexemType type, String value){
        this.type = type;
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public LexemType getType(){
        return type;
    }
}
