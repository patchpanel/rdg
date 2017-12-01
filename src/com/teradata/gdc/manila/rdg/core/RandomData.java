package com.teradata.gdc.manila.rdg.core;

import com.teradata.gdc.manila.rdg.core.types.TeraFactory;
import com.teradata.gdc.manila.rdg.util.ProgressBar;
import com.teradata.gdc.manila.rdg.util.TeraConnection;
import com.teradata.gdc.manila.rdg.util.TeraQuery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RandomData {
    private List<String> excludedFields;
    private String colName;//Made it a field since lambda expression does not allo local variables. Will find a better way

    public void generateRows(TeraConnection cn, String table, int numRows, char delim, BufferedWriter bw, File file) throws SQLException, IOException {
        //String colName;
        String colType, colPaS;
        Integer colSize;
        // Creating a statement object from an active connection
        TeraQuery tq = new TeraQuery(cn.getConnection());
        tq.executeQuery("SELECT * FROM " + table.trim() + ";");
        int colCount = tq.getColumnCount();
        //Assign the arraylists to hold metadata info
        List<String> l_Name = new ArrayList<>(tq.getColumnName());
        List<Integer> l_Size = new ArrayList<>(tq.getColumnSize());
        List<String> l_Type = new ArrayList<>(tq.getColumnType());
        List<String> l_PaS = new ArrayList<>(tq.getPrecisionAndScale());
        //Arraylist to be returned
        ArrayList<String> row = new ArrayList<>();
        //Arraylist for exlusion fields
        List<String> items = new ArrayList<>(this.excludedFields);

        //Show status of generation
        ProgressBar bar = new ProgressBar();
        bar.update(0, numRows);

        int c = 0;
        while (c < numRows) {
            TeraFactory tf = new TeraFactory();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < colCount; i++) {
                this.colName = l_Name.get(i);
                colType = l_Type.get(i);
                colSize = l_Size.get(i);
                colPaS = l_PaS.get(i);
                //Process the column if it's not found in exlusion list
                List<String> matches = items.stream().filter(it -> it.equalsIgnoreCase(this.colName)).collect(Collectors.toList());
                if (matches.size() == 0) {
                    sb.append(tf.generateRows(colType, colSize, colPaS));
                    //if (i < colCount - 1) {
                        sb.append(delim);
                    //}
                }
            }
            //Add the generated column data to the row array and trim the last delimeter
            //row.add(sb.toString().substring(0, sb.length() - 1));
            //Write it immediately
            bw.append(sb.toString().substring(0, sb.length() - 1) + System.lineSeparator());
            //FileUtils.writeLines(file, row);
            //row.clear();
            //Update generation status
            bar.update(c, numRows);
            c++;
        }
        tq.closeStatement();
        //return row;
    }

    public List getExcludedFields() {
        return excludedFields;
    }

    public void setExcludedFields(List<String> excludedFields) {
        this.excludedFields = excludedFields;
    }
}