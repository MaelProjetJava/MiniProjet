package Modele;


public class MicroMarketRevenue {
    private String microMarketZip;
    private long revenue;
    
    MicroMarketRevenue(MicroMarket microMarket, long revenue) {
        this.microMarketZip = microMarket.getZipCode();
        this.revenue = revenue;
    }
    
    public String getMicroMarketZip() {
        return microMarketZip;
    }
    
    public long getRevenue() {
        return revenue;
    }
}
