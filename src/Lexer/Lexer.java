package Lexer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    static private StringBuilder currentString = new StringBuilder();
    static private StringBuilder acc = new StringBuilder();
    static private int n = 0;
    static private ArrayList<Token> tokenList = new ArrayList<>();

    private void match(String string){
        while(string.length()!=0) {
            for(LexemType lexemType: LexemType.values()) {
                Pattern pattern = lexemType.getPattern();
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

    private void addToken(LexemType lexemType,String value){
        String type = lexemType.getType();
        switch (type) {
            case "DIGIT":
            case "VAR":
            case "TYPE":
                tokenList.add(new TokenOperand(type, value));
                break;
            case "SPACE":
                break;
            case "END":
            case "COMA":
            case "CYCLE":
            case "L_F_B":
            case "R_F_B":
                tokenList.add(new Token(type, value));
                break;
            default:
                tokenList.add(new TokenOperator(lexemType.getPriority(),type,value));
                break;
        }
    }

    public ArrayList<Token> getTokenList(String s){
        match(s);
        return tokenList;
    }
}