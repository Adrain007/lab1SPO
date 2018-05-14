package RPN;

import Handler.TokenOperand;
import Handler.TokenOperator;
import Lexer.Lexer;
import Lexer.Token;

import java.util.*;

public class RPN {
    private static Stack<TokenOperator> stack = new Stack<>();
    private static ArrayList<Token> output = new ArrayList<>();
    private static Lexer lexer = new Lexer();
    private static ArrayList<Token> input = new ArrayList<>();


    public RPN(){

    }

    public ArrayList<Token> toRPN(ArrayList<Token> tokens1){
        input = tokens1;
        for(Token token: input){

            if(token instanceof TokenOperand){
                output.add(token);
            } else if(token instanceof TokenOperator){
                TokenOperator tokenOperator = (TokenOperator) token;
                if(tokenOperator.getType().equals("L_B")){
                    stack.push(tokenOperator);
                }
                else if(tokenOperator.getType().equals("R_B")){
                    while (!stack.peek().getType().equals("L_B")){
                        output.add(stack.pop());
                    }
                    stack.pop();
                }
                else if(!stack.empty() && tokenOperator.getPriority() <= stack.peek().getPriority()){
                    while (tokenOperator.getPriority() <= stack.peek().getPriority()) {
                        output.add(stack.pop());
                    }
                    stack.push(tokenOperator);
                } else {
                    stack.push(tokenOperator);
                }
            } else if(input.size()-1==input.indexOf(token)){
                while (!stack.empty()){
                    output.add(stack.pop());
                }
            }
        }
        return output;
    }
    //a b c d * 1 2 + e - / + asd - as as 78 / - 97 - 23 * 234 / + =

    private void stackPush(TokenOperator tokenOperator){
        if(tokenOperator.getType().equals("L_B")){
            tokenOperator.setPriority(0);
        }
        stack.push(tokenOperator);
    }
}
