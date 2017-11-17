package src;

public abstract class Account {
    String accountNumber;
    String ownerName;
    String ownerId;
    double balance;

    public Account(String accountNumber, String ownerName, String ownerId, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.balance = balance;


    }

    public Account() {

    }

    public void openAccount() {

    }

    public void closeAccount() {

    }

    public void report() {

    }

    public void creditAccount() {

    }

    public void debitAccount() {

    }

    public void showLog() {

    }
}
