package analise;

public enum Tags {
    AND(256), BASIC(257), BREAK(258), DO(259), ELSE(260),
    EQ(261), FALSE(262), GE(263), ID(264), IF(265),
    INDEX(266), LE(267), MINUS(268), NE(269), NUM(270),
    OR(271), REAL(272), TEMP(273), TRUE(274), WHILE(275);

    private final int value;

    public static final int AND_VALUE = 256;
    public static final int BASIC_VALUE = 257;
    public static final int BREAK_VALUE = 258;
    public static final int DO_VALUE = 259;
    public static final int ELSE_VALUE = 260;
    public static final int EQ_VALUE = 261;
    public static final int FALSE_VALUE = 262;
    public static final int GE_VALUE = 263;
    public static final int ID_VALUE = 264;
    public static final int IF_VALUE = 265;
    public static final int INDEX_VALUE = 266;
    public static final int LE_VALUE = 267;
    public static final int MINUS_VALUE = 268;
    public static final int NE_VALUE = 269;
    public static final int NUM_VALUE = 270;
    public static final int OR_VALUE = 271;
    public static final int REAL_VALUE = 272;
    public static final int TEMP_VALUE = 273;
    public static final int TRUE_VALUE = 274;
    public static final int WHILE_VALUE = 275;


    Tags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
