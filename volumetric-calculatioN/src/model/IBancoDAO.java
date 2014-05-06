/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.BancoDAOExcepiton;
import model.Owner;

import java.awt.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public interface IBancoDAO {

    public String getServer();

    public void setServer(String server);

    public double getPorta();

    public void setPorta(String porta);

    public String getSid();

    public void setSid(String sid);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public ArrayList<Owner> buscaListaDeOwners() throws BancoDAOExcepiton;
    
    public ArrayList<Table> buscaListaDeTabelasDoOwner(Owner o) throws BancoDAOExcepiton;
    
}
