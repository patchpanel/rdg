package com.teradata.gdc.manila.rdg.core.types;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class TeraTimeStamp {

    public String generateRow() {
        Timestamp current_ts = new Timestamp(System.currentTimeMillis());

        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf(current_ts.toString()).getTime();
        long diff = end - offset + 1;

        Timestamp ts = new Timestamp(offset + (long) (Math.random() * diff));

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(ts);
    }
}
