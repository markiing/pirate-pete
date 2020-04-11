/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirate.pete;

import java.util.Scanner;

/**
 *
 * @author Marcus
 */
public class Utils {

    //Open a prompt and return an value
    static public Object interact(String whatYouWantAsk, Scanner scn){
        System.out.print(whatYouWantAsk+":");
        Object toReturn = scn.nextLine();
        return toReturn;
    }
    
}
