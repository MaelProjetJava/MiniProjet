package Modele;


public class ProductCodeRevenue {
    private String productCode;
    private double revenue;
    
    ProductCodeRevenue(String productCode, double revenue) {
        this.productCode = productCode;
        this.revenue = revenue;
    }
    
    public String getProductCode() {
        return productCode;
    }
    
    public double getRevenue() {
        return revenue;
    }
}
