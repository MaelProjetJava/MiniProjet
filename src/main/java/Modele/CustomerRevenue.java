package Modele;

public class CustomerRevenue {
    private String customer;
    private double revenue;
    
    CustomerRevenue(String customer, double revenue) {
        this.customer = customer;
        this.revenue = revenue;
    }
    
    public String getCustomer() {
        return customer;
    }
    
    public double getRevenue() {
        return revenue;
    }
}
