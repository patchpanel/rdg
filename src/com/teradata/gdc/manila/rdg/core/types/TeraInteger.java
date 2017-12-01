/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teradata.gdc.manila.rdg.core.types;

import java.util.Random;

/**
 * @author jl186034
 */
class TeraInteger { //extends TeraBaseType {

    private Integer rows;
    private final Integer min;
    private final Integer max;

    public TeraInteger(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public String generateRowAsString() {
        return this.generateRow().toString();
    }

    private Integer generateRow() {
        Random rand = new Random();

        //maxValue = (this.limits != 0) ? this.limits : MAX_LIMIT;
        //return rand.nextInt(this.max);//((maxValue - MIN_LIMIT) + 1) + MIN_LIMIT;
        return rand.nextInt((this.max - this.min) + 1) + this.min;

    }
}
