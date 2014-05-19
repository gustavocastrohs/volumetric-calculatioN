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
public class Owner implements IOwner{
    private String nome;
    private int id;
    private ArrayList<ITable> listaDeTabelas;

    public Owner(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }
        public Owner() {
        
    }

    public Owner(String nome) {
        this.nome = nome;
    }

    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public void setNome(String nome) {
        this.nome = nome;

    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    
    public String toString(){
        return nome;
    }

    public ArrayList<ITable> getListaDeTabelas() {
        return listaDeTabelas;
    }

    public void setListaDeTabelas(ArrayList<ITable> listaDeTabelas) {
        
        this.listaDeTabelas = listaDeTabelas;
    }
    
}
