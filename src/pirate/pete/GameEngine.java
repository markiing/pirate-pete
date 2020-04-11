/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import pirate.pete.exceptions.AgeException;
import pirate.pete.exceptions.DugException;
import pirate.pete.exceptions.ShovelBrokenException;
import pirate.pete.models.Player;
import pirate.pete.models.TreasureMap;

/**
 *
 * @author Marcus
 */
public class GameEngine {
    
    private List<Player> players = new LinkedList<>();
    final Scanner scn = new Scanner(System.in);
    final TreasureMap tMap = new TreasureMap();
    final Shop shop = new Shop();
    
    
    void run(){
        try{
            initPlayersSetup();
            startTurn(players.get(0) /* START THE TURN FROM THE FIRST PLAYER*/ );
        }catch(RuntimeException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    
    public void startTurn(Player player){
        try{
            System.out.printf("> %s turns \n", player.getName());
            Menu.show();
            int option = Integer.parseInt(Utils.interact("Choose an option", scn).toString()); //Parse input to int
            
            switch(option){
                case 1: dig(player); break;
                case 2: player.shovelStatus(); startTurn(player); break;
                case 3: tMap.show(); startTurn(player); break;
                case 4: shop.show(player); startTurn(player);break;
                default: throw new RuntimeException("");
            }

        }catch(NumberFormatException | ClassCastException ex) {
           System.err.println("You should provide a valid number ");
            startTurn(player);
       }catch(RuntimeException ex){
           System.err.println(ex.getMessage());
       }
    }
    
         
    
    public void dig(Player player){
        try{
            String col = Utils.interact("Select the column to dig (A, B, C...)", scn).toString(); // Select the column to dig
            int row = Integer.parseInt(Utils.interact("Select the row to dig (1, 2, 3...)", scn).toString()); // Select thr row to dig
            player.dig(tMap, col, row);
            startTurn(player.getNextPlayer());
        }catch(DugException ex){
            System.err.println(ex.getMessage());
            dig(player);
        }catch(ShovelBrokenException ex){
            System.err.println(ex.getMessage());
            startTurn(player.getNextPlayer());
        }catch(NumberFormatException | ClassCastException ex) {
           System.err.println("You should provide a valid number ");
           dig(player);
       }
            
    }
   

    //Player instanciation method.
    public Player initPlayer(int index){
        String firstName = Utils.interact("Hey player "+ (index + 1) + " how is your first name?", scn).toString();
        String surname = Utils.interact("And your surname? ", scn).toString();
        int age = Integer.parseInt(Utils.interact("And your age?", scn).toString()); //Parse input to int
        return new Player(firstName, surname, age);
    }
    
    
    
    public void initPlayersSetup(){
       try{
           int qtPlayers = Integer.parseInt(Utils.interact("How many players will join ?", scn).toString()); //Parse input to int
           if(qtPlayers < 2 || qtPlayers > 4) throw new RuntimeException("You have to play with min 2 peoples and max 4 peoples");
           
           for(int i = 0; i < qtPlayers; i++){ 
               Player player = initPlayer(i);
               players.add(player);
           }

           for(int i = 0; i < players.size(); i++){
               if(i == players.size() - 1) players.get(i).setNextPlayer(players.get(0));
               else players.get(i).setNextPlayer(players.get(i + 1));
           }
       }catch(NumberFormatException | ClassCastException ex) {
           System.err.println("You should provide a valid number ");
           initPlayersSetup();
       }catch(AgeException ex){
           System.err.println(ex.getLocalizedMessage());
           exit();
       }
    }
    
    
    

    void exit(){
        scn.close();
    }
    
}
