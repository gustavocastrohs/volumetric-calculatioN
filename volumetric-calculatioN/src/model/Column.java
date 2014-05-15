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
    private String data_length;
    private String data_precision;
    private String nullable;
    private Double tamanhoMedioEstimado;
    private Double percentualDeLinhasNulas;

    public Column() {
    }

    public Column(String column_name, String data_type, String data_length, String data_precision, String nullable) {
        this.column_name = column_name;
        this.data_type = data_type;
        this.data_length = data_length;
        this.data_precision = data_precision;
        this.nullable = nullable;
    }

    public Double getTamanhoMedioEstimado() {
        return tamanhoMedioEstimado;
    }

    public void setTamanhoMedioEstimado(Double tamanhoMedioEstimado) {
        this.tamanhoMedioEstimado = tamanhoMedioEstimado;
    }

    public Double getPercentualDeLinhasNulas() {
        return percentualDeLinhasNulas;
    }

    public void setPercentualDeLinhasNulas(Double percentualDeLinhasNulas) {
        this.percentualDeLinhasNulas = percentualDeLinhasNulas;
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

    public String getData_length() {
        return data_length;
    }

    public void setData_length(String data_length) {
        this.data_length = data_length;
    }

    public String getData_precision() {
        return data_precision;
    }

    public void setData_precision(String data_precision) {
        this.data_precision = data_precision;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    
}
