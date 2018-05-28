import Lexer.Lexer;
import MyLinkedList.MyLinkedList;
import Parser.Parser1;
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
        StackMachine stackMachine = new StackMachine(rpn.toRPN());
        stackMachine.calculation();
        MyLinkedList list = new MyLinkedList();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.set(1,67);
        String s = "0";
        list.remove(s);
        list.remove(6);
        System.out.println(list.size()+" "+list.get(0)+" "+list.contains(67));
        long finish = System.nanoTime();
        System.out.println("\n"+"Execution time = "+((finish-start)/Math.pow(10,6))+" ms");
    }
}