package Handler;

public class TokenOperator implements TranslationElement {
    public void translate(Handler h){
        h.handle(this);
    }
}
