/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete.models;


public class Shovel {
    
    private Integer digs = 0;
    private Integer digCapacity;
    private Integer price;
    private String type;

    public Shovel(ShovelType shovelType) {
        this.digCapacity = shovelType.getDigCapacity();
        this.price = shovelType.getPrice();
        this.type = shovelType.getType();
    }

    public String getType() {
        return type;
    }

    public Integer getDigs() {
        return digs;
    }

    public Integer getDigCapacity() {
        return digCapacity;
    }
    
    public void incrementDigs(){
        this.digs ++;
    }

    public Integer getPrice() {
        return price;
    }
    
    
    
    
    
}
