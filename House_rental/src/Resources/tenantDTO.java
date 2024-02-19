package Resources;

public class tenantDTO 
{
    private int Tenant_id;
    private int Property_id;

    public tenantDTO(int Tenant_id, int Property_id) {
        this.Tenant_id = Tenant_id;
        this.Property_id = Property_id;
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

}
