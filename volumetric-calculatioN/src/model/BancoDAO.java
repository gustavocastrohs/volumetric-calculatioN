package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author
 */
public class BancoDAO implements IBancoDAO {

    // Inicialização e conexão com o Banco Oracle
    private static String driverName; // = "oracle.jdbc.driver.OracleDriver";
    private static String url; // = "jdbc:oracle:thin:@" + server + ":" + porta + ":" + sid;

    // Dados do Servidor
    private static String server; // = "camburi.pucrs.br";
    private static int porta; // = "1521";
    private static String sid; // = "facin11g";
    private static String username; // = "login";
    private static String password; // = "senha";
//    private static BancoDAO instancia;
    private static Connection conexao;
    private static boolean primeiraVez = true;

    /*  
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
     */
    public BancoDAO(
            String username,
            String password,
            String server,
            String porta2,
            String sid
    ) throws BancoDAOExcepiton {
        driverName = "oracle.jdbc.driver.OracleDriver";
        url = "jdbc:oracle:thin:@";
        this.username = username;
        this.password = password;
        this.porta = Integer.parseInt(porta2);
        this.server = server;
        this.sid = sid;
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
    private Connection getConnection() throws BancoDAOExcepiton {
        if (primeiraVez) {
            url = url + server + ":" + porta + ":" + sid;
            primeiraVez = false;
        }
        try {
            conexao = DriverManager.getConnection(url, username, password);
            return conexao;
        } catch (SQLException ex) {
            throw new BancoDAOExcepiton(ex.getMessage());
        }

    }

    @Override
    public ArrayList<IOwner> buscaListaDeOwners() throws BancoDAOExcepiton {

        String sql;
        sql = "select distinct(OWNER) from ALL_TABLES a order by OWNER";

        String resultado = "";
        ArrayList<IOwner> l = new ArrayList<IOwner>();
        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(sql);

                while (res.next()) {
                    String s = res.getString(1);
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
    public ArrayList<IColumn> buscaListaDeTabelasDoOwnerComOsDados(IOwner owner,ITable tabela) throws BancoDAOExcepiton {
        ArrayList<IColumn> l = new ArrayList<IColumn>();
        if (tabela!=null ) {
            if (null != owner) {
                String sql;
                sql = "select "
                        + "column_name,"
                        + "data_type,"
                        + "data_length,"
                        + "data_precision,"
                        + "nullable"
                        + " from ALL_TAB_COLUMNS where  table_name='"+tabela.getTable_name()+"' and owner = '" + owner.getNome() + "'";
                
                String resultado = "";
                try (Connection con = getConnection()) {
                    Statement sta = con.createStatement();
                    ResultSet res = sta.executeQuery(sql);
                    
                    while (res.next()) {
                        //column_name
                        Column column = new Column();
                        String string1 = res.getString(1);
                        if (string1!=null && !string1.equalsIgnoreCase(""))
                            column.setColumn_name(string1);
                        //data_type
                        String string2 = res.getString(2);
                        if (string2!=null && !string2.equalsIgnoreCase(""))
                            column.setData_type(string2);
                        String string3 = res.getString(3);
                        //data_length
                        if (string3!=null && !string3.equalsIgnoreCase(""))
                            column.setData_length(string3);
                        String string4 = res.getString(4);
                        //data_precision
                        if (string4!=null && !string4.equalsIgnoreCase(""))
                            column.setData_precision(string4);
                        //nullable
                        String string5 = res.getString(5);
                        if (string5!=null && !string5.equalsIgnoreCase(""))

                            column.setNullable(string5);
                        
                        
                        l.add(column);
                    }
                    res.close();
                    sta.close();
                }catch (SQLException ex) {
                System.out.println(ex.getMessage());
                
                }
            }
        }
        return l;
    }

    @Override
    public ArrayList<ITable> buscaListaDeTabelasDoOwner(IOwner o) throws BancoDAOExcepiton {
        ArrayList<ITable> l = new ArrayList<ITable>();
        if (o != null) {
            String sql;
            sql = "select TABLE_NAME from ALL_TABLES where owner ='" + o.getNome() + "'";

            String resultado = "";

            try {

                try (Connection con = getConnection()) {
                    Statement sta = con.createStatement();
                    ResultSet res = sta.executeQuery(sql);

                    while (res.next()) {

                        l.add(new Table(res.getString("TABLE_NAME")));
                    }
                    res.close();
                    sta.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return l;
    }
}
