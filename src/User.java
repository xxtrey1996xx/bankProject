import java.util.ArrayList;

public class User {
    String ssn;
    String streetAddress;
    String city;
    String state;
    String zip;
    String firstName;
    String lastName;

    ArrayList<Account> accounts;


    public User(String social, String fName, String lName, String address, String city, String state, String zip) {
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

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
