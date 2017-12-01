package com.teradata.gdc.manila.rdg.core.types;

class TeraLong {

    private final Long min;
    private final Long max;

    public TeraLong(Long min, Long max) {//(String name) {
        //super(name);
        this.min = min;
        this.max = max;
    }

// --Commented out by Inspection START (11/27/2017 9:37 AM):
//    public Long getMin() {
//        return min;
//    }
// --Commented out by Inspection STOP (11/27/2017 9:37 AM)

// --Commented out by Inspection START (11/27/2017 9:37 AM):
//    public Long getMax() {
//        return max;
//    }
// --Commented out by Inspection STOP (11/27/2017 9:37 AM)

    public String generateRowAsString() {
        return this.generateRow().toString();
    }

    private Long generateRow() {
        //long leftLimit = -128L;
        //long rightLimit = -127L;
        //long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return this.min + (long) (Math.random() * (this.max - this.min));
    }
}
