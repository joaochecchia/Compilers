package lexer;

import java.io.*;
import java.util.*;
import static lexer.Tags.*;

public class Lexer {
    private int line = 1;
    private int peek;
    private Map<String, Tags> idTable;

    public Lexer() throws IOException {
        this.peek = System.in.read();
        this.idTable = new HashMap<>();
        idTable.put("TRUE", TRUE);
        idTable.put("FALSE", FALSE);
    }

    public int getLine() {
        return line;
    }

    public Token scan() throws IOException {
        while (Character.isWhitespace(peek)) {
            if (peek == '\n') {
                line += 1;
            }

            peek = System.in.read();
        }

        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                int n = peek - '0';
                v = v * 10 + n;
                peek = System.in.read();
            } while (Character.isDigit(peek));
            return new Token(NUM, Integer.toString(v));
        }

        if (Character.isLetter(peek)) {
            StringBuilder ss = new StringBuilder();
            do {
                ss.append((char) peek);
                peek = System.in.read();
            } while (Character.isLetter(peek));

            String s = ss.toString();
            Tags t = idTable.get(s);

            if (t != null) {
                return new Token(t, s);
            }

            idTable.put(s, ID);
            return new Token(ID, s);
        }

        if (peek == -1) {
            return new Token(Tags.EOF, "");
        }

        Token token = new Token(OP, Character.toString((char) peek));
        peek = ' ';
        return token;
    }
}