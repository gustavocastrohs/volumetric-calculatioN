/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewcontroller;

import model.IBancoDAO;
import model.IOwner;

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
        this.ownerAtual = ownerAtual;
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
 
 
 
}
