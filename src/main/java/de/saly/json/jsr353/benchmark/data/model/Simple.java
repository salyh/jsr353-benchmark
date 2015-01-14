package de.saly.json.jsr353.benchmark.data.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Simple {

    private String string;
    private String complexString;
    private int numInt;
    private double numDouble;
    private BigDecimal numBigDecimal;
    private BigInteger numBigInteger;
    private boolean bool;
    //private short numShort;
    //private byte numByte;
    private long numLong;
    private char charValue;
    
    private String[] stringA;
    private int[] numIntA;
    private double[] numDoubleA;
    private BigDecimal[] numBigDecimalA;
    private BigInteger[] numBigIntegerA;
    private boolean[] boolA;
    //private short[] numShortA;
    //private byte[] numByteA;
    private long[] numLongA;
    private char[] charValueA;
    
    private Simple nestedSimple;
    private Simple[] nestedSimpleA;
    
    public void init() {
        init(true);
    }
    
    private void init(boolean nested) {
        string = "This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String, This is a simple String";
        complexString = "some \t \u0001 unicode: ÖÄÜ \u0070\u0070\u0070ন\udbff\udfff some \t \u0001 unicode: ÖÄÜ \u0070\u0070\u0070ন\udbff\udfff some \t \u0001 unicode: ÖÄÜ \u0070\u0070\u0070ন\udbff\udfff";
        numInt = 123;
        numDouble = 657.991D;
        numBigDecimal = new BigDecimal("213431222.77182");
        numBigInteger = new BigInteger("761857163488");
        bool = true;
        //numShort = (short) -2;
        //numByte = (byte) 6;
        numLong = -987654L;
        charValue = 'A';
        
        stringA = new String[]{string, string, complexString};
        numIntA = new int[]{2,5,4,1,-4444,1898};
        numDoubleA = new double[]{12.7D, -212.55D, 3D, 8.9D};
        numBigDecimalA = new BigDecimal[]{numBigDecimal, numBigDecimal};
        numBigIntegerA = new BigInteger[]{numBigInteger, numBigInteger, numBigInteger};
        boolA = new boolean[]{true, true, false, true, false};
        //numShortA = new short[]{(short)1,(short)2,(short)3,(short)4,(short)5,(short)-7,(short)-8};
        numLongA = new long[]{-22312345L, 0L, 123L, 987654L, -7771987L};
        charValueA = new char[]{'H','e','l','l','o'};
        //numByteA = new byte[]{(byte) 1, (byte) 1,(byte) 1,(byte) 2};
        
        if(nested) {
            Simple n = new Simple();
            n.init(false);
            
            nestedSimpleA = new Simple[]{n,n,n};
            
        }
        
    }
    
    
    public Simple() {

    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getComplexString() {
        return complexString;
    }

    public void setComplexString(String complexString) {
        this.complexString = complexString;
    }

    public int getNumInt() {
        return numInt;
    }

    public void setNumInt(int numInt) {
        this.numInt = numInt;
    }

    public double getNumDouble() {
        return numDouble;
    }

    public void setNumDouble(double numDouble) {
        this.numDouble = numDouble;
    }

    public BigDecimal getNumBigDecimal() {
        return numBigDecimal;
    }

    public void setNumBigDecimal(BigDecimal numBigDecimal) {
        this.numBigDecimal = numBigDecimal;
    }

    public BigInteger getNumBigInteger() {
        return numBigInteger;
    }

    public void setNumBigInteger(BigInteger numBigInteger) {
        this.numBigInteger = numBigInteger;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
    /*
    public short getNumShort() {
        return numShort;
    }

    public void setNumShort(short numShort) {
        this.numShort = numShort;
    }

    public byte getNumByte() {
        return numByte;
    }

    public void setNumByte(byte numByte) {
        this.numByte = numByte;
    }*/

    public long getNumLong() {
        return numLong;
    }

    public void setNumLong(long numLong) {
        this.numLong = numLong;
    }

    public char getCharValue() {
        return charValue;
    }

    public void setCharValue(char charValue) {
        this.charValue = charValue;
    }

    public String[] getStringA() {
        return stringA;
    }

    public void setStringA(String[] stringA) {
        this.stringA = stringA;
    }

    public int[] getNumIntA() {
        return numIntA;
    }

    public void setNumIntA(int[] numIntA) {
        this.numIntA = numIntA;
    }

    public double[] getNumDoubleA() {
        return numDoubleA;
    }

    public void setNumDoubleA(double[] numDoubleA) {
        this.numDoubleA = numDoubleA;
    }

    public BigDecimal[] getNumBigDecimalA() {
        return numBigDecimalA;
    }

    public void setNumBigDecimalA(BigDecimal[] numBigDecimalA) {
        this.numBigDecimalA = numBigDecimalA;
    }

    public BigInteger[] getNumBigIntegerA() {
        return numBigIntegerA;
    }

    public void setNumBigIntegerA(BigInteger[] numBigIntegerA) {
        this.numBigIntegerA = numBigIntegerA;
    }

    public boolean[] getBoolA() {
        return boolA;
    }

    public void setBoolA(boolean[] boolA) {
        this.boolA = boolA;
    }
/*
    public short[] getNumShortA() {
        return numShortA;
    }

    public void setNumShortA(short[] numShortA) {
        this.numShortA = numShortA;
    }

    public byte[] getNumByteA() {
        return numByteA;
    }

    public void setNumByteA(byte[] numByteA) {
        this.numByteA = numByteA;
    }*/

    public long[] getNumLongA() {
        return numLongA;
    }

    public void setNumLongA(long[] numLongA) {
        this.numLongA = numLongA;
    }

    public char[] getCharValueA() {
        return charValueA;
    }

    public void setCharValueA(char[] charValueA) {
        this.charValueA = charValueA;
    }

    public Simple getNestedSimple() {
        return nestedSimple;
    }

    public void setNestedSimple(Simple nestedSimple) {
        this.nestedSimple = nestedSimple;
    }

    public Simple[] getNestedSimpleA() {
        return nestedSimpleA;
    }

    public void setNestedSimpleA(Simple[] nestedSimpleA) {
        this.nestedSimpleA = nestedSimpleA;
    }

    
    
}
