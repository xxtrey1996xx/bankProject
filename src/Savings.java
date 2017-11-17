public class Savings extends Account {

    public Savings(String ownerID, String balance, String interestRate, String accountNumber, String type, String date) {
        this.ownerID = ownerID;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = accountNumber;
        this.type = type;
        this.date = date;
    }

    @Override
    public String getBalance() {
        return balance;
    }

    @Override
    public String getInterestRate() {
        return interestRate;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void credit() {

    }

    @Override
    public void debit() {

    }

    @Override
    public double calcInterest() {
        return 0;//TODO get this from Pickett
    }
}
