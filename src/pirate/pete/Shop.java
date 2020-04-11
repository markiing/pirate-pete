/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete;

import java.util.Scanner;
import pirate.pete.models.Player;
import pirate.pete.models.Shovel;
import pirate.pete.models.ShovelType;

/**
 *
 * @author Marcus
 */
public class Shop {
    private final ShovelType[] shovels = ShovelType.values();
    private final Scanner scn = new Scanner(System.in);
    
    void buy(Player player, ShovelType shovel){
        if(player.getPiratePoints() < shovel.getPrice()){
            throw new RuntimeException("You don't have money enought to buy this shovel.");
        }
        player.setShovel(new Shovel(shovel));
        player.setPiratePoints(player.getPiratePoints() - shovel.getPrice());
        System.out.println("You have bought "+shovel.getType()+" shovel.");
    }
    
    void show(Player player){
        System.out.println("--------------\t Shop \t--------------");
        for(int i = 0; i < shovels.length; i++){
            System.out.printf("%d. %s shovel (Price: %d PP, Capacity: %d digs)\n", i, shovels[i].getType(), shovels[i].getPrice(), shovels[i].getDigCapacity());
        }
        
        System.out.println("You have " + player.getPiratePoints() + "PP");
        int opt = Integer.parseInt(Utils.interact("Choose a number to buy (press 10 to back to menu)", scn).toString());
        
        try{
            if(opt == 10){
                Menu.show();
            }else{
                buy(player, shovels[opt]);
            }
        }catch(RuntimeException ex){
            System.err.println(ex.getMessage());
            show(player);
        }
        
        System.out.println("--------------\t Shop \t--------------");
    }
    
}
