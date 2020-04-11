package pirate.pete;

import java.util.Scanner;

public class Utils {

    //Open a prompt and return an value
    static public Object interact(String whatYouWantAsk, Scanner scn){
        System.out.print(whatYouWantAsk+":");
        Object toReturn = scn.nextLine();
        return toReturn;
    }
    
}
