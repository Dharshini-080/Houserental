
import java.sql.SQLException;
import Models.Connect;
import Views.UserView;
import util.Input;
public class App extends Input
 {
    public static void main(String[] args) throws Exception 
    {
        try
        {
            Connect.getConnection();
            //System.out.println("connected");
        }
        catch(SQLException err)
        {
            System.out.println("Database Connection error");
            System.out.println(err);
        }
        UserView U =new UserView();
        U.tosignUpLogin();
    }
}
