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
public class Table implements ITable{
    private String table_name;
    
      
    

//SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER = 'SYS';
    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    
}
