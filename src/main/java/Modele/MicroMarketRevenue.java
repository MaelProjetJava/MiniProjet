package Modele;


public class MicroMarketRevenue {
    private String microMarket;
    private double revenue;
    
    MicroMarketRevenue(String microMarket, double revenue) {
        this.microMarket = microMarket;
        this.revenue = revenue;
    }
    
    public String getMicroMarket() {
        return microMarket;
    }
    
    public double getRevenue() {
        return revenue;
    }
}
