package model;

import util.Validator;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (!Validator.validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        Customer customer = (Customer) obj;
        return (customer.getEmail().equals(email) && customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
