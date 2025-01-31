package analise;

import analise.tokens.Num;
import analise.tokens.Real;
import analise.tokens.Token;
import analise.tokens.Word;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class Lexer {
    public int line = 1;
    public char peek = ' ';
    HashMap<String, Tags> idTable = new HashMap<>();

    public Lexer() {
        idTable.put("if" ,Tags.IF);
        idTable.put("else", Tags.ELSE);
        idTable.put("while", Tags.WHILE);
        idTable.put("do", Tags.DO);
        idTable.put("break", Tags.BREAK);
        idTable.put("true", Tags.TRUE);
        idTable.put("false", Tags.FALSE);
        idTable.put("&&", Tags.AND);
        idTable.put("||", Tags.OR);
        idTable.put("==", Tags.EQ);
        idTable.put("!=", Tags.NE);
        idTable.put(">=", Tags.GE);
        idTable.put("<=", Tags.LE);
    }

    public void readch() throws IOException {
        peek = (char) System.in.read();
    }

    boolean readch(char c) throws IOException {
        readch();
        if(peek != c){
            return false;
        }
        peek = ' ';
        return true;
    }

    public Token scan() throws IOException {
        while(Character.isWhitespace(peek)){
            if(peek == '\n'){
                line++;
            }

            readch();
        }

        switch(peek){
            case '&':
                if(readch('&')) return new Word("&&", idTable.get("&&").getValue());
                return new Token('&');
            case '|':
                if(readch('|')) return new Word("||", idTable.get("||").getValue());
                return new Token('|');
            case '=':
                if(readch('=')) return new Word("==", idTable.get("==").getValue());
                return new Token('=');
            case '!':
                if(readch('=')) return new Word("!=", idTable.get("!=").getValue());
                return new Token('!');
            case '>':
                if(readch('=')) return new Word(">=", idTable.get(">=").getValue());
                return new Token('>');
            case '<':
                if(readch('=')) return new Word("<=", idTable.get("<=").getValue());
                return new Token('<');
        }

        if(Character.isDigit(peek)){
            int v = 0;

            do {
                v = 10 * v + Character.digit(peek, 10);
                readch();
            } while(Character.isDigit(peek));

            if(peek != '.') {
                System.out.println(new Num(v).toString());
                return new Num(v);
            }
            float x = v;
            float d = 10;

            do {
                readch();
                x = x + Character.digit(peek, 10) / d;
                d = d * 10;
            } while(Character.isDigit(peek));

            System.out.println(new Real(x));
            return new Real(x);
        }

        if(Character.isLetter(peek)){
            StringBuffer stringBuffer = new StringBuffer();

            do{
                stringBuffer.append(peek);
                readch();
            } while(Character.isLetterOrDigit(peek));

            String s = stringBuffer.toString();
            Tags tags = idTable.get(s);

            if(tags != null){

                return new Word(s, tags.getValue());
            }

            idTable.put(s, Tags.ID);
            System.out.println(new Word(s, Tags.ID.getValue()).toString());

            return new Word(s, Tags.ID.getValue());
        }

        Token token = new Token(peek);
        peek = ' ';
        System.out.println(token.toString());
        return token;
    }

    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        while (true){
            lexer.scan();
        }
    }
}
