package Views;
import java.util.List;

import Controllers.UserControl;

import Resources.propertiesDTO;
import util.Input;


public class landlordView extends Input
{

    public void addProperty() throws Exception 
    {
       System.out.println("1.Add a new Property");
       System.out.println("2.Delete a Property");
       System.out.println("3.Show properties based on property_id");
       System.out.println("4.Show all Properties");
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
    
}
