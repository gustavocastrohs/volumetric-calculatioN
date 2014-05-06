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
public interface ITable {
   
    @Override
    public String toString();

    public String getTable_name();

    public void setTable_name(String table_name);
    
    public String getColumn_name();

    public void setColumn_name(String column_name);

    public String getData_type();

    public void setData_type(String data_type);

    public int getData_length();

    public void setData_length(int data_length);

    public int getData_precision();

    public void setData_precision(int data_precision);

    public String getNullable();

    public void setNullable(String nullable);   
 
}
