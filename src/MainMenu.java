public class MainMenu {
    public static String[] items = {"Find and reserve a room", "See my reservations", "Create an account", "Admin", "Exit"};
    public static void show(String prettier) {
        System.out.println(prettier);
        for(int i=0; i<items.length; i++) {
            System.out.println((i+1) + ". " + items[i]);
        }
        System.out.println(prettier);
        System.out.println("Please select a number for the menu option");
    }
}

