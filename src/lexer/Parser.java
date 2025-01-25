package lexer;

import java.io.IOException;
import static lexer.Tags.ID;
import static lexer.Tags.NUM;

public class Parser {
    private Token lookAhead;
    private Lexer lexer;

    public Parser() throws IOException {
        this.lexer = new Lexer();
        this.lookAhead = lexer.scan();
    }

    public void expr() throws IOException {
        term();
        while(true) {
            if (lookAhead.getValue().equals("+")) {
                match("+");
                term();
                System.out.println("+");
            } else if (lookAhead.getValue().equals("-")) {
                match("-");
                term();
                System.out.println("-");
            } else {
                break;
            }
        }
    }

    public void term() throws IOException {
        fact();
        while(true) {
            if (lookAhead.getValue().equals("*")) {
                match("*");
                fact();
                System.out.println("*");
            } else if (lookAhead.getValue().equals("/")) {
                match("/");
                fact();
                System.out.println("/");
            } else {
                break;
            }
        }
    }

    public void fact() throws IOException {
        if(lookAhead.getValue().equals("(")) {
            match("(");
            expr();
        } else if(lookAhead.getTag().equals(NUM)) {
            System.out.print("[" + lookAhead.toString() + "]");
            match(lookAhead.getValue());
        } else if(lookAhead.getTag().equals(ID)) {
            System.out.print("[" + lookAhead.toString() + "]");
        } else {
            throw new RuntimeException("Símbolo " + lookAhead.toString());
        }
    }

    public boolean match(String tag) throws IOException {
        if (tag.equals(lookAhead.getValue())) {
            lookAhead = lexer.scan();
            return true;
        }

        System.out.println("Esperado: " + tag + ", mas encontrado: " + lookAhead.getValue());
        return false;
    }

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.expr();
    }
}