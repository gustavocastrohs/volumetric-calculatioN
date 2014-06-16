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
public class Configuracoes {
 private static Configuracoes instancia;
 
 private Configuracoes(){}

    /**
     *
     * @return
     */
    public static Configuracoes getInstancia(){
        if (instancia == null){
            instancia = new Configuracoes();
        }
        return instancia;
 
 }
    
    private  IBancoDAO baseDeDados;
    private IOwner ownerAtual;
    private String usuarioLogado;
    
    
    public IOwner getOwnerAtual() {
        return ownerAtual;
    }

    public void setOwnerAtual(IOwner ownerAtual) {
     try {
         this.ownerAtual = ownerAtual;
         baseDeDados.InsertIntoOwners(ownerAtual);
     } catch (BancoDAOExcepiton ex) {
        // Logger.getLogger(Configuracoes.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }

    public String getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(String usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public IBancoDAO getBaseDeDados() {
        return baseDeDados;
    }

    public void setBaseDeDados(IBancoDAO baseDeDados) {
        this.baseDeDados = baseDeDados;
    }
    
    public void setTabelasOwner (ArrayList<ITable> tabelas){
    
    this.ownerAtual.setListaDeTabelas(tabelas);
    setListaDeTabelasAlterandoSeuValor(tabelas);
             
    }
    
    public ITable getOwnerTable(String tabela) {
        ITable retorno = null;
        for (ITable t : ownerAtual.getListaDeTabelas()) {
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
    
    public void setIColumnDaTabela(ArrayList<IColumn> buscaListaDeTabelasDoOwnerComOsDados,ITable t){
        
    
    }
    
    
        
     
    
    
    
    public void setListaDeTabelasAlterandoSeuValor(ArrayList<ITable> listaDeTabelas) {
     
        IBancoDAO base = getBaseDeDados();
        for (ITable tabela : listaDeTabelas) {           
            try {
                tabela.setListaDeColunas(base.buscaListaDeTabelasDoOwnerComOsDados(getOwnerAtual(), tabela));
                for (IColumn coluna : tabela.getListaDeColunas()) {
                    if (coluna.getNullable().equalsIgnoreCase("Y")) {
                        coluna.setPercentualDeLinhasNulas(50.00);
                    }
                    coluna.setTamanhoMedioEstimado(Double.parseDouble(coluna.getData_length()) / 2);
                }
            } catch (BancoDAOExcepiton ex) {
                Logger.getLogger(Configuracoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



}



