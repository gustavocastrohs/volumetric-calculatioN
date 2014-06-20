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
public class VolumetricCalc {
    
    public VolumetricCalc() {
    }

    public String insert(IOwner owner) {
        String procedure = "INSERT INTO owners VALUES (\n"
                + "  T_OWNER(\'"+owner.getNome()+"\', T_TABLES("
                + generateTables(owner)
                + " ))"
                + "  )";
        return procedure;

    }

    private String generateTables(IOwner owner) {
        String tabelas = "";
        for (ITable t : owner.getListaDeTabelas()) {
            tabelas =""+ generateTable(t);
        }
        return "T_TABLE(" + tabelas + ")";
    }

    private String generateTable(ITable table) {
        String tabelaString = "";
        for (IColumn c : table.getListaDeColunas()) {
        tabelaString =""+ generateColumns(c);
        }
        return "'"
                +table.getTable_name()
                +"',"
                +table.getNumeroEstimadoDeLinhasInicias()
                +","
                +table.getPercentualDeCrescimento()
                +","
                +table.getTempoDeRetencao()
                +","
                +"null,"
                +"null,"
                +"null,"
                +"null"
                +", T_COLUMNS(" + tabelaString+ ")";
    }

    private String generateColumns(IColumn coluna) {
        return "TCOLUMN(" + coluna.getColumn_name()
                + "," 
                +coluna.getData_type() 
                + "," 
                +coluna.getData_length()
                + ",'"                 
                +coluna.getNullable()
                + "'," 
                +coluna.getTamanhoMedioEstimado()
                + "," 
                +coluna.getPercentualDeLinhasNulas()
                +")";
    }

}
