package com.teradata.gdc.manila.rdg.core.types;

import com.teradata.gdc.manila.rdg.exception.TeraException;

public class TeraDecimal {
    private long leftLimit;
    private long rightLimit;


    public TeraDecimal(String leftLimit, String rightLimit) throws TeraException {

        final long LENGTH_1 = 9L;
        final long LENGTH_2 = 99L;
        final long LENGTH_3 = 999L;
        final long LENGTH_4 = 9999L;
        final long LENGTH_5 = 99999L;
        final long LENGTH_6 = 999999L;
        final long LENGTH_7 = 9999999L;
        final long LENGTH_8 = 99999999L;
        final long LENGTH_9 = 999999999L;
        final long LENGTH_10 = 9999999999L;
        final long LENGTH_11 = 99999999999L;
        final long LENGTH_12 = 999999999999L;
        final long LENGTH_13 = 9999999999999L;
        final long LENGTH_14 = 99999999999999L;
        final long LENGTH_15 = 999999999999999L;
        final long LENGTH_16 = 9999999999999999L;
        final long LENGTH_17 = 99999999999999999L;
        final long LENGTH_18 = 999999999999999999L;

        int leftLength = (Integer.valueOf(leftLimit) - Integer.valueOf(rightLimit)) - 2;
        int rightLength = Integer.valueOf(rightLimit);

        if (leftLength <= 0) {
            throw new TeraException("Fractional digits must be between 0 and total digit number");
        } else if ((leftLength > 18) || (rightLength > 18)) {
            throw new TeraException("Fractional or Decimal part may not exceed 18");
        }

        //Align limits
        if (leftLength <= 1) {
            this.leftLimit = LENGTH_1;
        } else if (leftLength <= 2) {
            this.leftLimit = LENGTH_2;
        } else if (leftLength <= 3) {
            this.leftLimit = LENGTH_3;
        } else if (leftLength <= 4) {
            this.leftLimit = LENGTH_4;
        } else if (leftLength <= 5) {
            this.leftLimit = LENGTH_5;
        } else if (leftLength <= 6) {
            this.leftLimit = LENGTH_6;
        } else if (leftLength <= 7) {
            this.leftLimit = LENGTH_7;
        } else if (leftLength <= 8) {
            this.leftLimit = LENGTH_8;
        } else if (leftLength <= 9) {
            this.leftLimit = LENGTH_9;
        } else if (leftLength <= 10) {
            this.leftLimit = LENGTH_10;
        } else if (leftLength <= 11) {
            this.leftLimit = LENGTH_11;
        } else if (leftLength <= 12) {
            this.leftLimit = LENGTH_12;
        } else if (leftLength <= 13) {
            this.leftLimit = LENGTH_13;
        } else if (leftLength <= 14) {
            this.leftLimit = LENGTH_14;
        } else if (leftLength <= 15) {
            this.leftLimit = LENGTH_15;
        } else if (leftLength <= 16) {
            this.leftLimit = LENGTH_16;
        } else if (leftLength <= 17) {
            this.leftLimit = LENGTH_17;
        } else if (leftLength <= 18) {
            this.leftLimit = LENGTH_18;
        }

        leftLength = new Integer(rightLimit.trim());

        if (leftLength <= 1) {
            this.rightLimit = LENGTH_1;
        } else if (leftLength <= 2) {
            this.rightLimit = LENGTH_2;
        } else if (leftLength <= 3) {
            this.rightLimit = LENGTH_3;
        } else if (leftLength <= 4) {
            this.rightLimit = LENGTH_4;
        } else if (leftLength <= 5) {
            this.rightLimit = LENGTH_5;
        } else if (leftLength <= 6) {
            this.rightLimit = LENGTH_6;
        } else if (leftLength <= 7) {
            this.rightLimit = LENGTH_7;
        } else if (leftLength <= 8) {
            this.rightLimit = LENGTH_8;
        } else if (leftLength <= 9) {
            this.rightLimit = LENGTH_9;
        } else if (leftLength <= 10) {
            this.rightLimit = LENGTH_10;
        } else if (leftLength <= 11) {
            this.rightLimit = LENGTH_11;
        } else if (leftLength <= 12) {
            this.rightLimit = LENGTH_12;
        } else if (leftLength <= 13) {
            this.rightLimit = LENGTH_13;
        } else if (leftLength <= 14) {
            this.rightLimit = LENGTH_14;
        } else if (leftLength <= 15) {
            this.rightLimit = LENGTH_15;
        } else if (leftLength <= 16) {
            this.rightLimit = LENGTH_16;
        } else if (leftLength <= 17) {
            this.rightLimit = LENGTH_17;
        } else if (leftLength <= 18) {
            this.rightLimit = LENGTH_18;
        }
    }

    public String generateRowAsString() {
        String wholePart = Long.toString(-Math.abs(this.leftLimit) + (long) (Math.random() * (this.leftLimit - (-Math.abs(this.leftLimit)))));
        String fractionPart = Long.toString(0 + (long) (Math.random() * (this.rightLimit - 0)));
        return wholePart + '.' + fractionPart;
    }

    public void setLeftLimit(long leftLimit) {
        this.leftLimit = leftLimit;
    }

    public void setRightLimit(long rightLimit) {
        this.rightLimit = rightLimit;
    }
}
