package StackMachine;

import Lexer.TokenOperand;
import Lexer.TokenOperator;
import Lexer.Token;
import MyLinkedList.MyLinkedList;

import java.util.*;

public class StackMachine {
    private ArrayList<Token> tokens;
    private Stack<Token> stackMachine = new Stack<>();
    private HashMap<String, VarList> variablesTable = new HashMap<>();
    private static int i;
    private static Variable assignTo;
    private static MyLinkedList list;

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
                if (tokens.get(i).getType().equals("LIST_METHOD")) {
                    methodOp(value);
                } else {
                    binaryOp(value);
                }
            }
        }
    }


    private float getOperand() {
        if (stackMachine.peek().getType().equals("VAR")) {
            String var = stackMachine.pop().getValue();
            try {
                if (variablesTable.get(var) instanceof Variable) {
                    assignTo = (Variable) variablesTable.get(var);
                    return assignTo.getValue();
                }
            } catch (NullPointerException e) {
                throw new NullPointerException("Variable '" + var + "' is not initialized!!!");
            }
        } else {
            return Float.parseFloat(stackMachine.pop().getValue());
        }
        return 234;
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
                if(variablesTable.get(stackMachine.peek().getValue()) instanceof Variable) {
                    printVar(stackMachine.pop().getValue());
                }else if(variablesTable.get(stackMachine.peek().getValue()) instanceof MyLinkedList) {
                    String varName = stackMachine.pop().getValue();
                    list =(MyLinkedList) variablesTable.get(varName);
                    printList(list,varName);
                }
                break;
            case "type": {
                String buff = stackMachine.pop().getValue();
                if (buff.equals("String")) {
                    variablesTable.put(stackMachine.pop().getValue(), new Variable(0, buff));
                } else if (buff.equals("List")) {
                    variablesTable.put(stackMachine.pop().getValue(), new MyLinkedList());
                }
                break;
            }
            default:
                switchOp(getOperand(), getOperand(), value);
                break;
        }
    }

    private void printVar(String varName) {
        try {
            if (variablesTable.get(varName) instanceof Variable) {
                Variable variable = (Variable) variablesTable.get(varName);
                System.out.println(variable.getType() + " " + varName + " := " + variable.getValue());
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Variable '" + varName + "' is not initialized!!!");
        }
    }

    private void printList(MyLinkedList list, String varName) {
        System.out.print("Values of "+varName+": ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("[" + list.get(i)+"]");
        }

    }

    private void methodOp(String value) {
        float arg2 = getOperand();
        int arg1 = 0;
        if (value.equals(".set")) {
            arg1 = Integer.parseInt(stackMachine.pop().getValue());
        }
        if (variablesTable.get(stackMachine.peek().getValue()) instanceof MyLinkedList) {
            list = (MyLinkedList) variablesTable.get(stackMachine.pop().getValue());
        }
        switch (value) {
            case ".add": {
                list.add(arg2);
                break;
            }
            case ".remove":
                list.remove((int)arg2);
                break;
            case ".contains":
                list.contains(arg2);
                break;
            case ".get": {
                TokenOperand token = new TokenOperand("DIGIT", String.valueOf(list.get((int) arg2)));
                stackMachine.push(token);
                break;
            }
            case ".set":
                list.set(arg1, arg2);
                break;
            default:
                break;
        }
    }
}