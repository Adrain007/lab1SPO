import Lexer.Lexer;
import Parser.Parser1;
import Lexer.Token;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) throws IOException {
        ArrayList<Token> tokens;
        final String input = new String(Files.readAllBytes(Paths.get("C:/Users/Адриан/IdeaProjects/lab1(SPO)/src", "proga.txt")),"UTF-8");
        Lexer lexer = new Lexer();
        long start = System.nanoTime();
        tokens = lexer.getTokenList(input);
        Parser1 parser = new Parser1(tokens);
        parser.parse();
        long finish = System.nanoTime();
        for(Token token: tokens){
            if(!token.getType().equals("SPACE")) {
                System.out.println((tokens.indexOf(token)) + " - " + token.getType() + " " + token.getValue());
            }
        }
        System.out.println("\n"+"Execution time = "+((finish-start)/Math.pow(10,6))+" ms");
    }
}