/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.SQLException;

/**
 *
 * @author Gustavo
 */
public class BancoDAOExcepiton extends Exception{

    /**
     *
     * @param message recebe a menssagem que será exposta para o usuario
     */
    public BancoDAOExcepiton(String message) {
        super(message);
    }
/**
 * 
 * @param message recebe a menssagem que será exposta para o usuario
 * @param ex  recebe a causa do problema
 */

    public BancoDAOExcepiton(String message, SQLException ex) {
        super(message);
    }
/**
 * 
 * @param ex recebe a causa do problema
 */
    public BancoDAOExcepiton(SQLException ex) {
        super(ex);
    }

}
