/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Date;

/**
 *
 * @author pedago
 */
public class PurchaseOrder {

    private int orderNum;
    private int customerId;
    private int productId;
    private int quantity;
    private double shippingCost;
    private Date salesDate;
    private Date shippingDate;
    private String freightCompany;
    
    public PurchaseOrder(int orderNum, int customerId, int productId, int quantity, double shippingCost, Date salesDate, Date shippingDate, String freightCompany)
    {
        this.orderNum = orderNum;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.shippingCost = shippingCost;
        this.salesDate = salesDate;
        this.shippingDate = shippingDate;
        this.freightCompany = freightCompany;
    }
    
    public PurchaseOrder(int customerId, int productId)
    {
        this.customerId = customerId;
        this.productId = productId;
    }
    
    public void setOrderNum(int orderNum)
    {
        this.orderNum = orderNum;
    }
    
    public int getOrderNum()
    {
        return this.orderNum;
    }    

    public int getCustomerId()
    {
        return this.customerId;
    }

    public int getProductId()
    {
        return this.productId;
    }
    
    public String getProductName()
    {
        IDAO dao = new DAO(DataSourceFactory.getDataSource());
        return dao.getProductName(productId);
    }

    public int getQuantity()
    {
        return this.quantity;
    }
    
    public double getShippingCost()
    {
        return this.shippingCost;
    }
    
    public Date getSalesDate()
    {
        return this.salesDate;
    }

    public Date getShippingDate()
    {
        return this.shippingDate;
    }
    
    public String getFreightCompany()
    {
        return this.freightCompany;
    }
}

