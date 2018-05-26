package Handler;

import Lexer.Token;

public class TokenOperand extends Token {



    public TokenOperand(String type, String value){
        super(type,value);
    }
    @Override
    public String toString(){
        return this.getValue();
    }
}