package Handler;

import Lexer.Token;

public class TokenOperand extends Token implements TranslationElement {
    private String type;
    private String value;
    public void translate(Handler h){
        h.handle(this);
    }
    public TokenOperand(String type, String value){
        this.type = type;
        this.value = value;
    }
}
