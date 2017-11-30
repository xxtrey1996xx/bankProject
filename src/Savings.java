public class Savings extends Account {

  String ownerID, balance, interestRate,accountNumber, type,date;

    public Savings(String ownerID, String balance, String interestRate, String accountNumber, String type, String date) {
        this.setOwnerID(ownerID);
        this.setBalance(balance);
        this.setInterestRate(interestRate);
        this.setAccountNumber(accountNumber);
        this.setType(type);
        this.setDate(date);
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
    public void debit() {

    }

    @Override
    public void debit(double amount) {
        //Initialization
        double currentTotal;
        double newTotal;
        String oldTotal = getBalance();

        //Convert Balance from String to double
        currentTotal = Double.valueOf(oldTotal);
        //Subtract Debit Amount from Balance
        newTotal = currentTotal - amount;
        //Check if this will send account negative
        if (newTotal < 0.00)
            //handleOverdraft();
            setBalance(Double.toString(newTotal));

    }

    @Override
    public double calcInterest() {
        return 0;//TODO get this from Pickett
    }
}
