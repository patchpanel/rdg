package com.teradata.gdc.manila.rdg.core.types;

import java.util.Random;

public class TeraReal {
    private double leftLimit;
    private double rightLimit;

    public TeraReal(double leftLimit, double rightLimit) {
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    public Double generateRow() {
        //double leftLimit = 1D;
        //double rightLimit = 10D;
        return leftLimit + new Random().nextDouble() * (this.rightLimit - this.leftLimit);
    }

    public String generateRowAsString() {
        return this.generateRow().toString();
    }

    public void setLeftLimit(double leftLimit) {
        this.leftLimit = leftLimit;
    }

    public void setRightLimit(double rightLimit) {
        this.rightLimit = rightLimit;
    }
}
