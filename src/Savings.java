public class Savings extends Account {

  String ownerID, balance, interestRate,accountNumber, type,date;

    public Savings(String ownerID, String balance, String interestRate, String accountNumber, String type, String date) {
        this.setOwnerID(ownerID);
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = accountNumber;
        this.setType(type);
        this.date = date;
    }

    @Override
    public String getBalance() {
        //return this.getBalance();
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
        //convert into double format
        newBalance = Double.valueOf(balance) + amount;
        //saving new balance
        balance = newBalance.toString();
        System.out.println(accountNumber + " should be " + balance);
    }

    @Override
    public void debit() {

    }

    @Override
    public void debit(double amount) {
        //Initialization
        double currentTotal;
        double newTotal;
        String oldTotal = this.getBalance();

        //Convert Balance from String to double
        currentTotal = Double.valueOf(oldTotal);
        //Subtract Debit Amount from Balance
        newTotal = (currentTotal - amount);
        //Check if this will send account negative
        if (newTotal < 0.00) {
            setBalance(Double.toString(newTotal));
        }
    }

    @Override
    public double calcInterest() {
        return 0;//TODO get this from Pickett
    }

    @Override
    public boolean getOverdraftAccount() {
        return false;
    }

    @Override
    public String getBackupAccountNumber() {
        return null;
    }

    @Override
    public String getLastPaymentDate() {
        return null;
    }
}
