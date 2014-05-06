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
public interface IOwner {

    public void setNome(String nome);

    public String getNome();

    public int getId();

    public void setId(int id);
    
    public String toString();
}
