package Controllers;
import java.sql.SQLException;
import java.util.List;

import Models.loginDAO;
import Models.propertiesDAO;
import Models.usersDAO;
import Resources.loginDTO;
import Resources.propertiesDTO;
import Resources.usersDTO;
import util.Cookie;
public class UserControl
{

    public int login(String Email,String Password) throws Exception
    {
        loginDAO login=loginDAO.getInstance();
        loginDTO user=login.getLoginData(Email);
        if(user==null)
        {
            throw new Exception("Invalid user");
        }
        if(!user.getPassword().equals(Password))
        {
            throw new Exception("Invalid Password");
        }
        Cookie.userId=user.getUser_id();
        if(user.getRole().equals("land lord"))
        {
            return 1;
        }
        return 2;
    }
    public int signUp(String User_Name,String Email,String Password,String Phone_no,String Role,String Address) throws SQLException
    {
    
        usersDAO sign=usersDAO.getInstance();
        
        usersDTO dummy=new usersDTO();

        dummy.setAddress(Address);
        dummy.setPhone_no(Phone_no);
        dummy.setRole(Role);
        dummy.setUser_Name(User_Name);
        
        int user1=sign.getsignUp(dummy);
        
        int j= sign.getId(Phone_no);

        loginDTO tummy=new loginDTO();
        tummy.setEmail(Email);
        tummy.setPassword(Password);

        int go=sign.getsignUp2(tummy,j);

        if(go==user1)
        return user1;
        return 2;
    }
    public int Property(String typeofhouse, String waterfacility, int status, int rent) throws SQLException
    {
        
        int Landlord_id=Cookie.userId;
        propertiesDAO add=propertiesDAO.getInstance();
        propertiesDTO p=new propertiesDTO();

        p.setTypeOfHouse(typeofhouse);
        p.setWaterfacility(waterfacility);
        p.setStatus(status);
        p.setRent(rent);
        p.setLandlord_id(Landlord_id);
        int g=add.addProperty(p);
        return g;
        
    }
    public int deleteProperty(int property_id) throws Exception 
    {
        propertiesDAO add=propertiesDAO.getInstance();
        int p=add.Propertydelete(property_id);
        if(p==0)
        {
            throw new Exception("Invalid Property");
        }
        return p;
    }
    public propertiesDTO getProperty(int property_id) throws SQLException 
    {
        propertiesDAO add = propertiesDAO.getInstance();
        propertiesDTO p = add.getProperty(property_id);
        return p;
    }
    public List<propertiesDTO> getAllProperty() throws SQLException
     {
        propertiesDAO add = propertiesDAO.getInstance();
        List<propertiesDTO> properties = add.getAllProperty();
        return properties;
    }
    public boolean updatePropertyType(int p, String n) throws SQLException 
    {
        propertiesDAO add = propertiesDAO.getInstance();
        return add.updatePropertyType(p, n);
    }
    public boolean updatePropertyWaterFacility(int p, String newWaterFacility) throws SQLException 
    {
        propertiesDAO add = propertiesDAO.getInstance();
        return add.updatePropertyWaterFacility(p, newWaterFacility);
    }
    public boolean updatePropertyRent(int p, int newRent) throws SQLException 
    {
        propertiesDAO add = propertiesDAO.getInstance();
        return add.updatePropertyRent(p, newRent);
    }
}
