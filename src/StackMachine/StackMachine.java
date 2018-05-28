package StackMachine;

import Lexer.TokenOperand;
import Lexer.TokenOperator;
import Lexer.Token;

import java.util.*;

public class StackMachine {
    private ArrayList<Token> tokens;
    private Stack<Token> stackMachine = new Stack<>();
    private HashMap<String, Variable> variablesTable = new HashMap<>();
    private static int i;
    private static Variable assignTo;

    public StackMachine(ArrayList<Token> tokens1) {
        this.tokens = tokens1;
    }

    public void calculation() {
        for (i = 0; i < tokens.size() - 1; i++) {
            if (tokens.get(i).getValue().equals("end point")) {
                break;
            } else if (tokens.get(i) instanceof TokenOperand) {
                stackMachine.push(tokens.get(i));
            } else if (tokens.get(i) instanceof TokenOperator) {
                String value = tokens.get(i).getValue();
                binaryOp(value);
            }
        }
    }

    private float getOperand() {
        if (stackMachine.peek().getType().equals("VAR")) {
            String var;
            var = stackMachine.pop().getValue();
            try {
                assignTo = variablesTable.get(var);
                return assignTo.getValue();
            } catch (NullPointerException e){
                throw new NullPointerException("Variable '"+var+"' is not initialized!!!");
            }
        } else {
            return Float.parseFloat(stackMachine.pop().getValue());
        }
    }

    private void switchOp(float arg2, float arg1, String value) {
        float result;
        switch (value) {
            case "+":
                result = arg1 + arg2;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case "-":
                result = arg1 - arg2;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case "/":
                result = arg1 / arg2;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case "*":
                result = arg1 * arg2;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case ":=":
                assignTo.setValue(arg2);
                break;
            case "==":
                if (arg1 == arg2) {
                    stackMachine.push(new TokenOperand("DIGIT", "1"));
                } else {
                    stackMachine.push(new TokenOperand("DIGIT", "0"));
                }
                break;
            case ">":
                if (arg1 > arg2) {
                    stackMachine.push(new TokenOperand("DIGIT", "1"));
                } else {
                    stackMachine.push(new TokenOperand("DIGIT", "0"));
                }
                break;
            case "<":
                if (arg1 < arg2) {
                    stackMachine.push(new TokenOperand("DIGIT", "1"));
                } else {
                    stackMachine.push(new TokenOperand("DIGIT", "0"));
                }
                break;
            default:
                break;
        }
    }
    private void binaryOp(String value) {
        switch (value) {
            case "!F": {
                int buff = Integer.parseInt(stackMachine.pop().getValue());
                if (stackMachine.pop().getValue().equals("0")) {
                    i = buff - 1;
                }
                break;
            }
            case "!":
                i = Integer.parseInt(stackMachine.pop().getValue()) - 1;
                break;
            case "print":
                printVar(stackMachine.pop().getValue());
                break;
            case "type": {
                String buff = stackMachine.pop().getValue();
                variablesTable.put(stackMachine.pop().getValue(), new Variable(0, buff));
                break;
            }
            default:
                switchOp(getOperand(), getOperand(), value);
                break;
        }
    }
    private void printVar(String varName) {
        try {
            System.out.println(variablesTable.get(varName).getType() + " " + varName + " := " + variablesTable.get(varName).getValue());
        } catch (NullPointerException e){
            throw new NullPointerException("Variable '"+varName+"' is not initialized!!!");
        }
    }
}