package Resources;

public class usersDTO 
{
    private int User_id;
    private String User_Name;
    private String Phone_no;
    private String Role;
    private String Address;
    

    public usersDTO() {
    }


    public usersDTO(int User_id, String User_Name, String Phone_no, String Role, String Address) {
        this.User_id = User_id;
        this.User_Name = User_Name;
        this.Phone_no = Phone_no;
        this.Role = Role;
        this.Address = Address;
        
    }


    public int getUser_id() {
        return this.User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public String getUser_Name() {
        return this.User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String getPhone_no() {
        return this.Phone_no;
    }

    public void setPhone_no(String Phone_no) {
        this.Phone_no = Phone_no;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    

}

