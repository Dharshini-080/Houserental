package Models;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import util.Env;
public class Connect 
{
    protected static Connection con;
    protected static Statement st;
    public static void getConnection() throws SQLException{
        con=DriverManager.getConnection(Env.databaseURL, Env.databaseUser, Env.databasePassword);
        st=con.createStatement();
    }
}
