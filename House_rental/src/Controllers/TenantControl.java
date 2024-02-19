package Controllers;

import java.sql.SQLException;
import java.util.List;
import Models.tenantDAO; 
import Resources.propertiesDTO;

public class TenantControl 
{

    public List<propertiesDTO> getAllProperty() throws SQLException 
    {
        tenantDAO dao = tenantDAO.getInstance(); 
        List<propertiesDTO> properties = dao.getAllProperty(); 
        return properties;
    }

    public int bookProperty(int propertyId) throws SQLException 
    {
        tenantDAO dao = tenantDAO.getInstance();
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
        int insertTenantResult = dao.insertTenant(propertyId); 
        if (insertTenantResult != 1) 
        {
            return -3; 
        }
        return 1;

    }
}
