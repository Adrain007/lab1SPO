package Handler;

public class TokenOperand implements TranslationElement {
    public void translate(Handler h){
        h.handle(this);
    }
}
