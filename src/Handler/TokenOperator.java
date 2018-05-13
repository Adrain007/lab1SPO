package Handler;

import Lexer.Token;

public class TokenOperator extends Token implements TranslationElement {
    private int priority;
    private String value;

    public void translate(Handler h){
        h.handle(this);
    }

    public TokenOperator(int priority, String value){
        this.priority = priority;
        this.value = value;
    }
}
