/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    public ArrayList<IOwner> buscaListaDeOwners() throws BancoDAOExcepiton;
 
    public ArrayList<ITable> buscaListaDeTabelasDoOwner(IOwner o) throws BancoDAOExcepiton;
    
    public ArrayList<IColumn> buscaListaDeTabelasDoOwnerComOsDados(IOwner owner,ITable tabela) throws BancoDAOExcepiton;
    public void dropAllTables() throws BancoDAOExcepiton;
}
