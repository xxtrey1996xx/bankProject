public class Checking extends Account {
    public boolean hasOverdraftAccount;
    private String overdraftAccount;

    public Checking(String ownerID, String balance, String interestRate, String accountNumber, String type, String date, boolean hasOverdraftAccount){
        this.ownerID = ownerID;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = accountNumber;
        this.type = type;
        this.date = date;
        this.hasOverdraftAccount = hasOverdraftAccount;
    }
    public Checking(String ownerID, String balance, String interestRate, String accountNumber, String type, String date, boolean hasOverdraftAccount, String overdraftAccount){
        this.ownerID = ownerID;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = accountNumber;
        this.type = type;
        this.date = date;
        this.hasOverdraftAccount = hasOverdraftAccount;
        this.overdraftAccount = overdraftAccount;
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
        return 0;
    }
}
