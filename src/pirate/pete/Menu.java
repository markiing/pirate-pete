package pirate.pete;

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
