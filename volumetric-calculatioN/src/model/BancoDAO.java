
package model;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class BancoDAO implements IBancoDAO{
    // Inicialização e conexão com o Banco Oracle
    private static String driverName; // = "oracle.jdbc.driver.OracleDriver";
    private static String url       ; // = "jdbc:oracle:thin:@" + server + ":" + porta + ":" + sid;
    
    // Dados do Servidor
    private static String server; // = "camburi.pucrs.br";
    private static int porta; // = "1521";
    private static String sid       ; // = "facin11g";
    private static String username  ; // = "login";
    private static String password  ; // = "senha";
    private static BancoDAO instancia;
    private static Connection conexao;
    
    
    public static BancoDAO novaConexao(
            String username,
            String password,
            String server,
            String porta,
            String sid
            ) throws BancoDAOExcepiton {
        
            
            instancia = new BancoDAO(username, password, server, porta, sid);

        
        return instancia;
    }
    
    
    private BancoDAO(
            String username,
            String password,
            String server,
            String porta2,
            String sid
            

    ) throws BancoDAOExcepiton{
        driverName = "oracle.jdbc.driver.OracleDriver";
        url        = "jdbc:oracle:thin:@";
        this.username = username;
        this.password= password;
        this.porta = Integer.parseInt(porta2);
        this.server=server;
        this.sid=sid;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            throw new BancoDAOExcepiton("JdbcOdbDriver not found!!");
        }
      //  boolean verificaSeExisteATabela = verificaSeExisteATabela();
     //   if (verificaSeExisteATabela){
        //cria instancia de tableas;
    //    }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public double getPorta() {
        return porta;
    }

    public void setPorta(String porta2) {
        this.porta = Integer.parseInt(porta2);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */

        public Connection getConnection() throws BancoDAOExcepiton  {
            url=url + server + ":" + porta + ":" + sid;
        try {
            conexao  =  DriverManager.getConnection(url, username, password);
            return conexao;
        } catch (SQLException ex) {
            throw  new BancoDAOExcepiton(ex.getMessage());
        }
  
    }

    
    
    @Override
    public ArrayList<Owner> buscaListaDeOwners() throws BancoDAOExcepiton {

        String sql;
        sql = "select distinct(OWNER) from ALL_TABLES a order by OWNER";

        String resultado = "";
        ArrayList<Owner> l = new ArrayList<Owner>();
        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                
                while (res.next()) {
                   String s=  res.getString(1);
                 //  int i = res.getInt(2);
                    l.add(new Owner(s));
                }
                res.close();
                sta.close();
            }
            
        } catch (SQLException ex) {
            
        }
    return l;
    }
    
    
    

 @Override
    public ArrayList<Table> buscaListaDeTabelasDoOwner(Owner o) throws BancoDAOExcepiton {
    ArrayList<Table> l = new ArrayList<Table>();
        if (o !=null){
        String sql;
             sql = "select table_name,"
             + "column_name,"
             + "data_type,"
             + "data_length,"
             + "data_precision,"
             + "nullable"
             + "from ALL_TAB_COLUMNS where owner = '"+o.getNome()+"'";

        String resultado = "";
        
        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);
                
                while (res.next()) {
                 
               //     l.add(new Table(res.getString(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6)));
                }
                res.close();
                sta.close();
            }
            
        } catch (SQLException ex) {
            
        }
    }
    return l;
    }
}