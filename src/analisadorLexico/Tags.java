package analisadorLexico;

public enum Tags {
    NUM(256), ID, TRUE, FALSE;

    private final int value;

    Tags() {
        this.value = -1;
    }

    Tags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
