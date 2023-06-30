import configuration.MysqlConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    public static void main(String[]args)throws SQLException, ClassNotFoundException{

        //insertCliente();
        eliminarCliente();

    }

    public static void insertCliente() throws SQLException, ClassNotFoundException {

        MysqlConfig mysqlConfig = new MysqlConfig();
        Connection con = mysqlConfig.getMysqlConnection();
        String commandSql = "{ CALL develop.insert_cliente(?,?,?,?,?,?) }";

        CallableStatement cs = con.prepareCall(commandSql);
        cs.setString(1,"Martin");
        cs.setString(2,"Rivasplata");
        cs.setString(3,"Urrunaga");
        cs.setString(4,"1992-01-17");
        cs.setString(5,"marton@gmail.com");
        cs.setString(6,"lima");

        ResultSet resultSet = mysqlConfig.getResulSet(cs);

        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+" "+
                    resultSet.getString(2)+" "+
                    resultSet.getString(3)+" "+
                    resultSet.getString(4)+" "+
                    resultSet.getString(5)+" "+
                    resultSet.getString(6));
        }
    }


    public static void eliminarCliente()throws  SQLException,ClassNotFoundException{
        // Creamos el objeto de la clase MysqlConfig
        MysqlConfig mysqlConfig = new MysqlConfig();

        //Creamos el objeto Connection e instanciamos con el metodo getConexion
        Connection con = mysqlConfig.getMysqlConnection();

        //Creamos un String curo valor sea la ejecicion del procedimiento
        String callSP = "{ CALL develop.delete_cliente_correo(?) }";

        //Creamos el CallableStatement y ejecutamos la instruccion
        CallableStatement cs = con.prepareCall(callSP);

        //Enviamos los datos
        cs.setString(1,"marton@gmail.com");

        //Registramos los resultados
        int filasAfectadas =  cs.executeUpdate();

        //Imprimos el resultado
        if(filasAfectadas>0) {
            System.out.println("(" + filasAfectadas + ") filas han sido afectadas ...");
        }else{
            System.out.println("el cliente no existe");
        }
    }
}
