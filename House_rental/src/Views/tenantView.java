package Views;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Controllers.TenantControl;
import Resources.propertiesDTO;
import Resources.rentalagreementsDTO;
import util.Cookie;
import util.Input;
public class tenantView extends Input
{

    public void buyProperty() throws SQLException 
    {
        boolean m=true;
        while(m)
        {
        System.out.println("**************************************************************");
        System.out.println("*                1.View all Property                         *");
        System.out.println("*                2.View the details of booked property       *");
        System.out.println("*                3.Book a Property                           *");
        System.out.println("*                4.Make Payment                              *");
        System.out.println("*                5.Logout                                    *");
        System.out.println("**************************************************************");
        int choice=sc.nextInt();
        if(choice==1)
        {
            viewAllProperties();
        }
        else if(choice==2)
        {
            viewAllRentalagreements();
        }
        else if(choice==3)
        {
            bookProperty();
        }
        else if(choice==4)
        {
            makePayment();
        }
        else if(choice==5)
        {
            System.out.println("Thank you for visiting");
            m=false;
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }
    }


    


    private void viewAllProperties() throws SQLException 
    {
        TenantControl tenantControl = new TenantControl(); 
        List<propertiesDTO> properties = tenantControl.getAllProperty(); 
        
        if(!properties.isEmpty())
        {
            System.out.println("Displaying all properties:");
            System.out.println("-----------------------------------------------");
            for(propertiesDTO property : properties)
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
            System.out.println("No properties found");
        }
    }
    private void bookProperty() throws SQLException
    {
        System.out.println("Enter property ID to book");
        int propertyId=sc.nextInt();

        System.out.println("Enter start date (YYYY-MM-DD): ");
        String startDateStr = sc.next();
        Date startDate = Date.valueOf(startDateStr);
    
       System.out.println("Enter end date (YYYY-MM-DD): ");
       String endDateStr = sc.next();
       Date endDate = Date.valueOf(endDateStr);

       System.out.println("Enter number of family members: ");
        int members = sc.nextInt();

        TenantControl tenantControl = new TenantControl(); 
        int result = tenantControl.bookProperty(propertyId,startDate,endDate,members);   
        if (result == 1) 
        {
            System.out.println("Property booked successfully");
        } 
        else if (result == 0)
        {
            System.out.println("Property does not exist");
        } 
        else if (result == -1 || result==9) 
        {
            System.out.println("Property is not available for booking");
        } 
        else 
        {
            System.out.println("Failed to book property");
        }
    
    }
    private void makePayment() throws SQLException 
    {
        System.out.println("Enter landlord ID: ");
        int landlordId = sc.nextInt();

        System.out.println("Enter property ID: ");
        int propertyId = sc.nextInt();

        System.out.println("Enter payment date (YYYY-MM-DD): ");
        String dateStr = sc.next();
        Date date = Date.valueOf(dateStr);
        int tenantId=Cookie.userId;

        TenantControl tenantControl = new TenantControl();
        boolean success = tenantControl.makePayment(tenantId,landlordId, propertyId, date);
        if (success) {
            System.out.println("Payment made successfully");
        } else {
            System.out.println("Failed to make payment.Please book the property and make the payemnt");
        }
    }
    private void viewAllRentalagreements() throws SQLException 
    {
        TenantControl tenantControl = new TenantControl();
        List<rentalagreementsDTO> t=tenantControl.getAllRentalAgreements();
        if (!t.isEmpty()) {
        System.out.println("Booked Tenants:");
        for (rentalagreementsDTO tenant : t) {
            System.out.println("Property ID: "+tenant.getProperty_id());
            System.out.println("Rental ID: " + tenant.getRental_id());
            System.out.println("Start_date: "+tenant.getStart_date());
            System.out.println("End_date: "+tenant.getEnd_Date());
            System.out.println("Tenant_id: "+tenant.getTenant_id());
            System.out.println("----------------------------------------");
        }
    } 
    else {
        System.out.println("No rental agreements done yet");
    }
    }
    
}
