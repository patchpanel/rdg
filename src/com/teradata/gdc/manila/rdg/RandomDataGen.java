/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teradata.gdc.manila.rdg;

import com.teradata.gdc.manila.rdg.core.RandomData;
import com.teradata.gdc.manila.rdg.util.TeraConnection;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jl186034
 */
public class RandomDataGen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = null;
        String[] tables = null;
        int numRows = 0;
        char delim = 0;
        String output = null;
        String userName = null;
        List<String> exclusionList = null;

        //-----------------OPTIONS CREATION----------------//
        try {
            // create Options object
            Options options = new Options();

            options.addOption("c", "url", true, "Teradata Connection String");
            options.addOption("u", "username", true, "Teradata Username");
            options.addOption("t", "table", true, "Table (in [DB].[TABLE] format) which Data will be generated for");
            options.addOption("n", "numRows", true, "Number of rows to generate");
            options.addOption("d", "delim", true, "Column delimeter when written to file");
            options.addOption("o", "output", true, "Output [Directory] File");
            options.addOption("e", "exclusionList", true, "Exclusion list File");

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("c")) {
                url = cmd.getOptionValue("c");
            } else {
                System.out.println("Teradata Connection String required");
                System.exit(99);
            }

            if (cmd.hasOption("u")) {
                userName = cmd.getOptionValue("u");
            } else {
                System.out.println("Teradata Username required");
                System.exit(99);
            }

            if (cmd.hasOption("t")) {
                tables = cmd.getOptionValues("t");
            } else {
                System.out.println("Teradata Source Table required");
                System.exit(99);
            }

            if (cmd.hasOption("n")) {
                numRows = new Integer(cmd.getOptionValue("n"));
            } else {
                System.out.println("Number of rows to be generated required");
                System.exit(99);
            }

            if (cmd.hasOption("d")) {
                delim = cmd.getOptionValue("d").charAt(0);
            } else {
                System.out.println("File delimeter required");
                System.exit(99);
            }

            if (cmd.hasOption("o")) {
                output = cmd.getOptionValue("o");
            } else {
                System.out.println("Output file required");
                System.exit(99);
            }

            if (cmd.hasOption("e")) {
                java.io.File exclusionFile = new java.io.File(cmd.getOptionValue("e"));
                exclusionList = new ArrayList<>(FileUtils.readLines(exclusionFile, "UTF-8"));
            }
        } catch (ParseException | IOException ex) {
            Logger.getLogger(RandomDataGen.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(99);
        }

        //Get user password
        //Scanner scanner = new Scanner(System.in);
        System.out.print("Password: ");
        String sPassword = new String(System.console().readPassword());
        //String sPassword = "dbc";
        try {
            TeraConnection tc = new TeraConnection(url, userName, sPassword);
            BufferedWriter writer = null;
            //TeraConnection tc = new TeraConnection("jdbc:teradata://192.168.111.129/TMODE=ANSI,CHARSET=UTF8", "dbc", "dbc");
            //ProgressBar bar = new ProgressBar();
            //bar.update(0, numRows);
            try {
                RandomData rd = new RandomData();
                rd.setExcludedFields(exclusionList);
                int f = 0;
                while (f < tables.length) {
                    String table = tables[f];
                    System.out.println("Generating data for " + table);

                    //Build the filename
                    java.io.File fileName = new java.io.File(output
                            + ((output.substring(output.length() - 1).equals('\\')) ? "" : '\\')
                            + table.split("\\.")[1]
                            + ".txt");
                    writer = new BufferedWriter(new FileWriter(fileName, true));
                    //Write mockdata
                    //ArrayList<String> mockRows = rd.generateRows(tc, table, numRows, delim, fileName);
                    rd.generateRows(tc, table, numRows, delim, writer, fileName);
                    System.out.println("Writing data for " + table);
//                    for (int i = 0; i < numRows; i++) {
//                        FileUtils.writeLines(fileName, mockRows);
//                        bar.update(i, numRows);
//                    }
                    f++;//process next table
                }
            } finally {
                tc.closeConnection();
                writer.close();
                System.out.println("All process completed.");
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(RandomDataGen.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
}