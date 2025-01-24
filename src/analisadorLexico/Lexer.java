package analisadorLexico;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static analisadorLexico.Tags.*;

public class Lexer {

    private int line = 1;
    private int peek;
    private Map<String, Tags> tokens;

    public Lexer() throws IOException {
        this.peek = System.in.read();
        this.tokens = new HashMap<>();

        tokens.put("TRUE", TRUE);
        tokens.put("FALSE", FALSE);
    }

    public void scan() throws IOException {
        while(Character.isWhitespace(peek)){
            if((char) peek == '\n'){
                this.line++;
            }

            this.peek = System.in.read();
        }

        if(Character.isDigit(peek)){
            int v = 0;

            do{
                int n = peek - '0';
                v = v * 10 + n;

                peek = System.in.read();
            }while (Character.isDigit(peek));

            System.out.println("<NUM, " + v + ">");

            return;
        }

        if(Character.isAlphabetic(peek)){
            StringBuilder stringBuilder = new StringBuilder();

            do {
                stringBuilder.append((char) peek);
                peek = System.in.read();
            } while (Character.isAlphabetic(peek));

            String s = stringBuilder.toString();

            if(tokens.containsKey(s)){
               switch(tokens.get(s)){
                   case TRUE:
                       System.out.println("<TRUE, " + s + ">");
                       break;
                   case FALSE:
                       System.out.println("<FALSE, " + s + ">");
                       break;
                   default:
                       System.out.println("<ID, " + s + ">");
                       break;
               }

               return;
            }

            tokens.put(s, ID);
            System.out.println("<ID, " + s + ">");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while(!Character.isWhitespace(peek)){
            stringBuilder.append((char) peek);
            peek = System.in.read();
        }

        peek = ' ';

        System.out.println("<ID, " + stringBuilder.toString() + ">");
    }


    public static void main(String[] args) throws IOException{
        Lexer lexer = new Lexer();

        while (true) {
            lexer.scan();
        }
    }
}
