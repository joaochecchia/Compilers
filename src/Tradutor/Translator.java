package Tradutor;

public class Translator {

    public String expressao;
    public int pos = 0;
    public char lookahed;

    public Translator(String expressao) {
        this.expressao = expressao;
        this.lookahed = expressao.charAt(pos);
    }

    public void expr(){
        term();
        oper();
    }

    public void oper(){
        while (true){
            if(lookahed == '+'){
                match('+');
                term();
                System.out.print("+");
            } else if (lookahed == '-') {
                match('-');
                term();
                System.out.print("-");
            } else return;
        }
    }

    public void term(){
        if (Character.isDigit(lookahed)){
            System.out.print(lookahed);
            match(lookahed);
        } else throw new IllegalArgumentException("Sentença não corresponde a syntax");
    }

    public void match(char t){
        if (t == lookahed){
            pos++;
            if (pos < expressao.length()) {
                lookahed = expressao.charAt(pos);
            } else {
                lookahed = '\0';
            }
        } else throw new IllegalArgumentException("Sentença não coresponde a syntax");
    }

    public static void main(String[] args) {
        Translator translator = new Translator("2+3-4");
        translator.expr();
    }
}
