package Lexer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    static private StringBuilder currentString = new StringBuilder();
    static private StringBuilder acc = new StringBuilder();
    static private int n = 0;
    static private ArrayList<Token> tokenList = new ArrayList<Token>();


    public void parse(String string){
        while(string.length()!=0) {
            for(LexemType lexemType: LexemType.values()) {
                Pattern pattern = lexemType.pattern;
                Matcher matcher = pattern.matcher(string);
                n++;
                if(matcher.lookingAt()){
                    currentString.append(matcher.group());
                    addToken(lexemType,currentString.toString());
                    acc.append(string);
                    acc.delete(0,(currentString.length()));
                    string = acc.toString();
                    currentString.setLength(0);
                    acc.setLength(0);
                    n = 0;
                    break;
                }
                else if(n == LexemType.values().length){
                    throw new RuntimeException("НЕ ТО НАПИСАЛ, ДЕБИК!!!");
                }
            }
        }
    }

    private void addToken(LexemType lexemType,String s){
        tokenList.add(new Token(lexemType.type,s));
    }

    public ArrayList<Token> getTokenList(String s){
        parse(s);
        return tokenList;
    }

}