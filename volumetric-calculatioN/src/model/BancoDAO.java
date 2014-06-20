package model;

import java.awt.List;
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
    private String server; // = "camburi.pucrs.br";
    private int porta; // = "1521";
    private String sid; // = "facin11g";
    private String username; // = "login";
    private String password; // = "senha";
//    private static BancoDAO instancia;
    private Connection conexao;
    private final boolean primeiraVez = true;

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
    }

    @Override
    public String getServer() {
        return server;
    }

    @Override
    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public double getPorta() {
        return porta;
    }

    @Override
    public void setPorta(String porta2) {
        this.porta = Integer.parseInt(porta2);
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    private Connection getConnection() throws BancoDAOExcepiton {
        try {
            conexao = DriverManager.getConnection("" + url + server + ":" + porta + ":" + sid + "", username, password);
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
    public ArrayList<IColumn> buscaListaDeTabelasDoOwnerComOsDados(IOwner owner, ITable tabela) throws BancoDAOExcepiton {
        ArrayList<IColumn> l = new ArrayList<IColumn>();
        if (tabela != null) {
            if (null != owner) {
                String sql;
                sql = "select "
                        + "column_name,"
                        + "data_type,"
                        + "data_length,"
                        + "data_precision,"
                        + "nullable"
                        + " from ALL_TAB_COLUMNS where  table_name='" + tabela.getTable_name() + "' and owner = '" + owner.getNome() + "'";

                String resultado = "";
                try (Connection con = getConnection()) {
                    Statement sta = con.createStatement();
                    ResultSet res = sta.executeQuery(sql);

                    while (res.next()) {
                        //column_name
                        Column column = new Column();
                        String string1 = res.getString(1);
                        if (string1 != null && !string1.equalsIgnoreCase("")) {
                            column.setColumn_name(string1);
                        }
                        //data_type
                        String string2 = res.getString(2);
                        if (string2 != null && !string2.equalsIgnoreCase("")) {
                            column.setData_type(string2);
                        }
                        String string3 = res.getString(3);
                        //data_length
                        if (string3 != null && !string3.equalsIgnoreCase("")) {
                            column.setData_length(string3);
                        }
                        String string4 = res.getString(4);
                        //data_precision
                        if (string4 != null && !string4.equalsIgnoreCase("")) {
                            column.setData_precision(string4);
                        }
                        //nullable
                        String string5 = res.getString(5);
                        if (string5 != null && !string5.equalsIgnoreCase("")) {
                            column.setNullable(string5);
                        }

                        l.add(column);
                    }
                    res.close();
                    sta.close();
                } catch (SQLException ex) {

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

    public boolean testaConexao() throws BancoDAOExcepiton, SQLException {
        String sql;
        sql = "select TABLE_NAME from ALL_TABLES ";
        Connection con = getConnection();
        Statement sta = con.createStatement();
        ResultSet res = sta.executeQuery(sql);

        while (res.next()) {
            break;
        }
        res.close();
        sta.close();
        return true;
    }

    
    public void dropAllTables() throws BancoDAOExcepiton {

        String sql1, sql2, sql3, sql4, sql5, sql6,sql7;
       
        sql1 = adicionaHeaderDropTable("TABLE OWNERS ");
        sql2 = adicionaHeaderDropTable("TYPE T_OWNER ");
        sql3 = adicionaHeaderDropTable("TYPE T_TABLES ");
        sql4 = adicionaHeaderDropTable("TYPE T_TABLE ");
        sql5 = adicionaHeaderDropTable("TYPE NEXTS_EST ");
        sql6 = adicionaHeaderDropTable("TYPE T_COLUMNS ");
        sql7 = adicionaHeaderDropTable("TYPE T_COLUMN ");
       

        ArrayList<String> l = new ArrayList<String>();
        l.add(sql1);
        l.add(sql2);
        l.add(sql3);
        l.add(sql4);
        l.add(sql5);
        l.add(sql6);
        l.add(sql7);

        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();

                for (String s : l) {
                    try {
                        sta.execute(s);
                    } catch (SQLException ex) {
                          System.out.println(s);
                          System.out.println(ex.getMessage());
                        continue;
                    }
                }
                sta.close();
            }
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }

    }

    private String adicionaHeaderDropTable(String tabela) {

        String saida = "DROP  " + tabela + "force" ;
        return saida;
    }

    public void createAllTables() throws BancoDAOExcepiton {

        String s1, s2, s3, s4, s5, s6, s7;

        s1 = createT_COLUMN();
        s2 = createT_COLUMNS();
        s3 = createNEXTS_EST();
        s4 = createT_TABLE();
        s5 = createT_TABLES();
        s6 = createT_OWNER();
        s7 = createOWNERS();

        ArrayList<String> l = new ArrayList<String>();
        l.add(s1);
        l.add(s2);
        l.add(s3);
        l.add(s4);
        l.add(s5);
        l.add(s6);
        l.add(s7);

        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();

                for (String s : l) {

                    try {
                        sta.execute(s);

                    } catch (SQLException ex) {
                        System.out.println(s);
                        System.out.println(ex.getMessage());
                        continue;

                    }

                }
                sta.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private String createT_COLUMN() {
        String saida = "CREATE TYPE T_COLUMN AS OBJECT\n"
                + "( \n"
                + "  COLUMN_NAME VARCHAR2(30),\n"
                + "  DATA_TYPE VARCHAR(30),\n"
                + "  DATA_LENGTH NUMBER,\n"
                + "  NULLABLE VARCHAR2(1),\n"
                + "  AVERAGE_SIZE NUMBER,\n"
                + "  PCT_NULL NUMBER\n"
                + ")";
        return saida;
    }

    private String createT_COLUMNS() {

        return "CREATE TYPE T_COLUMNS AS TABLE OF T_COLUMN";

    }

    private String createNEXTS_EST() {
        return "CREATE TYPE NEXTS_EST AS VARRAY(100) OF NUMBER";
    }

    private String createT_TABLE() {
        return "CREATE TYPE T_TABLE AS OBJECT\n"
                + "(\n"
                + "  TABLE_NAME VARCHAR2(30),\n"
                + "  INITIAL_ROWS NUMBER, \n"
                + "  PCT_GROWTH NUMBER,\n"
                + "  RETENTION_YEARS NUMBER,\n"
                + "  INITIAL_EST NUMBER,\n"
                + "  NEXT_EST_LIST NEXTS_EST,\n"
                + "  PCT_FREE NUMBER, \n"
                + "  PCT_USED NUMBER,\n"
                + "  T_COLUMNS_LIST T_COLUMNS\n"
                + ")";

    }

    private String createT_TABLES() {
        return "CREATE TYPE T_TABLES AS TABLE OF T_TABLE";
    }

    private String createT_OWNER() {
        return "CREATE TYPE T_OWNER AS OBJECT \n"
                + "(\n"
                + "  OWNER_NAME VARCHAR2(30),\n"
                + "  T_TABLES_LIST T_TABLES\n"
                + ")";
    }

    private String createOWNERS() {

        return "CREATE TABLE OWNERS OF T_OWNER \n"
                + "  NESTED TABLE T_TABLES_LIST STORE AS T_TABLES_NT\n"
                + "    (\n"
                + "      NESTED TABLE T_COLUMNS_LIST STORE AS T_COLUMNS_NT\n"
                + "    )";
    }
    
    
    @Override
    public void InsertIntoOwners(IOwner owner) throws BancoDAOExcepiton {
        VolumetricCalc vc = new VolumetricCalc();
        try {
            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                try {
                    sta.execute(vc.insert(owner));
                    /*
                    sta.execute(
                            "INSERT INTO owners VALUES ("
                            + "T_OWNER('" + owner.getNome() + "', NULL)"
                            + ")");
                    */
                } catch (SQLException ex) {
                      System.out.println(ex.getMessage());
                }
                sta.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    private String executeScript(String executeScript) throws BancoDAOExcepiton {
        String result = "";
        try {

            try (Connection con = getConnection()) {
                Statement sta = con.createStatement();
                ResultSet res = sta.executeQuery(executeScript);
                result = res.getNString(0);
                res.close();

                sta.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;


        
      
   
    }
   
}
