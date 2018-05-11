package Parser;

import Lexer.Token;

import java.util.ArrayList;

public class Parser1 {

    private static final Token EOF = new Token("EOF","");

    private final ArrayList<Token> tokens;

    private int pos;

    private final int size ;

    public Parser1(ArrayList<Token> token) {
        this.tokens = token;
        size = tokens.size();
    }

    private Token get(int relative){
        final int position = pos + relative;
        if (position >= size) return EOF;
        return tokens.get(position);
    }

    private boolean match(String type){
        final Token current = get(0);
        if(!(type.equals(current.getType()))) {
            return false;
        }
        pos++;
        return true;
    }

    public void parse(){
        lang();
    }

    private void lang() {
        while (get(0)!=EOF){
            expr();
        }
    }

    private void expr() {
        try {
            assign();
            if(!match("END")){
                throw new Exception("SOSIPISOS-5!!!");
            }
        }catch (RuntimeException e){
            cycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //print();
    }

    private void assign() throws RuntimeException {
        match("VAR");
        if(match("ASSIGN_OP")) {
            assign_value();
        } else {
            throw new RuntimeException("SOSIPISOS-4!!!");
        }
    }

    private void assign_value() {
        math_expr();
        while (true) {
            if(match("OP")) {
                math_expr();
            }
            else {
                break;
            }
        }

    }

    private void math_expr() {
        try{
            add_expr();
        } catch (RuntimeException e){
            math_br();
        }
    }

    private void add_expr() throws RuntimeException {
        value();
        while(true) {
            if(match("OP")){
                value();
            }else {
                break;
            }
        }
    }
    private void math_br() {
        while(true) {
            if(match("L_B")) {
                assign_value();
            }
            if(match("R_B")){
                break;
            }else {
                throw new RuntimeException("SOSIPISOS-1!!!");
            }
        }
    }
    private boolean value() throws RuntimeException{
        if(match("DIGIT")) {
            return true;
        }else if (match("VAR")){
            return true;
        } else {
            throw new RuntimeException("SOSIPISOS-2!!!");
        }
    }

    private void cycle(){
        if(match("CYCLE")) {
            compare();
            body();
        } else {
            throw new RuntimeException("SOSIPISOS-3!!!");
        }
    }

    private void compare() {
        match("L_B");
        match("VAR");
        match("COMP_OP");
        value();
        match("R_B");
    }

    private void body(){
        if(match("L_F_B")) {
            while (!get(0).getType().equals("R_F_B")) {
                expr();
            }
        }
        match("R_F_B");
    }
}
