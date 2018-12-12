/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author pedago
 */
public class Product {

    private int id;
    private int manufacturerId;
    private String productCode;
    private double purchaseCost;
    private int quantityOnHand;
    private double markup;
    private boolean available;
    private String description;
    
    public Product(int id, int manufacturerId, String productCode, double purchaseCost, int quantityOnHand, double markup, String available, String description)
    {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.productCode = productCode;
        this.purchaseCost = purchaseCost;
        this.quantityOnHand = quantityOnHand;
        this.markup = markup;
        this.available = available.equals("TRUE") ? true : false;
        this.description = description;
    }

    public Product(int manufacturerId, String productCode)
    {
        this.manufacturerId = manufacturerId;
        this.productCode = productCode;
    }

    public void setPurchaseCost(double purchaseCost)
    {
        this.purchaseCost = purchaseCost;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public int getManufacturerId()
    {
        return this.manufacturerId;
    }
    
    public String getProductCode()
    {
        return this.productCode;
    }
    
    public double getPurchaseCost()
    {
        return this.purchaseCost;
    }
    
    public int getQuantityOnHand()
    {
        return this.quantityOnHand;
    }
    
    public double getMarkup()
    {
        return this.markup;
    }
    
    public boolean isAvailable()
    {
        return this.available;
    }
    
    public String getDescription()
    {
        return this.description;
    }
}
