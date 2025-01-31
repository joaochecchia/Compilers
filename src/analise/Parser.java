package analise;

import analise.tokens.Num;
import analise.tokens.Real;
import analise.tokens.Token;
import analise.tokens.Word;

import java.io.IOException;

import static analise.Tags.ID;

public class Parser {

    Lexer lexer = new Lexer();
    Token lookahead;
    SymTable symTable;

    public Parser() throws IOException {
        this.lookahead = lexer.scan();
        this.symTable = new SymTable(null);
    }

    public void program() throws IOException {
        block();
    }

    public void block() throws IOException {
        match('{');

        SymTable temp = symTable;
        SymTable newSymTable = new SymTable(temp);

        decls();
        stmts();

        match('}');
        this.symTable = temp;
    }

    public void decls() throws IOException {
        while(lookahead.tag == Tags.ID_VALUE){
            String type = ((Word) lookahead).lexeme;
            match(ID.getValue());

            String name = ((Word) lookahead).lexeme;
            match(ID.getValue());

            if(symTable.get(name) != null){
                throw new RuntimeException("Variable " + name + " already exists in scope.");
            }

            symTable.put(name, type);
            match(';');
        }
    }

    public void stmts() throws IOException {
        while(lookahead.tag != '}'){
            switch(lookahead.tag){
                case '{':
                    match('{');
                    block();
                    break;
                case Tags.ID_VALUE:
                    expr();
                    break;
                case Tags.IF_VALUE:
                    match(Tags.IF_VALUE);
                    match('(');
                    expr();
                    match(')');
                    block();
                    break;
                case Tags.WHILE_VALUE:
                    expr();
                    block();
                default:
                    throw new RuntimeException("Can't read properties of symbol " + lookahead.tag + ".");
            }
        }
    }

    public void expr() throws IOException {
        term();

        while(true){
            switch(lookahead.tag){
                case '=':
                    match('=');
                    term();
                    break;
                case '>':
                    match('>');
                    term();
                    break;
                case '<':
                    match('<');
                    term();
                    break;
                case Tags.GE_VALUE:
                    match(Tags.GE_VALUE);
                    term();
                    break;
                case Tags.NE_VALUE:
                    match(Tags.NE_VALUE);
                    term();
                    break;
                case Tags.LE_VALUE:
                    match(Tags.LE_VALUE);
                    term();
                    break;
                case Tags.OR_VALUE:
                    match(Tags.OR_VALUE);
                    term();
                    break;
                case Tags.AND_VALUE:
                    match(Tags.AND_VALUE);
                    term();
                    break;
                default:
                    throw new RuntimeException("Can't read properties of " + Tags.ID_VALUE + ".");
            }
        }
    }

    public void term() throws IOException {
        if(lookahead instanceof Word){
            match(Tags.ID_VALUE);
        } else if(lookahead instanceof Real){
            match(Tags.REAL_VALUE);
        } else if(lookahead instanceof Num){
            match(Tags.NUM_VALUE);
        } else {
            throw new RuntimeException("Error: invalid expression: " + lookahead);
        }
    }

    public boolean match(int tag) throws IOException {
        if(tag == lookahead.tag){
            lookahead = lexer.scan();
            return true;
        }

        throw new RuntimeException("Expected: " + tag + ". Found: " + lookahead.tag);
    }

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();

        parser.program();
    }
}
