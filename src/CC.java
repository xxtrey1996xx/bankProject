import java.util.ArrayList;

public class CC extends Credit {
    String cardNumber, balance, interest, date, lastPaymentDate, monthlyPayment, openDate, missedPayment;


    ArrayList<Transaction> transactionsArrayList = new ArrayList<>();


    //Constructor for CC
    public CC(String acctNum, String balance, String interest, String date, String lastPaymentDate, String monthlyPayment, String openDate, String missedPayment) {
    this.cardNumber = acctNum;
        this.setBalance(balance);
        this.setInterestRate(interest);
        this.setType("CC");
    this.date = date;
    this.lastPaymentDate = lastPaymentDate;
    this.monthlyPayment = monthlyPayment;
    this.openDate = openDate;
        this.missedPayment = missedPayment;
    }

    public CC(String text, String text1, String text2, String text3) {
        super();
    }


    @Override
    public String getBalance() {
        return balance;
    }

    @Override
    public String getInterestRate() {
        return interest;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void credit() {

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getInterest() {
        return interest;
    }

    @Override
    public String getDate() {
        return date;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public String getOpenDate() {
        return openDate;
    }

    public String getMissedPayment() {
        return missedPayment;
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

    //Method to charge credit card
    public void creditCharge(double amount, String location, String date){
        //Create Transaction Object
        Transaction transaction = new Transaction(location, date, amount);
        //Add Transaction Object to Transaction ArrayList for Account
        this.transactionsArrayList.add(transaction);

    }

    private class Transaction {
        //Subclass of Credit Card used to log each transaction;
        String location;
        String date;
        double amount;
        private Transaction(String location, String date, double amount){
            this.location = location;
            this.date = date;
            this.amount = amount;
        }
    }
}
