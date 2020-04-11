package pirate.pete.models;


/**
 * Represents the ShovelTypes
 * @author Marcus
 */
public enum ShovelType {
    
    //Wood Shovel (Dig capacity = 3, Price = 15, Type = "Wood"
    WOOD(3, 15, "Wood"),
    //Iron Shovel (Dig capacity = 4, Price = 20, Type = "Iron"
    IRON(4, 20, "Iron"),
    //Gold Shovel (Dig capacity = 5, Price = 25, Type = "Gold"
    GOLD(5, 25, "Gold"),
    //Diamond Shovel (Dig capacity = 6, Price = 30, Type = "Diamond"
    DIAMOND(6, 30, "Diamond");
    
    private final String type;
    private final Integer digCapacity;
    private final Integer price;
    
    
    ShovelType(Integer digCapacity, Integer price, String type) {
        this.digCapacity = digCapacity;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Integer getDigCapacity() {
        return digCapacity;
    }

    public Integer getPrice() {
        return price;
    }
    

    
}
