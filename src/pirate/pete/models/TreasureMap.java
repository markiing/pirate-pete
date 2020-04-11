/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete.models;

import pirate.pete.exceptions.DugException;
import pirate.pete.exceptions.GameOverException;
import pirate.pete.exceptions.ShovelBrokenException;

/**
 * TreasureMap Abstraction. 
 * 
 * Treasure map is basically formed by an Grid 10x10 and two methods:
 * 
 * Show -> Render the map
 * Dig -> Dig an Cell given an col and a row.
 * 
 * @see Grid
 * 
 */
public class TreasureMap {
    
    /**
     * Grid instance of Treasure Map
     */
    final private Grid grid;
    final private int totalTreasures;
    private int treasuresFounded = 0;
    
    
    public TreasureMap(){
        this.grid = new Grid();
        this.totalTreasures = this.grid.getTotalTresures();
    }
    
    /**
     * Paint grid method abstraction
     */
    public void show(){
        this.grid.paint();
    }
    
    /**
     * Dig method Abstraction
     * 
     * @param player
     * @param col
     * @param row 
     */
    public void dig(Player player, String col, Integer row){
        if(player.getShovel().getDigs() >= player.getShovel().getDigCapacity()){
            //Throw this exception if the shovel is broken.
            throw new ShovelBrokenException("Argh,  Captain, me shovel has broken. I'll have to buy another one.");
        }else{
            Cell cell = this.grid.getCell(col, row); //Return an cell in cell matrix
            if(cell.getDug()){
                //If the cell aready been dug.
                throw new DugException("The coordinate "+col+row+" has already been dug by"+cell.getWhoDug());
            }else{
               //If the cell is available to dig
               cell.setDug(Boolean.TRUE); //update status to dug,
               cell.setWhoDug(player.getName()); //set who dug,
               this.grid.update(cell); //update the grid,
               show(); //repaint the Grid,
               player.getShovel().incrementDigs(); //and increment shovel digs.
               
               
               if(cell.haveTreasure()){
                   //if theres a treasure on cell choosed
                   player.addPiratePoints(20); //add pirate points to player
                   treasuresFounded++; //increments all treasures founded
                   System.out.println("Yo-ho-ho and a bottle of rum. I found me some pieces of eight."); // Show feedback to player
               }else{
                   System.out.println("Walk the plank! There be no treasure here!");
               }
               
               if(treasuresFounded == totalTreasures){
                   //If all treasures have found, throe GameOverException
                   throw new GameOverException("There is no more treasures to be found.");
               }
               
         }
      }
        
    }
    
    
}
