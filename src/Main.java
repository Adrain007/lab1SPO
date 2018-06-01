import Lexer.Lexer;

import Parser.*;
import Lexer.Token;
import RPN.RPN;
import StackMachine.StackMachine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        ArrayList<Token> tokens;
        final String input = new String(Files.readAllBytes(Paths.get("C:/Users/Адриан/IdeaProjects/lab1(SPO)/src", "proga.txt")),"UTF-8");
        Lexer lexer = new Lexer();
        tokens = lexer.getTokenList(input);
        Parser1 parser = new Parser1(tokens);
        parser.parse();
        RPN rpn = new RPN(tokens);
        rpn.toRPN();
        StackMachine stackMachine = new StackMachine(rpn.getOutput());
        stackMachine.calculation();
        long finish = System.nanoTime();
        System.out.println("\n\n"+"Execution time = "+((finish-start)/Math.pow(10,6))+" ms");
    }
}