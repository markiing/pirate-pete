/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete;

/**
 *
 * @author Marcus
 */
public class Menu {
    
     //Show Menu
    public static void show(){
        System.out.print("\033[H\033[2J");  //CLEAR OUT CONSOLE
        System.out.flush();
        System.out.println("1. Dig");
        System.out.println("2. Shovel Status");
        System.out.println("3. Show Map");
        System.out.println("4. Shop");
    }
}
