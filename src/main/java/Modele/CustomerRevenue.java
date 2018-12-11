package Modele;

public class CustomerRevenue {
    private String customerName;
    private long revenue;
    
    public CustomerRevenue(Customer customer, long revenue) {
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
