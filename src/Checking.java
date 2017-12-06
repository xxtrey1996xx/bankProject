public class Checking extends Account {
    public boolean hasOverdraftAccount;
    public String overdraftAccount;

    String ownerID, balance, interestRate, accountNumber, type, date;
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
    public void credit(double amount) {
        //Adding money to account
        Double newBalance;
        newBalance = Double.valueOf(balance) + amount;
        balance = newBalance.toString();
        System.out.println(accountNumber + " should be " + balance);

    }

    public boolean getOverdraftAccount() {
        return hasOverdraftAccount;
    }

    @Override
    public void debit() {
        //This isnt used
    }

    @Override
    public void debit(double amount) {
        //Taking money from account
        if ((Double.valueOf(balance) - amount) >= 0) {
            Double newBalance = Double.valueOf(balance) - amount;
            balance = newBalance.toString();
            System.out.println(accountNumber + " balance should be " + balance);
            updateTransactionList(new Transaction("Debit", Main.myDate.toString(), amount));
        } else if ((Double.valueOf(balance) - amount) < 0) {
            //This is where overdrafts are handled
        }

    }

    public void stopPay(String checkNumber) {

    }

    @Override
    public double calcInterest() {
        return 0;
    }
}
