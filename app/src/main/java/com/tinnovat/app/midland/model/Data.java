package com.tinnovat.app.midland.model;

import java.util.List;
import java.util.Map;

/**
 * Created by ADMIN on 5/12/2018.
 */

public class Data {

    private int numRows;
    private int totalRows;
    private int startRow;
    private List<Map<String,String>> dataSet;
    private int rowCount;
    private boolean success;

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public List<Map<String, String>> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Map<String, String>> dataSet) {
        this.dataSet = dataSet;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
