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
public class ProductCode {

    private String code;
    private char discountCode;
    private String description;
    
    public ProductCode(String code, char discoundCode, String description)
    {
        this.code = code;
        this.discountCode = discountCode;
        this.description = description;
    }
    
    public String getCode()
    {
        return this.code;
    }
    
    public char getDiscountCode()
    {
        return this.discountCode;
    }
    
    public String getdescription()
    {
        return this.description;
    }
}
