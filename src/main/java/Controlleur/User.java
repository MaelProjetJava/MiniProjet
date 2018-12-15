package Controlleur;

import Modele.Customer;

class User {
    private Customer customer;
    
    User() {
        customer = null;
    }
    
    User(Customer customer) {
        this.customer = customer;
    }
    
    public boolean isAdministrator() {
        return customer == null;
    }
    
    public boolean isCustomer() {
        return !isAdministrator();
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    protected void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
