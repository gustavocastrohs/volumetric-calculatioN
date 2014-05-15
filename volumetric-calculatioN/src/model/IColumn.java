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
public interface IColumn {
    
    public String getColumn_name();

    public void setColumn_name(String column_name);

    public String getData_type();

    public void setData_type(String data_type);

    public String getData_length();

    public void setData_length(String data_length);

    public String getData_precision();

    public void setData_precision(String data_precision);

    public String getNullable();

    public void setNullable(String nullable); 
    public Double getTamanhoMedioEstimado();
     public void setTamanhoMedioEstimado(Double tamanhoMedioEstimado);
      public Double getPercentualDeLinhasNulas();
        public void setPercentualDeLinhasNulas(Double percentualDeLinhasNulas);
}
