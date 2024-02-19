package Controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import Models.rentalgreementsDAO; 
import Resources.propertiesDTO;
import Resources.usersDTO;

public class TenantControl 
{

    public List<propertiesDTO> getAllProperty() throws SQLException 
    {
        rentalgreementsDAO dao = rentalgreementsDAO.getInstance(); 
        List<propertiesDTO> properties = dao.getAllProperty(); 
        return properties;
    }

    public int bookProperty(int propertyId, Date startDate, Date endDate, int members) throws SQLException 
    {
        rentalgreementsDAO dao = rentalgreementsDAO.getInstance();
        propertiesDTO property = dao.getProperty(propertyId);
        if(property==null)
        {
            return 0;
        }
        if(property.getStatus()!=1)
        {
            return -1;
        }

        int updateStatusResult = dao.updatePropertyStatus(propertyId,0);
        if(updateStatusResult!=1)
        {
            return -2;
        }

        int insertResult = dao.insertRentalAgreement(propertyId, startDate, endDate, members);
        if(insertResult != 1) {
            return -3;
        }
        return 1;

    }
    public List<usersDTO> getAllBookedTenants() throws SQLException 
    {
    rentalgreementsDAO dao = rentalgreementsDAO.getInstance();
    return dao.getAllBookedTenants();
     }
}