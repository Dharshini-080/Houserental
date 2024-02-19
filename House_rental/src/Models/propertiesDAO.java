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
    PreparedStatement addProperty,dp,sp,sa,up,upw,upr;

    private propertiesDAO() throws SQLException 
    {
        addProperty=con.prepareStatement("INSERT INTO Properties(TypeOfHouse,Waterfacility,Status,Rent,Landlord_id)Values(?,?,?,?,?)");
        dp=con.prepareStatement("Delete from Properties where Property_id=?");
        sp=con.prepareStatement("select*from properties where Property_id=?");
        sa=con.prepareStatement("Select*from properties");
        up=con.prepareStatement("UPDATE Properties SET TypeOfHouse = ? WHERE Property_id = ?");
        upw=con.prepareStatement("UPDATE Properties SET Waterfacility = ? WHERE Property_id = ?");
        upr=con.prepareStatement("UPDATE Properties SET Rent = ? WHERE Property_id = ?");

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


    public boolean updatePropertyType(int p, String n) throws SQLException 
    {
        up.setString(1,n);
        up.setInt(2,p);
        up.executeUpdate();
        return true;
    }


    public boolean updatePropertyWaterFacility(int p, String newWaterFacility) throws SQLException 
    {
        upw.setString(1,newWaterFacility);
        upw.setInt(2,p);
        upw.executeUpdate();
        return true;
    }


    public boolean updatePropertyRent(int p, int newRent) throws SQLException
    {
        upr.setInt(1,newRent);
        upr.setInt(2,p);
        upr.executeUpdate();
        return true;
    } 
    
}
