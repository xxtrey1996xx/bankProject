public class Transaction {
    //Subclass of Credit Card used to log each transaction;
    String location;
    String date;
    String amount;
    String checkNumber;
    String type;

    //Constructor
    public Transaction(String location, String date, String amount) {
        this.location = location;
        this.date = date;
        this.amount = amount;
    }

    //Overload Constructor
    public Transaction(String date, String amount){
        this.date = date;
        this.amount = amount;
    }

}