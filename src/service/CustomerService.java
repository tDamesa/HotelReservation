package service;

import model.customer.Customer;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Tigist
 */
public class CustomerService {
    private CustomerService() {
    }

    private static class SingletonHelper {
        private static final CustomerService INSTANCE = new CustomerService();
    }

    public static CustomerService getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public Customer addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        if (!Repository.customers.containsKey(email)) {
            Repository.customers.put(email, customer);
        } else {
            System.out.println("Email already exist.");
        }
        System.out.println(customer);
        return customer;
    }

    public Customer getCustomer(String customerEmail) {
        return Repository.customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return new ArrayList<Customer>(Repository.customers.values());
    }
}