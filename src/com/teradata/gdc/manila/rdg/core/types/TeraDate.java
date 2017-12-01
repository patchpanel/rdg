package com.teradata.gdc.manila.rdg.core.types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

class TeraDate {

    public String generateRow() {
        Random rnd = new Random();
// 1262275200000L = January 1, 2010
// Add up to 3 years to it (using modulus on the next long)
        long ms = 1262275200000L + (Math.abs(rnd.nextLong()) % (7L * 365 * 24 * 60 * 60 * 1000));
// Construct a date
        Date dt = new Date(ms);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dt);
    }

// --Commented out by Inspection START (11/27/2017 9:38 AM):
//    public long dateToLong(String date) {
//        long milliseconds = 0;
//
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date d = f.parse(date);
//            milliseconds = d.getTime();
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
//        return milliseconds;
//    }
// --Commented out by Inspection STOP (11/27/2017 9:38 AM)
}