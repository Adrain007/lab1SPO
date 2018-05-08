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

    private void parse() {
        lang();
    }

    private void lang() {
        while (i<tokens.size()){
            expr();
        }

    }

    private void expr() {
        try {
            VAR();
            ASSIGN_OP();
            value();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void value(){
        try{
            VAR();
        } catch(Exeption e){
            try{
                DIGIT();
            }
        }
    }

    private void VAR() throws Exception {
        match();
        if(!currentToken.getType().equals("VAR")) {
            throw new Exception("VAR expected, but "+ currentToken.getType()+" found!!!");
        }else {
            System.out.println("vse OK!");
        }
    }

    private void ASSIGN_OP() throws Exception {
        match();
        if(!currentToken.getType().equals("ASSIGN_OP")) {
            throw new Exception("ASSIGN_OP expected, but "+ currentToken.getType()+" found!!!");
        }else {
            System.out.println("vse OK!");
        }
    }

    private void DIGIT() throws Exception {
        match();
        if(!currentToken.getType().equals("DIGIT")) {
            throw new Exception("DIGIT expected, but "+ currentToken.getType()+" found!!!");
        }else {
            System.out.println("vse OK!");
        }
    }

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Parser parser = new Parser();
        tokens = lexer.parse(line);
        for(Token token: tokens) {
            System.out.println(token.getType()+" "+token.getValue());
        }
        parser.parse();
    }
}
