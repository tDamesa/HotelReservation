package model.customer;

import java.util.regex.Pattern;

/**
 *
 * @author Tigist
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private final String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);

    public Customer(String firstName, String lastName, String email) {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer: " + "First Name: " + firstName + ", " + "Last Name: " + lastName + ", " + "Email: " + email;
    }

}
