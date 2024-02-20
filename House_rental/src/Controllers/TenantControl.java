package Controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Models.paymentsDAO;
import Models.rentalgreementsDAO; 
import Resources.propertiesDTO;
import Resources.rentalagreementsDTO;
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
        TenantControl u=new TenantControl();
        boolean c1=u.bookcheckProperty1(propertyId);
        boolean c2=u.bookcheckProperty2(propertyId, startDate, endDate);
        if(c1 || c2)
        {
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
        return 9;
        

    }
    public boolean bookcheckProperty1(int propertyId) throws SQLException
    {
        rentalgreementsDAO dao = rentalgreementsDAO.getInstance();
        propertiesDTO property = dao.getProperty(propertyId);
        if(property==null)
        {
            return false;
        }
        if(property.getStatus()!=1)
        {

            return true;
        }
        return false;
    }
    public boolean bookcheckProperty2(int propertyId,Date startDate, Date endDate) throws SQLException{
        rentalgreementsDAO dao = rentalgreementsDAO.getInstance();
        List<rentalagreementsDTO> existingBookings = dao.getBookingsForProperty(propertyId);
      for(rentalagreementsDTO booking : existingBookings) {
        Date bookedStartDate = (Date) booking.getStart_date();
        Date bookedEndDate = (Date) booking.getEnd_Date();
        
        if(startDate.before(bookedEndDate) && endDate.after(bookedStartDate)) 
        {
            return false; 
        }

       }
       return true;
    }
    public List<usersDTO> getAllBookedTenants() throws SQLException 
    {
    rentalgreementsDAO dao = rentalgreementsDAO.getInstance();
    return dao.getAllBookedTenants();
     }

     public boolean makePayment(int tenantId, int landlordId,int propertyId ,Date date) throws SQLException {
        rentalgreementsDAO dao = rentalgreementsDAO.getInstance();
        boolean isBooked = dao.isPropertyBookedByTenant(tenantId, propertyId);
        if (!isBooked) 
        {
            return false; 
        }
        paymentsDAO paymentsDao = paymentsDAO.getInstance();
        return paymentsDao.insertPayment(landlordId, propertyId, date);
    }

    public List<rentalagreementsDTO> getAllRentalAgreements() throws SQLException
     {
        rentalgreementsDAO dao = rentalgreementsDAO.getInstance();
         return dao.getAllRentalAgreements();
    }

}