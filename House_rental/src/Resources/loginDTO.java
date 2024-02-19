package Resources;

public class loginDTO
{
    private int User_id;
    private String Email;
    private String Password;
    private String Role;

    public loginDTO(){
        
    }
    public loginDTO(int User_id, String Email, String Password, String Role) {
        this.User_id = User_id;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
    }
    public int getUser_id() {
        return this.User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
    
}