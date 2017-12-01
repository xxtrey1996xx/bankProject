import java.util.ArrayList;

public abstract class Account {
    //Abstract Variables
    public String balance;
    public String interestRate;
    public String accountNumber;
    public String type;
    public String ownerID;
    public String date;

    public ArrayList<Transaction> transactionArrayList;

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //Abstract Functions
    public abstract String getBalance();//Returns balance

    public abstract String getInterestRate();//Returns Interest Rate
    public abstract String getType();//Returns account type
    public abstract void credit();//credits the account
    public abstract void debit();//debits the account
    public void updateTransactionList(Transaction transaction){
        transactionArrayList.add(transaction);
    }
    public abstract void debit(double amount);

    public abstract double calcInterest();


    public abstract Object getOverdraftAccount();
}