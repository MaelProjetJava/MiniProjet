package Modele;

public class CustomerRevenue {
    private String customerName;
    private long revenue;
    
    CustomerRevenue(Customer customer, long revenue) {
        this.customerName = customer.getName();
        this.revenue = revenue;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public long getRevenue() {
        return revenue;
    }
}
