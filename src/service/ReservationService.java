package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private List<Reservation> reservations = new ArrayList<>();
    private Map<String, IRoom> rooms = new HashMap<>();

    private static ReservationService reservationService = null;

    private ReservationService() {

    }

    public static ReservationService getInstance()
    {
        if (reservationService == null)
            reservationService = new ReservationService();

        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkedInDate, Date checkedOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkedInDate, checkedOutDate);
        reservations.add(newReservation);
        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkedInDate, Date checkedOutDate) {
        Map<String, IRoom> availableRooms = new HashMap<>(rooms);
        for (Reservation reservation: reservations) {
            boolean isRoomAvailable =
                    (checkedInDate.after(reservation.getCheckedInDate())
                            && checkedOutDate.after(reservation.getCheckedOutDate())
                            && !checkedInDate.before(reservation.getCheckedOutDate())
                    ) ||
                    (checkedInDate.before(reservation.getCheckedInDate())
                            && checkedOutDate.before(reservation.getCheckedOutDate())
                            && !checkedOutDate.after(reservation.getCheckedInDate())
                    );
            if (!isRoomAvailable) availableRooms.remove(reservation.getRoom().getRoomNumber());
        }
        return availableRooms.values();
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        ArrayList<Reservation> customersReservations = new ArrayList<>();
        for (Reservation reservation: reservations) {
            if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                customersReservations.add(reservation);
            }
        }
        return customersReservations;
    }

    public void printAllReservations() {
        for(Reservation reservation: reservations) {
            System.out.println(reservation);
        }
    }
}

