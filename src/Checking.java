public class Checking extends Account {
    public boolean hasOverdraftAccount;
    public String overdraftAccount;

    String ownerID, balance, interestRate,accountNumber,type,date;
    boolean HasOverdraftAccount;

    public Checking(String ownerID, String balance, String interestRate, String accountNumber, String type, String date, boolean hasOverdraftAccount) {
        this.setOwnerID(ownerID);
        this.setBalance(balance);
        this.setInterestRate(interestRate);
        this.setAccountNumber(accountNumber);
        this.setType(type);
        this.setDate(date);
        this.hasOverdraftAccount = hasOverdraftAccount;
    }

    public Checking(String ownerID, String balance, String interestRate, String accountNumber, String type, String date, boolean hasOverdraftAccount, String overdraftAccount) {
        this.setOwnerID(ownerID);
        this.setBalance(balance);
        this.setInterestRate(interestRate);
        this.setAccountNumber(accountNumber);
        this.setType(type);
        this.setDate(date);
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
    public void debit(double amount) {

    }

    @Override
    public double calcInterest() {
        return 0;
    }
}
