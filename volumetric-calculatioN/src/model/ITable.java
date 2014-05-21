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
public interface ITable {

    @Override
    public String toString();

    public String getTable_name();

    public void setTable_name(String table_name);

    public ArrayList<IColumn> getListaDeColunas();

    public void setListaDeColunas(ArrayList<IColumn> listaDeColunas);

    public int getNumeroEstimadoDeLinhasInicias();

    public void setNumeroEstimadoDeLinhasInicias(int numeroEstimadoDeLinhasInicias);

    public double getPercentualDeCrescimento();

    public void setPercentualDeCrescimento(double percentualDeCrescimento);

    public int getTempoDeRetencao();

    public void setTempoDeRetencao(int tempoDeRetencao);
}
