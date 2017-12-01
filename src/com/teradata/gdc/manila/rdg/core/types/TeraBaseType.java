/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teradata.gdc.manila.rdg.core.types;

/**
 * @author jl186034
 */
abstract class TeraBaseType {
    private double limits;
    //private final String name;

    //public TeraBaseType() {
    //this.name = name;
    //}

    public double getLimits() {
        return this.limits;
    }
}
