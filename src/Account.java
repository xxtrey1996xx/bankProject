public abstract class Account {
    //Abstract Variables
    public String balance;
    public String interestRate;
    public String accountNumber;
    public String type;
    public String ownerID;
    public String date;

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

    public abstract void debit(double amount);

    public abstract double calcInterest();

//    double balance;
//    double interestRate;
//    String accountNumber;
//    String type;
//    String ownerId;
//
//
//
//    public abstract double getBalance();
//    public abstract double getInterestRate();
//    public abstract String getAccountNumber();
//    public abstract String getType();
//    public abstract String setAccountNumber();
//    public abstract String setAccountType();
//
//
//    public Account(double balance, String accountNumber, String type, String ownerId, double interestRate ){
//        this.balance = balance;
//        this.accountNumber = accountNumber;
//        this.type = type;
//        this.ownerId = ownerId;
//        this.interestRate = interestRate;
//    }
//
//
//    public class SavingsAccount extends Account{
//
//        //actnum, actype, balance, interest rate,  date
//        public SavingsAccount( double interestRate, double balance, String accountNumber, String type, String ownerId, String date) {
//            super(balance, accountNumber, type, ownerId,interestRate);
//
//            this.accountNumber = getAccountNumber();
//            this.balance = getBalance();
//            this.interestRate = getInterestRate();
//
//        }
//
//
//        @Override
//        public double getBalance() {
//            return balance;
//        }
//
//        @Override
//        public double getInterestRate() {
//            return interestRate;
//        }
//
//        @Override
//        public String getAccountNumber() {
//            return accountNumber;
//        }
//
//        @Override
//        public String getType() {
//            return type;
//        }
//
//        @Override
//        public String setAccountNumber() {
//            return null;
//        }
//
//        @Override
//        public String setAccountType() {
//            return null;
//        }
//    }

}