/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewcontroller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BancoDAOExcepiton;
import model.IBancoDAO;
import model.IColumn;
import model.IOwner;
import model.ITable;

/**
 *
 * @author 10109144
 */
public class Configurations {
private static Configurations instancia;
 
 private Configurations(){}

    /**
     *
     * @return
     */
    public static Configurations getInstancia(){
        if (instancia == null){
            instancia = new Configurations();
        }
        return instancia;
 
 }
   
    private IBancoDAO baseDeDados;
    private IOwner currentOwner;
    private String userLoggedIn;

    
    public IOwner getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(IOwner currentOwner) {
    
         this.currentOwner = currentOwner;
      

        
    }

    public String getUsuarioLogado() {
        return userLoggedIn;
    }

    public void setUsuarioLogado(String usuarioLogado) {
        this.userLoggedIn = usuarioLogado;
    }

    public IBancoDAO getBaseDeDados() {
        return baseDeDados;
    }

    public void setBaseDeDados(IBancoDAO baseDeDados) {
        this.baseDeDados = baseDeDados;
    }
    
    public void setTabelasOwner (ArrayList<ITable> tabelas){
    
    this.currentOwner.setListaDeTabelas(tabelas);
    setListaDeTabelasAlterandoSeuValor(tabelas);
             
    }
    
    public ITable getOwnerTable(String tabela) {
        ITable retorno = null;
        for (ITable t : currentOwner.getListaDeTabelas()) {
            if (t.getTable_name().equalsIgnoreCase(tabela)) {
                return t;
            }

        }
        return retorno;
    }
    
    public IColumn getColunaDaTabela(String tabela, String coluna) {
        IColumn retorno = null;
        ITable ownerTable = getOwnerTable(tabela);
        for (IColumn c : ownerTable.getListaDeColunas()) {
            if (c.getColumn_name().equalsIgnoreCase(coluna)) {
                return c;

            }

        }

        return retorno;
    }
    public IColumn getColunaDaTabela(ITable tabela, String coluna) {
        IColumn retorno = null;
        
        for (IColumn c : tabela.getListaDeColunas()) {
            if (c.getColumn_name().equalsIgnoreCase(coluna)) {
                return c;

            }

        }

        return retorno;
    }

    
    
        
     
    
    
    
    public void setListaDeTabelasAlterandoSeuValor(ArrayList<ITable> listaDeTabelas) {
     
        IBancoDAO base = getBaseDeDados();
        for (ITable tabela : listaDeTabelas) {           
            try {
                tabela.setListaDeColunas(base.buscaListaDeTabelasDoOwnerComOsDados(getCurrentOwner(), tabela));
                for (IColumn coluna : tabela.getListaDeColunas()) {
                    if (coluna.getNullable().equalsIgnoreCase("Y")) {
                        coluna.setPercentualDeLinhasNulas(50.00);
                    }
                    coluna.setTamanhoMedioEstimado(Double.parseDouble(coluna.getData_length()) / 2);
                }
            } catch (BancoDAOExcepiton ex) {
                Logger.getLogger(Configurations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   public void InsertIntoOwners() throws BancoDAOExcepiton {
      IBancoDAO base = getBaseDeDados();
      base.InsertIntoOwners(currentOwner);
    }

    public void alterTable(ITable table) {        
    
       currentOwner.alterTable(table);
    }

    public void addIColumnTableByList(ArrayList<IColumn> buscaListaDeTabelasDoOwnerComOsDados, ITable t) {
      for (IColumn column : buscaListaDeTabelasDoOwnerComOsDados){
          for (ITable table: currentOwner.getListaDeTabelas()){
              if (column.getColumn_name().equalsIgnoreCase(table.getTable_name())){
                  table.addColumn(column);
              }
          }
      }
    }



}



