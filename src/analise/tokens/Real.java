package analise.tokens;

import analise.Tags;

public class Real extends Token{

    public final float value;

    public Real(float value) {
        super(Tags.REAL.getValue());
        this.value = value;
    }

    @Override
    public String toString() {
        return "value=" + value ;
    }
}
