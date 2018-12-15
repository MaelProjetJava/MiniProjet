package Modele;

public class Manufacturer {
    private final int id;
    private final String name;
    
    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
