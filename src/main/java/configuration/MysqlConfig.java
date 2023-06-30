package configuration;

import java.sql.*;

public class MysqlConfig {
    public String host = "localhost";
    public String port = "3306";
    public String user = "root";
    public String pass = "";
    public String db = "develop";
    public String classDriver = "com.mysql.cj.jdbc.Driver";

    public Connection getMysqlConnection() throws ClassNotFoundException, SQLException{
        Class.forName(classDriver);
        String url ="jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pass;
        return DriverManager.getConnection(url);
    }

    public ResultSet getResulSet(CallableStatement callableStatement)throws SQLException{
        return callableStatement.executeQuery();
    }

}
