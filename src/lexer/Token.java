package lexer;

import lexer.Tags;

public class Token {

    private Tags tag;
    private String value;

    public Token(Tags type, String value) {
        this.tag = type;
        this.value = value;
    }

    public Tags getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return tag + ": " + value;
    }
}