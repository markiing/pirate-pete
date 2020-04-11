/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete.models;

import pirate.pete.exceptions.AgeException;


public class Player {
    
    private static final int INITIAL_PIRATE_POINTS = 100;
   
    
    private Player nextPlayer;
    private String name;
    private String surname;
    private Integer age;
    private Shovel shovel;
    private Integer piratePoints;
    
    
    //Constructor to receive basics data of player (Name, Surname and Age)
    public Player(String name, String surname, Integer age){
        if(age < 12) throw new AgeException("You should have 12 years old or over"); //Validation for 12 YO Age
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.shovel = new Shovel(ShovelType.WOOD);
        this.piratePoints = INITIAL_PIRATE_POINTS - shovel.getPrice(); //Each player should be awarded 100 “Pirate Points” to start the game. 
        
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setShovel(Shovel shovel) {
        this.shovel = shovel;
    }

    public Integer getPiratePoints() {
        return piratePoints;
    }
    
    

    public void setPiratePoints(Integer piratePoints) {
        this.piratePoints = piratePoints;
    }
    

    public Player getNextPlayer() {
        return nextPlayer;
    }


    public String getName() {
        return name;
    }

    public Shovel getShovel() {
        return shovel;
    }
    
    public void addPiratePoints(Integer qtd){
        this.piratePoints += qtd;
    }
    
    
        
    public void dig(TreasureMap treasureMap, String col, Integer row){
        treasureMap.dig(this, col, row);
        System.out.printf(" > %s digged %s%d with %s shovel. \n",this.getName(), col, row, this.shovel.getType());
    }

    
    public void shovelStatus(){
        System.out.println("--------------\t Shovel Status \t--------------");
        System.out.println("Shovel Type: "+ this.shovel.getType());
        System.out.println("Dig Capacity: "+ this.shovel.getDigCapacity());
        System.out.println("Dig done: "+ this.shovel.getDigs());
        System.out.println("Digs remainings: "+ (this.shovel.getDigCapacity() - this.shovel.getDigs()));
        System.out.println("--------------\t Shovel Status \t--------------");
    }
    
    
    
}
