package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.propertiesDTO;

public class propertiesDAO extends Connect
{
    private static propertiesDAO instance;
    PreparedStatement addProperty,dp,sp,sa;

    private propertiesDAO() throws SQLException 
    {
        addProperty=con.prepareStatement("INSERT INTO Properties(TypeOfHouse,Waterfacility,Status,Rent,Landlord_id)Values(?,?,?,?,?)");
        dp=con.prepareStatement("Delete from Properties where Property_id=?");
        sp=con.prepareStatement("select*from properties where Property_id=?");
        sa=con.prepareStatement("Select*from properties");
    }


    public static propertiesDAO getInstance() throws SQLException 
    {
       if(instance==null)
       {
       instance=new propertiesDAO();
       }
       return instance;
    }

    public int addProperty(propertiesDTO p) throws SQLException 
    {
        addProperty.setString(1,p.getTypeOfHouse());
        addProperty.setString(2,p.getWaterfacility());
        addProperty.setInt(3,p.getStatus());
        addProperty.setInt(4,p.getRent());
        addProperty.setInt(5,p.getLandlord_id());
        int ps=addProperty.executeUpdate();
        return ps;
       
    }


    public int Propertydelete(int property_id) throws SQLException 
    {
        dp.setInt(1,property_id);
        int s=dp.executeUpdate();
        return s;  
    }


    public propertiesDTO getProperty(int property_id) throws SQLException 
    {
        sp.setInt(1,property_id);
        ResultSet rs=sp.executeQuery();
       
        propertiesDTO property = null;
       if (rs.next()) {
        property = new propertiesDTO();
        property.setTypeOfHouse(rs.getString("TypeOfHouse"));
        property.setWaterfacility(rs.getString("Waterfacility"));
        property.setStatus(rs.getInt("Status"));
        property.setRent(rs.getInt("Rent"));
        property.setLandlord_id(rs.getInt("Landlord_id"));
     }
    return property;
        
    }


    public List<propertiesDTO> getAllProperty() throws SQLException 
    {
    
    ResultSet rs=sa.executeQuery(); 
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


    
    
}
