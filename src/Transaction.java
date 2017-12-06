public class Transaction {
    //Subclass of Credit Card used to log each transaction;
    String location;
    String date;
    double amount;
    String checkNumber;
    String type;

    //Constructor
    public Transaction(String location, String date, double amount) {
        this.location = location;
        this.date = date;
        this.amount = amount;
    }
}