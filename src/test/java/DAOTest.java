
import Modele.Customer;
import Modele.DAO;
import Modele.DAOException;
import Modele.DataSourceFactory;
import static Modele.DataSourceFactory.getDataSource;
import Modele.Product;
import Modele.PurchaseOrder;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;


public class DAOTest
{
    private DAO myDAO;
    private DataSource myDataSource;
    private static Connection myConnection ;

    @Before
    public void setUp() throws SQLException, IOException, SqlToolError
    {
	myDataSource = getDataSource();
	myConnection = myDataSource.getConnection();

        String sqlFilePath = DAOTest.class.getResource("TestSql.sql").getFile();
	SqlFile sqlFile = new SqlFile(new File(sqlFilePath));
	sqlFile.setConnection(myConnection);
	sqlFile.execute();
	sqlFile.closeReader();

        myDAO = new DAO(myDataSource);
    }
 
    @Test
    public void testGetPurchaseOrders() throws DAOException
    {
        Customer customer = new Customer(1);
        List<PurchaseOrder> result = myDAO.getPurchaseOrders(customer);
        assertEquals(2, result.size());
    }
    
    @Test
    public void testAddPurchaseOrder() throws DAOException
    {
        Customer customer = new Customer(1);
        List<PurchaseOrder> save = myDAO.getPurchaseOrders(customer);

        PurchaseOrder purchaseOrder = new PurchaseOrder(1, 980001);
        assertEquals(true, myDAO.addPurchaseOrder(purchaseOrder));

        List<PurchaseOrder> result = myDAO.getPurchaseOrders(customer);
        assertEquals(save.size() + 1, result.size());
    }
    
    @Test
    public void testLogin() throws DAOException
    {
        Customer customer = myDAO.login("jumboeagle@example.com", "1");
        assertEquals(customer.getEmail(), "jumboeagle@example.com");

        Customer fake = myDAO.login("fake@example.com", "1");
        assertEquals(null, fake);
    }
    
    @Test
    public void testUpdateCustomer() throws DAOException
    {
        Customer customer = myDAO.login("jumboeagle@example.com", "1");
        customer.setEmail("test@example.com");
        assertEquals(true, myDAO.updateCustomer(customer));
        customer = myDAO.login("test@example.com", "1");
        assertEquals(customer.getEmail(), "test@example.com");        
    }
    
    @Test
    public void testDeletePurchaseOrders() throws DAOException
    {    
        PurchaseOrder purchaseOrder = new PurchaseOrder(1, 980001);
        assertEquals(true, myDAO.addPurchaseOrder(purchaseOrder));
        List<PurchaseOrder> begin = myDAO.getAllPurchaseOrders();
        PurchaseOrder tmp = begin.get(begin.size() - 1);
        assertEquals(true, myDAO.deletePurchaseOrder(tmp.getOrderNum()));
        List<PurchaseOrder> end = myDAO.getAllPurchaseOrders();
        assertEquals(begin.size(), end.size() + 1);
    }
    
    @Test
    public void testUpdatePurchaseOrder() throws DAOException
    {
        PurchaseOrder purchaseOrder = new PurchaseOrder(36, 980001);
        purchaseOrder.setOrderNum(30198001);
        assertEquals(true, myDAO.updatePurchaseOrder(purchaseOrder));
    }
    
    @Test
    public void testGetAllProducts() throws DAOException
    {
        List<Product> lst = myDAO.getAllProducts();
        assertEquals(true, (lst != null));
    }
    
    @Test
    public void testAddProduct() throws DAOException
    {
        Product product = new Product(19985678, "SW");
        assertEquals(true, myDAO.addProduct(product));
    }
    
    @Test
    public void testDeleteProduct() throws DAOException
    {
        List<Product> begin = myDAO.getAllProducts();
        Product toDelete = begin.get(0);
        assertEquals(true, myDAO.deleteProduct(toDelete));
        assertEquals(begin.size() - 1, myDAO.getAllProducts().size());
    }
    
    @Test
    public void testUpdateProduct() throws DAOException
    {
        List<Product> begin = myDAO.getAllProducts();
        Product beginElem = begin.get(3);
        beginElem.setPurchaseCost(87.45);

        assertEquals(true, myDAO.updateProduct(beginElem));
        
        List<Product> end = myDAO.getAllProducts();
        Product endElem = begin.get(3);
        
        assertEquals(87.45, endElem.getPurchaseCost(), 0.01);
    }
    
    
    
    
    
    @After
    public void tearDown() throws IOException, SqlToolError, SQLException
    {
	myConnection.close();
     	myDAO = null;
    }
    
    public static DataSource getDataSource() throws SQLException
    {
	org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
	ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
	ds.setUser("app");
	ds.setPassword("app");
	return ds;
    }	
}
