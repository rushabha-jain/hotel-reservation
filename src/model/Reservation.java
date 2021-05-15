package model;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkedInDate;
    private final Date checkedOutDate;

    public Reservation(Customer customer, IRoom room, Date checkedInDate, Date checkedOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkedInDate = checkedInDate;
        this.checkedOutDate = checkedOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckedInDate() {
        return checkedInDate;
    }

    public Date getCheckedOutDate() {
        return checkedOutDate;
    }

    @Override
    public String toString() {
        return "Reservation --" +
                "\n" + customer +
                "\n" + room +
                "\nCheckIn Date: " + checkedInDate +
                "\nCheckOut Date: " + checkedOutDate;
    }
}
