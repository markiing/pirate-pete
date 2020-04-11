package pirate.pete;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import pirate.pete.exceptions.AgeException;
import pirate.pete.exceptions.DugException;
import pirate.pete.exceptions.GameOverException;
import pirate.pete.exceptions.PlayersRangeException;
import pirate.pete.exceptions.ShovelBrokenException;
import pirate.pete.models.Player;
import pirate.pete.models.TreasureMap;

/**
 * GameEngine is responsible to manage all the game status.
 * The game have the following pipeline
 * 1. Init players setup (How many players, players input...)
 * 2. Start the first turn with the first player -> 'players.get(0)'
 * 3. Recursivelly start turn with current or next player, depending on case.
 * 4. If the GameOverException is threw, the game is over :)
 */
public class GameEngine {
    
    private List<Player> players = new LinkedList<>(); //List of players
    final Scanner scn = new Scanner(System.in); //Global input scanner
    final TreasureMap tMap = new TreasureMap(); //Treasure map unique instance per game
    final Shop shop = new Shop(); // Shop
    
    
    void run(){
        try{
            initPlayersSetup();
            System.out.println("Continue");
            startTurn(players.get(0) /* START THE TURN FROM THE FIRST PLAYER*/ );
        }catch(AgeException ex){
            // Do Nothing
        }catch(IndexOutOfBoundsException ex){
            // Do Nothing
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
       } catch (GameOverException ex){
            System.out.println(ex.getMessage());
            exit();
       } catch(RuntimeException ex){
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
    public Player initPlayer(int index) throws AgeException{
        String firstName = Utils.interact("Hey player "+ (index + 1) + " how is your first name?", scn).toString();
        String surname = Utils.interact("And your surname? ", scn).toString();
        int age = Integer.parseInt(Utils.interact("And your age?", scn).toString()); //Parse input to int
        return new Player(firstName, surname, age);
    }
    
    
    
    public void initPlayersSetup(){
       try{
           int qtPlayers = Integer.parseInt(Utils.interact("How many players will join ?", scn).toString()); //Parse input to int
           if(qtPlayers < 2 || qtPlayers > 4) throw new PlayersRangeException("You have to play with min 2 peoples and max 4 peoples");
           
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
       } catch (PlayersRangeException ex){
           System.err.println(ex.getMessage());
           initPlayersSetup();
       }
    }
    
    void exit(){
        scn.close();
        System.exit(0);
    }
    
}
