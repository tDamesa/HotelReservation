package service;

import model.customer.Customer;
import repository.Repository;

import java.util.Collection;
import java.util.List;

/**
 *
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
        if (!doesEmailExist(Repository.customers, email)) {
            Repository.customers.add(customer);
        } else {
            System.out.println("Email already exist.");
        }
        System.out.println(customer);
        return customer;
    }

    public Customer getCustomer(String customerEmail) {
        for (Customer obj : Repository.customers) {
            if (obj.getEmail().equals(customerEmail)) return obj;
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return Repository.customers;
    }

    private boolean doesEmailExist(List<Customer> list, String email) {
        return list.stream().anyMatch(obj -> obj.getEmail().equals(email));
    }
}