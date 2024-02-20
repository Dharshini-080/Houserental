package Views;
import java.sql.SQLException;
import java.util.List;

import Controllers.TenantControl;
import Controllers.UserControl;

import Resources.propertiesDTO;
import Resources.usersDTO;
import util.Input;


public class landlordView extends Input
{

    public void addProperty() throws Exception 
    {
       boolean y=true;
       while(y)
       {
       System.out.println("1.Add a new Property");
       System.out.println("2.Delete a Property");
       System.out.println("3.Show properties based on property_id");
       System.out.println("4.Show all Properties");
       System.out.println("5.Update property");
       System.out.println("6.Show all the details of tenants who have booked the house");
       System.out.println("7.Logout");
       int choice=sc.nextInt();
       sc.nextLine();
       if(choice==1)
       {
          getProp();
       }
        else if(choice==2)
       {
            delProp();
       }
        else if(choice==3)
       {
            showProp();
       }
        else if(choice==4)
       {
            showAll();
       }
        else if(choice==5)
       {
            update();
       }
        else if(choice==6)
       {
        viewAllBookedTenants();
       }
       else if(choice==7)
       {
        System.out.println("Thank You");
        y=false;
       }
       else
       {
        System.out.println("Invalid choice");
       }
       
    }

    }
    
    public void getProp() throws Exception
    {
        System.out.println("Enter the type of house");
        String Typeofhouse=sc.nextLine();
        System.out.println("Enter the type of water facility");
        String Waterfacility =sc.nextLine();
        System.out.println("Enter the status of house");
        int Status =sc.nextInt();
        System.out.println("Enter the Rent of the house");
        int Rent=sc.nextInt();
        UserControl us=new UserControl();
        int check=us.Property(Typeofhouse,Waterfacility,Status,Rent);
        if(check==1)
        {
            System.out.println("Property added successfully");
        }
        else
        {
            System.out.println("Adding of property failed");
        } 
    }
    public void delProp() throws Exception
    {
        System.out.println("Enter Property ID to delete the property");
        int Property_id=sc.nextInt();
        UserControl us=new UserControl();
        int check=us.deleteProperty(Property_id);
        if(check==1)
        {
            System.out.println("Property deleted successfully");
        }
        else
        {
            System.out.println("Deleting the property failed");
        }

    }
    private void showProp() throws Exception 
    {
        
        System.out.println("Enter Property ID to display Property details");
        int Property_id=sc.nextInt();
        UserControl us=new UserControl();
        propertiesDTO property = us.getProperty(Property_id);
        if(property!=null)
        {
            System.out.println("Displayed the details of entered property ID:");
            System.out.println("-----------------------------------------------");
            System.out.println("Property ID: " + Property_id);
            System.out.println("Type of House: " + property.getTypeOfHouse());
            System.out.println("Water Facility: " + property.getWaterfacility());
            System.out.println("Status: " + property.getStatus());
            System.out.println("Rent: " + property.getRent());
            System.out.println("Landlord ID: " + property.getLandlord_id());
            System.out.println("-----------------------------------------------");
        }
        else
        {
            System.out.println("No property found");
        }
    }
    public void showAll() throws Exception
    {
        
        UserControl us=new UserControl();
        List<propertiesDTO> properties = us.getAllProperty();
        if(!properties.isEmpty())
        {
            System.out.println("Displayed the details of entered property ID:");
            System.out.println("-----------------------------------------------");
            for(propertiesDTO property:properties)
            {
            System.out.println("Property ID: " + property.getProperty_id());
            System.out.println("Type of House: " + property.getTypeOfHouse());
            System.out.println("Water Facility: " + property.getWaterfacility());
            System.out.println("Status: " + property.getStatus());
            System.out.println("Rent: " + property.getRent());
            System.out.println("Landlord ID: " + property.getLandlord_id());
            System.out.println("-----------------------------------------------");
            }
        }
        else
        {
            System.out.println("No property found");
        }
    }
    private void update() throws SQLException 
    {
        System.out.println("Enter property ID to update");
        int p=sc.nextInt();
        UserControl us=new UserControl();
        propertiesDTO check=us.getProperty(p);
        if(check==null)
        {
            System.out.println("Property does not exist");
        }
        else
        {
            boolean updating =true;
            while(updating)
            {
                System.out.println("What would you like to update?");
                System.out.println("1.TypeofHouse");
                System.out.println("2.Waterfacility");
                System.out.println("3.Rent");
                System.out.println("4.Done updating");
                int upc=sc.nextInt();
                switch(upc)
                {
                    case 1:
                    System.out.println("Existing Type of House: " + check.getTypeOfHouse());
                    System.out.println("Enter the new typeofhouse");
                    String n=sc.next();
                    boolean updated=us.updatePropertyType(p,n);
                    if(updated)
                    {
                        System.out.println("New type of house updation successfull");
                    }
                    else
                    {
                        System.out.println("Failed to update type of house");
                    }
                    break;
                    case 2:
                    System.out.println("Existing Water Facility: " + check.getWaterfacility());
                    System.out.println("Enter the new water facility:");
                    String newWaterFacility = sc.next();
                    boolean waterFacilityUpdated = us.updatePropertyWaterFacility(p, newWaterFacility);
                    if(waterFacilityUpdated)
                    {
                        System.out.println("New water facility updation successful");
                    }
                    else
                    {
                        System.out.println("Failed to update water facility");
                    }
                    break;
                    case 3:
                    System.out.println("Existing Rent: " + check.getRent());
                    System.out.println("Enter the new rent:");
                    int newRent = sc.nextInt();
                    boolean rentUpdated = us.updatePropertyRent(p, newRent);
                    if(rentUpdated)
                    {
                        System.out.println("New rent updation successful");
                    }
                    else
                    {
                        System.out.println("Failed to update rent");
                    }
                    break;
                case 4:
                    updating = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;

                }
                
            }
        }
    }
    public void viewAllBookedTenants() throws SQLException 
    {
        TenantControl tenantControl = new TenantControl();
    List<usersDTO> bookedTenants = tenantControl.getAllBookedTenants();
    if (!bookedTenants.isEmpty()) {
        System.out.println("Booked Tenants:");
        for (usersDTO tenant : bookedTenants) {
            System.out.println("Tenant ID: " + tenant.getUser_id());
            System.out.println("Tenant Name: "+tenant.getUser_Name());
            System.out.println("Tenant Phone number: "+tenant.getPhone_no());
            System.out.println("Tenant Address: "+tenant.getAddress());
        }
    } else {
        System.out.println("No tenants are currently booked.");
    }
    }
    
}
