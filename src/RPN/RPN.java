package RPN;

import Handler.TokenOperand;
import Handler.TokenOperator;
import Lexer.Lexer;
import Lexer.Token;

import java.util.*;

public class RPN {
    private static Stack<Token> stack = new Stack<>();
    private static List<Token> exit = new ArrayList<>();
    private static Lexer lexer = new Lexer();
    private static String string = "as:=as-sd;";
    private static ArrayList<Token> input;

    public RPN(){

    }

    private void toRPN(){
        for(Token token: input){
            if(token.getType().equals("VAR")||token.getType().equals("DIGIT")){
                exit.add(token);
            }else if(token.getType().equals("OP")){

            }
        }
    }

    private void dsfsd(ArrayList<Token> token){
        List<TokenOperand> tokenOperands = new LinkedList<>();
        List<TokenOperator> tokenOperators = new LinkedList<>();
        String reg = null;
        for (Token token1: token){
            if(token1.getType().equals("VAR")||token1.getType().equals("DIGIT")){
                tokenOperands.add(new TokenOperand(token1.getType(),token1.getValue()));
            }
        }
    }
}
