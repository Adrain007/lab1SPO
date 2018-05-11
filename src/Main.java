import Lexer.Lexer;
import Parser.Parser1;
import Lexer.Token;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Token> tokens;

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try {
            tokens = lexer.parse(line);
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        Parser1 parser = new Parser1(tokens);
        parser.parse();
        tokens.forEach(token -> System.out.println((tokens.indexOf(token)+1)+" - "+token.getType() + " " + token.getValue()));
    }
}
