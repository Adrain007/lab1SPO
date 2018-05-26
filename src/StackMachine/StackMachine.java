package StackMachine;
import Handler.TokenOperand;
import Handler.TokenOperator;
import Lexer.Token;
import java.util.*;

public class StackMachine {

    private ArrayList<Token> tokens;
    private Stack<Token> stackMachine = new Stack<>();
    private HashMap<String, Variable> variablesTable  = new HashMap<>();

    public StackMachine(ArrayList<Token> tokens1){
        this.tokens = tokens1;
    }

    public void calculation(){
        for(Token token: tokens){
            if(token.getValue().equals("end point")){
                break;
            }
            else if(token instanceof TokenOperand){
                stackMachine.push(token);
            }
            else if(token instanceof TokenOperator){
                String value = token.getValue();
                binaryOp(value);
            }
        }
    }

    private void binaryOp(String value){
        if(stackMachine.peek().getType().equals("VAR")){
            Variable a = variablesTable.get(stackMachine.pop().getValue());
            if(stackMachine.peek().getType().equals("VAR")) {
                Variable b = variablesTable.get(stackMachine.pop().getValue());
                int c;
                switch (value) {
                    case "+":
                        c = b.getValue() + a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "-":
                        c = b.getValue() - a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "/":
                        c = b.getValue() / a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "*":
                        c = b.getValue() * a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case ":=":
                        b.setValue(a.getValue());
                    default:
                        break;
                }
            }
            else if(stackMachine.peek().getType().equals("DIGIT")){
                int c;
                int b = Integer.parseInt(stackMachine.pop().getValue());
                switch (value) {
                    case "+":
                        c = b + a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "-":
                        c = b - a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "/":
                        c = b / a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "*":
                        c = b * a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    default:
                        break;
                }
            }
        } else if(stackMachine.peek().getType().equals("DIGIT")) {
            int a = Integer.parseInt(stackMachine.pop().getValue());
            if(stackMachine.peek().getType().equals("VAR")) {
                Variable b = variablesTable.get(stackMachine.pop().getValue());
                int c;
                switch (value) {
                    case "+":
                        c = b.getValue()+a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "-":
                        c = b.getValue()-a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "/":
                        c = b.getValue()/a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "*":
                        c = b.getValue()*a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case ":=":
                        b.setValue(a);
                        break;
                    default:
                        break;
                }
            }
            else if(stackMachine.peek().getType().equals("DIGIT")){
                int c;
                int b = Integer.parseInt(stackMachine.pop().getValue());
                switch (value) {
                    case "+":
                        c = b + a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "-":
                        c = b - a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "/":
                        c = b / a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    case "*":
                        c = b * a;
                        stackMachine.push(new TokenOperand("DIGIT", Integer.toString(c)));
                        break;
                    default:
                        break;
                }
            }
        }else if(value.equals("type")){
            String buff = stackMachine.pop().getValue();
            variablesTable.put(stackMachine.pop().getValue(),new Variable(0,buff));
        }
    }
    public void printVar(){
        for(String string: variablesTable.keySet()){
            System.out.println(variablesTable.get(string).getType()+ " " +string + " := " + variablesTable.get(string).getValue());
        }
    }
}
