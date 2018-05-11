import Lexer.Lexer;
import Parser.Parser;
import Lexer.Token;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Token> tokens;

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Parser parser = new Parser();
        try {
            tokens = lexer.parse(line);
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        parser.parse(tokens);
        tokens.forEach(token -> System.out.println((tokens.indexOf(token)+1)+" - "+token.getType().type + " " + token.getValue()));
    }
}
