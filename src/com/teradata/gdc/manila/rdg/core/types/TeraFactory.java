/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teradata.gdc.manila.rdg.core.types;

import com.teradata.gdc.manila.rdg.exception.TeraException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jl186034
 */
public class TeraFactory {

    public String generateRows(String teraType, Integer size, String precisionAndScale) {
        String rc = null;
        try {
            switch (teraType) {
                case "CHAR":
                    TeraChar tc = new TeraChar(size);
                    rc = tc.generateRow();
                    break;
                case "VARCHAR":
                    TeraChar tvc = new TeraChar(size);
                    rc = tvc.generateRow();
                    break;
                case "DECIMAL":
                    TeraDecimal td = new TeraDecimal(precisionAndScale.split("\\,")[0], precisionAndScale.split("\\,")[1]);
                    rc = td.generateRowAsString();
                    break;
                case "NUMERIC":
                    rc = null;
                    break;
                case "BYTEINT":
                    TeraLong tyi = new TeraLong(-128L, +127L);
                    rc = tyi.generateRowAsString();
                    break;
                case "SMALLINT":
                    TeraLong tsi = new TeraLong(-32768L, +32767L);
                    rc = tsi.generateRowAsString();
                    break;
                case "INTEGER":
                    TeraLong ti = new TeraLong(-2147483648L, +2147483647L);
                    rc = ti.generateRowAsString();
                    break;
                case "BIGINT":
                    TeraLong tbi = new TeraLong(-923337203685477580L, +923337203685477580L);
                    rc = tbi.generateRowAsString();
                    break;
                case "FLOAT":
                    TeraReal tf = new TeraReal(1D, 1D);
                    rc = tf.generateRowAsString();
                case "DATE":
                    TeraDate tdt = new TeraDate();
                    rc = tdt.generateRow();
                    break;
                case "TIME":
                    rc = null;
                    break;
                case "TIMESTAMP":
                    TeraTimeStamp tts = new TeraTimeStamp();
                    rc = tts.generateRow();
                    break;
                default:
                    rc = "TYPE NOT SUPPORTED";
            }
        } catch (TeraException ex) {
            Logger.getLogger(TeraFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return rc;
    }
}
