package Resources;

public class propertiesDTO 
{
    private int Property_id;
    private String TypeOfHouse;
    private String Waterfacility;
    private int Status;
    private int Rent;
    private int Landlord_id;

    public propertiesDTO(){
        
    }

    public propertiesDTO(int Property_id, String TypeOfHouse, String Waterfacility, int Status, int Rent, int Landlord_id) {
        this.Property_id = Property_id;
        this.TypeOfHouse = TypeOfHouse;
        this.Waterfacility = Waterfacility;
        this.Status = Status;
        this.Rent = Rent;
        this.Landlord_id = Landlord_id;
    }

    public int getProperty_id() {
        return this.Property_id;
    }

    public void setProperty_id(int Property_id) {
        this.Property_id = Property_id;
    }

    public String getTypeOfHouse() {
        return this.TypeOfHouse;
    }

    public void setTypeOfHouse(String TypeOfHouse) {
        this.TypeOfHouse = TypeOfHouse;
    }

    public String getWaterfacility() {
        return this.Waterfacility;
    }

    public void setWaterfacility(String Waterfacility) {
        this.Waterfacility = Waterfacility;
    }

    public int getStatus() {
        return this.Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getRent() {
        return this.Rent;
    }

    public void setRent(int Rent) {
        this.Rent = Rent;
    }

    public int getLandlord_id() {
        return this.Landlord_id;
    }

    public void setLandlord_id(int Landlord_id) {
        this.Landlord_id = Landlord_id;
    }


}