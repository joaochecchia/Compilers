package analise.tokens;

import analise.Tags;

public class Num extends Token{
    public final int value;

    public Num(int value) {
        super(Tags.NUM.getValue());
        this.value = value;
    }

    @Override
    public String toString() {
        return "value=" + value;
    }
}
