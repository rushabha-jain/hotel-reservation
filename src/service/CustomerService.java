package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private Map<String, Customer> customers = new HashMap<>();

    private static CustomerService customerService = null;

    private CustomerService() {

    }

    public static CustomerService getInstance()
    {
        if (customerService == null)
            customerService = new CustomerService();

        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
