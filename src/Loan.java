public class Loan extends Credit {
    String ssn,acctNum, type, balance, interestRate, dueDate, lastPayment, minimumPayment, openDate, WTFvariable;

    //Constructor for Loan
    public Loan(String ssn, String acctNum, String type, String balance, String interest, String date, String lastPaymentDate, String monthlyPayment, String openDate, String WTFvariable){
        this.ssn = ssn;
        this.acctNum = acctNum;
        this.type = type;
        this.balance = balance;
        this.interestRate = interest;
        this.dueDate = date;
        this.lastPayment = lastPaymentDate;
        this.minimumPayment = monthlyPayment;
        this.openDate = openDate;
        this.WTFvariable = WTFvariable;
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
    //TODO setup loan class

}
