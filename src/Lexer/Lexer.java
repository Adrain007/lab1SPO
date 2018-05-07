package Lexer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    static private StringBuffer currentString = new StringBuffer();
    static private StringBuffer acc = new StringBuffer();

    public ArrayList<Token> parse(String string) {
        ArrayList<Token> tokenList = new ArrayList<>();
        while(string.length()!=0) {
            for(LexemType lexemType: LexemType.values()) {
                Pattern pattern = lexemType.pattern;
                Matcher matcher = pattern.matcher(string);
                if(matcher.lookingAt()){
                    currentString.append(matcher.group());
                    tokenList.add(new Token(lexemType.type,currentString.toString()));
                    acc.append(string);
                    acc.delete(0,(currentString.length()));
                    string = acc.toString();
                    currentString.setLength(0);
                    acc.setLength(0);
                    break;
                }
            }
        }
        return tokenList;
    }

}