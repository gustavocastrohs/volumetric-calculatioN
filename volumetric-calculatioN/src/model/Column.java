/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author 09201801
 */
public class Column implements IColumn{
    
    private String column_name;
    private String data_type;
    private int data_length;
    private int data_precision;
    private String nullable;

    public Column(String column_name, String data_type, int data_length, int data_precision, String nullable) {
        this.column_name = column_name;
        this.data_type = data_type;
        this.data_length = data_length;
        this.data_precision = data_precision;
        this.nullable = nullable;
    }

    public Column() {
    }

    public Column(String column_name) {
        this.column_name = column_name;
    }
    
    
    
    
    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public int getData_length() {
        return data_length;
    }

    public void setData_length(int data_length) {
        this.data_length = data_length;
    }

    public int getData_precision() {
        return data_precision;
    }

    public void setData_precision(int data_precision) {
        this.data_precision = data_precision;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }


    
}
