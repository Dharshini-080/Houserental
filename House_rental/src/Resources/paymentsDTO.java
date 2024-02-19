package Resources;
import java.util.Date;
public class paymentsDTO 
{
    private int Payment_id;
    private int Landlord_id;
    private int Property_id;
    private Date Date;
    
        public paymentsDTO(int Payment_id, int Landlord_id, int Property_id, Date Date) {
            this.Payment_id = Payment_id;
            this.Landlord_id = Landlord_id;
            this.Property_id = Property_id;
            this.Date = Date;
        }

    public int getPayment_id() {
        return this.Payment_id;
    }

    public void setPayment_id(int Payment_id) {
        this.Payment_id = Payment_id;
    }

    public int getLandlord_id() {
        return this.Landlord_id;
    }

    public void setLandlord_id(int Landlord_id) {
        this.Landlord_id = Landlord_id;
    }

    public int getProperty_id() {
        return this.Property_id;
    }

    public void setProperty_id(int Property_id) {
        this.Property_id = Property_id;
    }

    public Date getDate() {
        return this.Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    
}
