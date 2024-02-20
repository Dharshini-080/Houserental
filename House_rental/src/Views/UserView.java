package Views;

import Controllers.UserControl;
import util.Input;

public class UserView extends Input
{
    public void tosignUpLogin() throws Exception
    {
        boolean f=true;
        while(f)
        {
            try{
                System.out.println("Welcome to House Rental Management System");
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                
                sc.nextLine(); 
                switch(choice)
                {
                    case 1:
                    getLogin();
                    break;
            
                    case 2:
                    toSignUp();
                    break;
            
                    case 3:
                    System.out.println("Thank you for visiting");
                    break;
                }
            }
            catch(Exception err){
                System.out.println(err.getMessage());
            }
    }

    }
    public void getLogin() throws Exception
    {
        System.out.print("Enter email: ");
        String email=sc.nextLine();
        System.out.print("Enter password");
        String password=sc.nextLine();
        UserControl us=new UserControl();
        int check=us.login(email, password);
        if(check==1)
        {
            System.out.println("Landlord");
            landlordView U =new landlordView();
            U.addProperty();  


        }
        else{
            System.out.println("Tenant");
            tenantView U=new tenantView();
            U.buyProperty();
        }
    }
    public void toSignUp() throws Exception
    {
               System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                System.out.print("Enter phone number: ");
                String phone = sc.nextLine();
                
                System.out.print("Enter address: ");
                String address = sc.nextLine();
                UserControl us=new UserControl();
                int check=us.signUp(name,email,password,phone,"tenant",address);
                if(check==1)
                {
                    System.out.println("Signup Successfull");
                    tosignUpLogin();
                }
                else{
                    System.out.println("Signup failed");
                }
    }
}

