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
public class MicroMarket {
    
    private String zipCode;
    private double radius;
    private double lenght;
    private double width;
    
    public MicroMarket(String zipCode, double radius, double lenght, double width)
    {
        this.zipCode = zipCode;
        this.radius = radius;
        this.lenght = lenght;
        this.width = width;
    }
    
    
    public String getZipCode()
    {
        return this.zipCode;
    }
    
    public double getRadius()
    {
        return this.radius;
    }
    
    public double getLenght()
    {
        return this.lenght;
    }
    
    public double getWidth()
    {
        return this.width;
    }
}
