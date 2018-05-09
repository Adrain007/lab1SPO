package Parser;
import java.util.ArrayList;
import java.util.Scanner;

import Lexer.*;

public class Parser {
    static private ArrayList<Token> tokens;
    static private Token currentToken;
    static private int i = 0;

    private void match() {
        currentToken = tokens.get(i);
        i++;
    }

    private void proverka(String s) throws Exception{
        if(!currentToken.getType().equals(s)) {
            throw new Exception(s + " expected, but "+ currentToken.getType()+" found!!!");
        }else {
            System.out.println("vse OK!");
        }
    }

    public void parse(ArrayList<Token> token) {
        tokens = token;
        lang();
    }

    private void lang() {
        try {
            while (i < tokens.size()) {
                expr();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void expr() throws Exception {
        assign();
        //cycle();
        //print();
    }

    private void assign() throws Exception{
        try{
            VAR();
            ASSIGN_OP();
            assign_value();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Ne assign!!!");
        }
    }

    private void assign_value(){
        math_expr();
        //OP();
        //math_expr();
    }

    private void math_expr(){
        add_expr();
        //math_br();
    }
    private void add_expr(){
        value();
        currentToken = tokens.get(i);
        while(!currentToken.getType().equals("END")) {
            try {
                OP();
                value();
            } catch (Exception e){
                e.printStackTrace();
            }
            currentToken = tokens.get(i);
        }
    }

    private void value(){
        try{
            VAR();
        } catch (Exception e){
            --i;
            try{
                DIGIT();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    private void math_br(){
        try{
            L_B();
        }catch (Exception e){
            e.printStackTrace();
        }
        math_expr();
        try {
            R_B();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void OP() throws Exception{
        match();
        proverka("OP");
    }

    private void L_B() throws Exception{
        match();
        proverka("L_B");
    }

    private void R_B() throws Exception{
        match();
        proverka("R_B");
    }

    private void VAR() throws Exception {
        match();
        proverka("VAR");
    }

    private void ASSIGN_OP() throws Exception {
        match();
        proverka("ASSIGN_OP");
    }

    private void DIGIT() throws Exception {
        match();
        proverka("DIGIT");
    }

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Parser parser = new Parser();
        try{
            parser.parse(lexer.parse(line));
        } catch (Exception e){
            e.printStackTrace();
        }
        tokens.forEach(token -> System.out.println(token.getType() + " " + token.getValue()));
    }

}