package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.propertiesDTO;
import util.Cookie;

public class tenantDAO extends Connect
{

    private static tenantDAO instance;
    PreparedStatement view,getProperty,updatePropertyStatus,insertTenant;
    private tenantDAO() throws SQLException 
    {
        view=con.prepareStatement("select*from properties");
        getProperty = con.prepareStatement("select * from properties where Property_id = ?");
        updatePropertyStatus = con.prepareStatement("update properties set Status = ? where Property_id = ?");
        insertTenant = con.prepareStatement("insert into tenant (Tenant_id, Property_id) values (?, ?)");
    }
    public static tenantDAO getInstance() throws SQLException 
    {
        if (instance == null) {
            instance = new tenantDAO();
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
    
    public int insertTenant(int propertyId) throws SQLException 
    {
        int Landlord_id=Cookie.userId;
        insertTenant.setInt(1, Landlord_id); 
        insertTenant.setInt(2, propertyId);
        return insertTenant.executeUpdate();
    }
}

    
