package StackMachine;
import Lexer.TokenOperand;
import Lexer.TokenOperator;
import Lexer.Token;
import java.util.*;

public class StackMachine {

    private ArrayList<Token> tokens;
    private Stack<Token> stackMachine = new Stack<>();
    private HashMap<String, Variable> variablesTable  = new HashMap<>();
    private static int i;

    public StackMachine(ArrayList<Token> tokens1){
        this.tokens = tokens1;
    }

    public void calculation(){
        for(i = 0; i<tokens.size()-1;i++){
            if(tokens.get(i).getValue().equals("end point")){
                break;
            }
            else if(tokens.get(i) instanceof TokenOperand){
                stackMachine.push(tokens.get(i));
            }
            else if(tokens.get(i) instanceof TokenOperator){
                String value = tokens.get(i).getValue();
                binaryOp(value);
            }
        }
    }

    private void binaryOp(String value){
        if(value.equals("!F")){
            int buff = Integer.parseInt(stackMachine.pop().getValue());
            if(stackMachine.pop().getValue().equals("0")) {
                i = buff-1;
            }
        }else if(value.equals("!")){
            i = Integer.parseInt(stackMachine.pop().getValue())-1;
        }else if(value.equals("print")){
            printVar(stackMachine.pop().getValue());
        } else if(stackMachine.peek().getType().equals("VAR")){
            Variable a = variablesTable.get(stackMachine.pop().getValue());
            if(stackMachine.peek().getType().equals("VAR")) {
                Variable b = variablesTable.get(stackMachine.pop().getValue());
                float c;
                switch (value) {
                    case "+":
                        c = b.getValue() + a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "-":
                        c = b.getValue() - a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "/":
                        c = b.getValue() / a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "*":
                        c = b.getValue() * a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case ":=":
                        b.setValue(a.getValue());
                        break;
                    case "==":
                        if(b.getValue()==a.getValue()){
                            stackMachine.push(new TokenOperand("DIGIT","1"));
                        }else{
                            stackMachine.push(new TokenOperand("DIGIT","0"));
                        }
                        break;
                    case ">":
                        if(b.getValue()>a.getValue()){
                            stackMachine.push(new TokenOperand("DIGIT","1"));
                        }else{
                            stackMachine.push(new TokenOperand("DIGIT","0"));
                        }
                        break;
                    case "<":
                        if(b.getValue()<a.getValue()){
                            stackMachine.push(new TokenOperand("DIGIT","1"));
                        }else{
                            stackMachine.push(new TokenOperand("DIGIT","0"));
                        }
                        break;
                    default:
                        break;
                }
            }
            else if(stackMachine.peek().getType().equals("DIGIT")){
                float c;
                float b = Float.parseFloat(stackMachine.pop().getValue());
                switch (value) {
                    case "+":
                        c = b + a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "-":
                        c = b - a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "/":
                        c = b / a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "*":
                        c = b * a.getValue();
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    default:
                        break;
                }
            }
        } else if(stackMachine.peek().getType().equals("DIGIT")) {
            float a = Float.parseFloat(stackMachine.pop().getValue());
            if(stackMachine.peek().getType().equals("VAR")) {
                Variable b = variablesTable.get(stackMachine.pop().getValue());
                float c;
                switch (value) {
                    case "+":
                        c = b.getValue()+a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "-":
                        c = b.getValue()-a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "/":
                        c = b.getValue()/a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "*":
                        c = b.getValue()*a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case ":=":
                        b.setValue(a);
                        break;
                    case "==":
                        if(b.getValue()==a){
                            stackMachine.push(new TokenOperand("DIGIT","1"));
                        }else{
                            stackMachine.push(new TokenOperand("DIGIT","0"));
                        }
                        break;
                    case ">":
                        if(b.getValue()>a){
                            stackMachine.push(new TokenOperand("DIGIT","1"));
                        }else{
                            stackMachine.push(new TokenOperand("DIGIT","0"));
                        }
                        break;
                    case "<":
                        if(b.getValue()<a){
                            stackMachine.push(new TokenOperand("DIGIT","1"));
                        }else{
                            stackMachine.push(new TokenOperand("DIGIT","0"));
                        }
                        break;
                    default:
                        break;
                }
            }
            else if(stackMachine.peek().getType().equals("DIGIT")){
                float c;
                float b = Float.parseFloat(stackMachine.pop().getValue());
                switch (value) {
                    case "+":
                        c = b + a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "-":
                        c = b - a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "/":
                        c = b / a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
                        break;
                    case "*":
                        c = b * a;
                        stackMachine.push(new TokenOperand("DIGIT", Float.toString(c)));
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
    private void printVar(String string){
        System.out.println(variablesTable.get(string).getType()+ " " +string + " := " + variablesTable.get(string).getValue());
    }
}