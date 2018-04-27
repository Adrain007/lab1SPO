# lab1SPO
package lab3;

import java.util.ArrayList;

public class Parser {
	
	static private ArrayList<Token> tokens;
	static private Token currentToken;
	static int i = 0;
	public static void match() {
		currentToken = tokens.get(i);
		i++;
	}
	
	public void parse(ArrayList<Token> tokens) {
		lang();
	}
	
	public void lang() {
		expr();
	}
	public void expr() {
		try {
			VAR();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			ASSIGN_OP();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			DIGIT();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void VAR() throws Exception {
		match();
		if(!currentToken.getType().equals("VAR")) {
			throw new Exception("VAR expected, but "+ currentToken.getType()+" found!!!");
		}
	}
	
	public void ASSIGN_OP() throws Exception {
		match();
		if(!currentToken.getType().equals("ASSIGN_OP")) {
			throw new Exception("ASSIGN_OP expected, but "+ currentToken.getType()+" found!!!");
		}
	}
	public void DIGIT() throws Exception {
		match();
		if(!currentToken.getType().equals("DIGIT")) {
			throw new Exception("DIGIT expected, but "+ currentToken.getType()+" found!!!");
		}
	}
	public void main(String[] args) {

        Lexer lexer = new Lexer();

        String line = "super1=pop";
        
        Parser parser = new Parser();
        
        tokens = lexer.parse(line);
        for(Token token: tokens) {
        	System.out.println(token.getType()+" "+token.getValue());
        }
        
        parser.parse(tokens);

    }
	
}
