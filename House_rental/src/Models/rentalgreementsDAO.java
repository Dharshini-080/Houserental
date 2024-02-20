package Models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.propertiesDTO;
import Resources.rentalagreementsDTO;
import Resources.usersDTO;
import util.Cookie;

public class rentalgreementsDAO extends Connect
{

    private static rentalgreementsDAO instance;
    PreparedStatement view,getProperty,updatePropertyStatus,insertRentalAgreement,bookedtenants,bt,date,dt;
    private rentalgreementsDAO() throws SQLException 
    {
        view=con.prepareStatement("select*from properties");
        getProperty = con.prepareStatement("select * from properties where Property_id = ?");
        updatePropertyStatus = con.prepareStatement("update properties set Status = ? where Property_id = ?");
        insertRentalAgreement = con.prepareStatement("insert into rentalagreements (Start_date, End_date, Tenant_id, Property_id, Members) values (?, ?, ?, ?, ?)");
        bookedtenants=con.prepareStatement("SELECT * FROM users u JOIN rentalagreements r ON u.User_id = r.Tenant_id");
        bt=con.prepareStatement("SELECT * FROM rentalagreements WHERE Tenant_id = ? AND Property_id = ?");
        date=con.prepareStatement("SELECT * FROM RentalAgreements WHERE Property_id = ?");
        dt=con.prepareStatement("SELECT*FROM RentalAgreements");

        
    }
    public static rentalgreementsDAO getInstance() throws SQLException 
    {
        if (instance == null) {
            instance = new rentalgreementsDAO();
        }
        return instance;
    }

    public List<propertiesDTO> getAllProperty() throws SQLException 
    {
        ResultSet rs=view.executeQuery(); 
       List<propertiesDTO> propertiesList = new ArrayList<>();
       while (rs.next()) 
       {
        propertiesDTO property = new propertiesDTO();
        property.setProperty_id(rs.getInt("Property_id"));
        property.setTypeOfHouse(rs.getString("TypeOfHouse"));
        property.setWaterfacility(rs.getString("Waterfacility"));
        property.setStatus(rs.getInt("Status"));
        property.setRent(rs.getInt("Rent"));
        property.setLandlord_id(rs.getInt("Landlord_id"));
        propertiesList.add(property);
       }
      return propertiesList;
        
    }
    public propertiesDTO getProperty(int propertyId) throws SQLException {
        getProperty.setInt(1, propertyId);
        ResultSet rs = getProperty.executeQuery();
        if (rs.next()) {
            propertiesDTO property = new propertiesDTO();
            property.setProperty_id(rs.getInt("Property_id"));
            property.setTypeOfHouse(rs.getString("TypeOfHouse"));
            property.setWaterfacility(rs.getString("Waterfacility"));
            property.setStatus(rs.getInt("Status"));
            property.setRent(rs.getInt("Rent"));
            property.setLandlord_id(rs.getInt("Landlord_id"));
            return property;
        }
        return null;
    }

    public int updatePropertyStatus(int propertyId, int status) throws SQLException 
    {
        updatePropertyStatus.setInt(1, status);
        updatePropertyStatus.setInt(2, propertyId);
        return updatePropertyStatus.executeUpdate();
    }
    public int insertRentalAgreement(int propertyId, Date startDate, Date endDate, int members) throws SQLException {
        
        int Landlord_id=Cookie.userId;
        insertRentalAgreement.setDate(1, startDate);
        insertRentalAgreement.setDate(2, endDate);
        insertRentalAgreement.setInt(3, Landlord_id); 
        insertRentalAgreement.setInt(4, propertyId);
        insertRentalAgreement.setInt(5, members);
        return insertRentalAgreement.executeUpdate();
    }
    public List<usersDTO> getAllBookedTenants() throws SQLException 
    {
        List<usersDTO> bookedTenants = new ArrayList<>();
        ResultSet rs = bookedtenants.executeQuery();
        while (rs.next()) 
        {
            usersDTO tenant = new usersDTO();
            rentalagreementsDTO t=new rentalagreementsDTO();
            tenant.setUser_id(rs.getInt("User_id"));
            tenant.setUser_Name(rs.getString("User_name"));
            tenant.setPhone_no(rs.getString("Phone_no"));
            tenant.setAddress(rs.getString("Address"));
            bookedTenants.add(tenant);
        }
        return bookedTenants;

    }
    public boolean isPropertyBookedByTenant(int tenantId, int propertyId) throws SQLException 
    {
        bt.setInt(1, tenantId);
        bt.setInt(2, propertyId);
        ResultSet resultSet = bt.executeQuery();
        return resultSet.next();
    }
    public List<rentalagreementsDTO> getBookingsForProperty(int propertyId) throws SQLException 
    {
        date.setInt(1, propertyId);
    ResultSet resultSet = date.executeQuery();

    List<rentalagreementsDTO> bookings = new ArrayList<>();
    while (resultSet.next()) 
    {
        rentalagreementsDTO booking = new rentalagreementsDTO();
        booking.setRental_id(resultSet.getInt("Rental_id"));
        booking.setStart_date(resultSet.getDate("Start_date"));
        booking.setEnd_Date(resultSet.getDate("End_date"));
        booking.setTenant_id(resultSet.getInt("Tenant_id"));
        booking.setMembers(resultSet.getInt("Members"));
        bookings.add(booking);
    }
    return bookings;
    }
    public List<rentalagreementsDTO> getAllRentalAgreements() throws SQLException 
    {
        
    List<rentalagreementsDTO> bookings = new ArrayList<>();
    ResultSet resultSet = dt.executeQuery();
    while (resultSet.next()) 
    {
        rentalagreementsDTO booking = new rentalagreementsDTO();
        booking.setProperty_id(resultSet.getInt("Property_id"));
        booking.setRental_id(resultSet.getInt("Rental_id"));
        booking.setStart_date(resultSet.getDate("Start_date"));
        booking.setEnd_Date(resultSet.getDate("End_date"));
        booking.setTenant_id(resultSet.getInt("Tenant_id"));
        booking.setMembers(resultSet.getInt("Members"));
        bookings.add(booking);
    }
    return bookings;
    }
    

}

    
