import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    String ssn;
    String streetAddress;
    String city;
    String state;
    String zip;
    String firstName;
    String lastName;

    List<Account> accounts = new ArrayList<Account>(Arrays.asList());
    //ArrayList<Account> accounts;


    public Customer(String social, String fName, String lName, String address, String city, String state, String zip) {
        ssn = social;
        streetAddress = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        firstName = fName;
        lastName = lName;

    }

    public String getSSN() {
        return ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}