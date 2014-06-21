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
      //  System.out.println(procedure);
        return procedure;

    }

    private String generateTables(IOwner owner) {
        String tabelas = "";
        for (int i = 0; i < owner.getListaDeTabelas().size(); i++) {
        
        ITable t =     owner.getListaDeTabelas().get(i);       
            tabelas = tabelas + "T_TABLE(" + generateTable(t) + ")";
            if (owner.getListaDeTabelas().size() > 0) {
                if (i < owner.getListaDeTabelas().size() - 1) {
                    tabelas = tabelas + ",";
                }
            }
        }
        return tabelas;
    }

    private String generateTable(ITable table) {
        String tabelaString = "";
        for (int i = 0; i < table.getListaDeColunas().size(); i++) {
            IColumn c = (IColumn)table.getListaDeColunas().get(i);
            tabelaString = tabelaString + generateColumns(c);
            if (table.getListaDeColunas().size() > 0) {
                if (i < table.getListaDeColunas().size()-1) {
                    tabelaString = tabelaString + ",";
                }
            }
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
        return "T_COLUMN('" + coluna.getColumn_name()
                + "','" 
                +coluna.getData_type() 
                + "'," 
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
