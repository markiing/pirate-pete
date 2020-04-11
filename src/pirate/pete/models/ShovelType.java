/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete.models;

import java.util.List;

/**
 *
 * @author Marcus
 */
public enum ShovelType {
    
    WOOD(3, 0, "Wood"),
    IRON(4, 0, "Iron"),
    GOLD(5, 0, "Gold"),
    DIAMOND(6, 0, "Diamond");
    
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
        return digCapacity * 5;
    }
    

    
}
