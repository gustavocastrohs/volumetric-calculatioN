/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;



/**
 *
 * @author 09201801
 */
public class Table implements ITable{
    private String table_name;
    private ArrayList<IColumn> listaDeColunas;
    private int numeroEstimadoDeLinhasInicias = 5000;
    private double percentualDeCrescimento= 10.00;
    private int tempoDeRetencao = 5;
    
    public Table(String table_name) {
        this.table_name = table_name;
    }

    public Table() {
    }
    
      
    

//SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER = 'SYS';
    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
    public String toString(){
    
    return table_name;
    }

    public ArrayList<IColumn> getListaDeColunas() {
        return listaDeColunas;
    }

    public void setListaDeColunas(ArrayList<IColumn> listaDeColunas) {
        this.listaDeColunas = listaDeColunas;
    }

    public int getNumeroEstimadoDeLinhasInicias() {
        return numeroEstimadoDeLinhasInicias;
    }

    public void setNumeroEstimadoDeLinhasInicias(int numeroEstimadoDeLinhasInicias) {
        this.numeroEstimadoDeLinhasInicias = numeroEstimadoDeLinhasInicias;
    }

    public double getPercentualDeCrescimento() {
        return percentualDeCrescimento;
    }

    public void setPercentualDeCrescimento(double percentualDeCrescimento) {
        this.percentualDeCrescimento = percentualDeCrescimento;
    }

    public int getTempoDeRetencao() {
        return tempoDeRetencao;
    }

    public void setTempoDeRetencao(int tempoDeRetencao) {
        this.tempoDeRetencao = tempoDeRetencao;
    }

    @Override
    public void addColumn(IColumn column) {
        listaDeColunas.add(column);
    }

 
    
    
    
}
