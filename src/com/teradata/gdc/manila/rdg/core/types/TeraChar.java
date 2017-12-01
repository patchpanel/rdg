/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teradata.gdc.manila.rdg.core.types;

//import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author jl186034
 */
class TeraChar {
    private Integer size;

    public TeraChar(Integer size) {
        this.size = size;
    }

// --Commented out by Inspection START (11/27/2017 9:38 AM):
//    public void setLimits(Integer size) {
//        this.size = size;
//    }
// --Commented out by Inspection STOP (11/27/2017 9:38 AM)

    public String generateRow() {
        return RandomStringUtils.random(this.size, true, false);
        //ArrayList<String> list = new ArrayList<String>();
        //return list;
    }
}
