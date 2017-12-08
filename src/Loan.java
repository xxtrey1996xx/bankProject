public class Loan extends Credit {
    String ssn, length, type, balance, interestRate, dueDate, lastPayment, minimumPayment, openDate, missedPayment;

    //Constructor for Loan
    public Loan(String ssn, String length, String type, String balance, String interest, String date, String lastPaymentDate, String monthlyPayment, String openDate, String missedPayment) {
        this.setOwnerID(ssn);
        this.length = length;
        this.setType(type);
        this.setBalance(balance);
        this.setInterestRate(interest);
        this.dueDate = date;
        this.lastPayment = lastPaymentDate;
        this.minimumPayment = monthlyPayment;
        this.openDate = openDate;
        this.missedPayment = missedPayment;
    }



    @Override
    public String getBalance() {
        return null;
    }

    @Override
    public String getInterestRate() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void credit() {

    }

    @Override
    public void credit(double amount) {

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

    @Override
    public boolean getOverdraftAccount() {
        return false;
    }

    @Override
    public String getBackupAccountNumber() {
        return null;
    }
    //TODO setup loan class

}
