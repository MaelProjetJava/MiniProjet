package Modele;

import java.sql.Date;
import java.util.List;

public interface IDAO {
    Customer login(String login, String password);
    boolean updateCustomer(Customer newCustomerData);
    
    List<PurchaseOrder> getPurchaseOrders(Customer customer);
    PurchaseOrder getPurchaseOrder(int orderNum);
    boolean addPurchaseOrder(PurchaseOrder order);
    boolean deletePurchaseOrder(int orderNum);
    boolean updatePurchaseOrder(PurchaseOrder order);
    
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean deleteProduct(Product product);
    boolean updateProduct(Product product);
    String getProductName(int productId);
    
    List<Manufacturer> getAllManufacturers();
    List<ProductCode> getAllProductCodes();
    List<MicroMarket> getAllMicroMarkets();
    
    List<ProductCodeRevenue> getProductCodesRevenues(Date startDate,
                                                                Date endDate);
    List<MicroMarketRevenue> getMicroMarketsRevenues(Date startDate,
                                                                Date endDate);
    List<CustomerRevenue> getCustomersRevenues(Date startDate, Date endDate);
}
