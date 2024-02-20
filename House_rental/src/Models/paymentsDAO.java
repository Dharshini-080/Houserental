package Models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class paymentsDAO extends Connect 
{

    private static paymentsDAO instance;
    private PreparedStatement insertStatement;

    public paymentsDAO() throws SQLException {
        insertStatement = con.prepareStatement("INSERT INTO Payments (Landlord_id, Property_id, Date) VALUES (?, ?, ?)");
    }

    public static paymentsDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new paymentsDAO();
        }
        return instance;
    }

    public boolean insertPayment(int landlordId, int propertyId, Date date) throws SQLException {
        insertStatement.setInt(1, landlordId);
        insertStatement.setInt(2, propertyId);
        insertStatement.setDate(3, date);

        
        int rowsAffected = insertStatement.executeUpdate();
        return rowsAffected > 0;
    }
}
