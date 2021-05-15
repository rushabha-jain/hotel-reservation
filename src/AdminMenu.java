public class AdminMenu {
    public static String[] items = {"See all customers", "See all rooms", "See all Reservations", "Add a Room", "Back to Main Menu"};
    public static void show(String prettier) {
        System.out.println(prettier);
        for(int i=0; i<items.length; i++) {
            System.out.println((i+1) + ". " + items[i]);
        }
        System.out.println(prettier);
        System.out.println("Please select a number for the menu option");
    }
}
