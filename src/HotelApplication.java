import api.AdminResource;
import api.HotelResource;
import model.*;
import service.ReservationService;
import util.StringToDate;
import util.Validator;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class HotelApplication {
    public static Scanner scanner = new Scanner(System.in);

    private static String getEmail() {
        String email = "";
        System.out.println("Enter your email (name@domain.com)");
        email = scanner.next();
        while (!Validator.validateEmail(email)) {
            System.out.println("Please enter valid email with format name@domain.com");
            email = scanner.next();
        }
        return email;
    }

    private static String getUserConfirmation(String message) {
        System.out.println(message);
        String yesOrNo = scanner.next().toLowerCase();
        while (!(yesOrNo.equals("y") || yesOrNo.equals("n"))) {
            System.out.println("Please enter Y(Yes) or N(No)");
            yesOrNo = scanner.next().toLowerCase();
        }
        return yesOrNo;
    }

    private static String getUserInput(String message) {
        System.out.println(message);
        return scanner.next();
    }

    private static String createUserAccount() {
        String email = getEmail();
        String firstName = getUserInput("Please enter first name");
        String lastName = getUserInput("Please enter last name");
        HotelResource.getInstance().createACustomer(email, firstName, lastName);
        return email;
    }

    private static void printRooms (Collection<IRoom> rooms) {
        for (IRoom room: rooms) {
            System.out.println(room);
        }
    }

    public static void main(String[] args) {
        HotelResource hotelResource = HotelResource.getInstance();
        AdminResource adminResource = AdminResource.getInstance();
        String prettier = "-----------------------------------------------------------";
        String email = "";
        String yesOrNo = "";
        String roomNumber = "";
        System.out.println("Welcome to the Hotel Reservation Application\n");
        while (true) {
            MainMenu.show(prettier);
            try {
                int choice = Integer.valueOf(scanner.next());
                if (choice == 5) {
                    break;
                }
                switch (choice) {
                    case 1:
                        Date checkInDate;
                        Date checkOutDate;
                        while (true) {
                            try {
                                checkInDate = StringToDate.convert(getUserInput("Enter CheckIn Date mm/dd/yyyy example 02/01/2020"));
                                break;
                            } catch (ParseException exception) {}
                        }
                        while (true) {
                            try {
                                checkOutDate = StringToDate.convert(getUserInput("Enter CheckOut Date mm/dd/yyyy example 02/21/2020"));
                                break;
                            } catch (ParseException exception) {}
                        }
                        Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);
                        if (rooms.size() == 0) {
                                System.out.println("No rooms found in that date range!");
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(checkInDate);
                                calendar.add(Calendar.DAY_OF_MONTH, 7);
                                checkInDate = calendar.getTime();
                                calendar.setTime(checkOutDate);
                                calendar.add(Calendar.DAY_OF_MONTH, 7);
                                checkOutDate = calendar.getTime();
                                rooms = hotelResource.findARoom(checkInDate, checkOutDate);
                                if (rooms.size() > 0) {
                                    System.out.println("Showing recommended rooms available between " + checkInDate.toString() + " & " + checkOutDate.toString());
                                }
                        }
                        printRooms(rooms);
                        if (rooms.size() != 0) {
                            yesOrNo = getUserConfirmation("Would you like to book a room y/n");
                            if (yesOrNo.equals("y")) {
                                yesOrNo = getUserConfirmation("Do you have account with us? y/n");
                                if (yesOrNo.equals("y")) {
                                    email = getEmail();
                                } else {
                                    email = createUserAccount();
                                }
                                roomNumber = getUserInput("What room number you would like to reserve?");
                                System.out.println(hotelResource.bookARoom(email, hotelResource.getRoom(roomNumber), checkInDate, checkOutDate));
                            }
                        } else {
                            System.out.println("We are really sorry! No room are available");
                        }
                        break;
                    case 2:
                        email = getEmail();
                        for (Reservation reservation: hotelResource.getCustomersReservations(email)) {
                            System.out.println(reservation);
                        }
                        break;
                    case 3:
                        createUserAccount();
                        break;
                    case 4: while (true) {
                        AdminMenu.show(prettier);
                        try {
                            int adminChoice = scanner.nextInt();
                            if (adminChoice == 5) break;
                            switch (adminChoice) {
                                case 1:
                                    for (Customer customer: adminResource.getAllCustomers()) {
                                        System.out.println(customer);
                                    }
                                    break;
                                case 2:
                                    for (IRoom room: adminResource.getAllRooms()) {
                                        System.out.println(room);
                                    }
                                    break;
                                case 3:
                                    adminResource.displayAllReservations();
                                    break;
                                case 4:
                                    while(true) {
                                        roomNumber = getUserInput("Enter the room number");
                                        double roomPrice = Double.valueOf(getUserInput("Enter the room price"));
                                        String roomType = "";
                                        while (true) {
                                            roomType = getUserInput("Enter the room type single or double [S]/[D]").toLowerCase();
                                            if (roomType.equals("s") || roomType.equals("d")) {
                                                break;
                                            }
                                        }
                                        adminResource.addRoom(roomNumber, roomPrice, roomType.equals("s") ? RoomType.SINGLE : RoomType.DOUBLE);
                                        yesOrNo = getUserConfirmation("Would you like to add another room y/n");
                                        if (yesOrNo.equals("n")) break;
                                    }
                                    break;
                                default: throw new Exception();
                            }
                        } catch (Exception error) {
                            System.out.println("Please enter valid entry");
                        }
                    }
                    break;
                    default: throw new Exception();
                }
            } catch (Exception ex) {
                System.out.println("Please enter valid entry");
            }
        }
    }

}
