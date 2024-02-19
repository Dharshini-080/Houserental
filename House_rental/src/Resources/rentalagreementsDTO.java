package Resources;
import java.util.Date;
public class rentalagreementsDTO 
{
    private int Rental_id;
    private Date Start_date;
    private Date End_Date;
    private int Tenant_id;
    private int Property_id;
    private int members;

    public rentalagreementsDTO(){

    }

    public rentalagreementsDTO(int Rental_id, Date Start_date, Date End_Date, int Tenant_id, int Property_id, int members) {
        this.Rental_id = Rental_id;
        this.Start_date = Start_date;
        this.End_Date = End_Date;
        this.Tenant_id = Tenant_id;
        this.Property_id = Property_id;
        this.members = members;
    }

    public int getRental_id() {
        return this.Rental_id;
    }

    public void setRental_id(int Rental_id) {
        this.Rental_id = Rental_id;
    }

    public Date getStart_date() {
        return this.Start_date;
    }

    public void setStart_date(Date Start_date) {
        this.Start_date = Start_date;
    }

    public Date getEnd_Date() {
        return this.End_Date;
    }

    public void setEnd_Date(Date End_Date) {
        this.End_Date = End_Date;
    }

    public int getTenant_id() {
        return this.Tenant_id;
    }

    public void setTenant_id(int Tenant_id) {
        this.Tenant_id = Tenant_id;
    }

    public int getProperty_id() {
        return this.Property_id;
    }

    public void setProperty_id(int Property_id) {
        this.Property_id = Property_id;
    }

    public int getMembers() {
        return this.members;
    }

    public void setMembers(int members) {
        this.members = members;
    }
    


}