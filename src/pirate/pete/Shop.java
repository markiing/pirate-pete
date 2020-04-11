package pirate.pete;

import java.util.Scanner;
import pirate.pete.exceptions.NoPiratePointsEnoughtException;
import pirate.pete.models.Player;
import pirate.pete.models.Shovel;
import pirate.pete.models.ShovelType;

/**
 * Shop Interface
 */
public class Shop {
    private final ShovelType[] shovels = ShovelType.values(); //Shovel Types that player can bought
    private final Scanner scn = new Scanner(System.in);
    
    
    /**
     * Allow the player to buy spefic type of shovel
     * @param player
     * @param shovel 
     */
    void buy(Player player, ShovelType shovel){
        if(player.getPiratePoints() < shovel.getPrice()){
            //Money Check. If the player haven't pirate points enought, throw this exception.
            throw new NoPiratePointsEnoughtException("You don't have money enought to buy this shovel.");
        }
        
        player.setShovel(new Shovel(shovel)); //Update player shovel's
        player.setPiratePoints(player.getPiratePoints() - shovel.getPrice()); //Substract players piratepoints based on shovel price
        System.out.println("You have bought "+shovel.getType()+" shovel."); //Console status to player
    }
    
    /**
     * Show options to player.
     */
    void showOptions(){
        for(int i = 0; i < shovels.length; i++){
            //Show list of available shovels to buy.
            System.out.printf("%d. %s shovel (Price: %d PP, Capacity: %d digs)\n", i, shovels[i].getType(), shovels[i].getPrice(), shovels[i].getDigCapacity());
        }
    }
    
    /**
     * Trigger the options to buy and wait for an player input.
     * @param player 
     */
    void show(Player player){
        System.out.println("--------------\t Shop \t--------------");
        
        showOptions();
        System.out.println("You have " + player.getPiratePoints() + "PP");
        int opt = Integer.parseInt(Utils.interact("Choose a number to buy (press 10 to back to menu)", scn).toString());
        
        try{
            if(opt == 10){
                Menu.show();
            }else{
                buy(player, shovels[opt]);
            }
        }catch(NoPiratePointsEnoughtException ex){
            System.err.println(ex.getMessage());
            show(player);
        }
        
        System.out.println("--------------\t Shop \t--------------");
        scn.close();
    }
    
}
