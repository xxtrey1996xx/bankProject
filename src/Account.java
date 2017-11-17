public abstract class Account {
    double balance;
    double interestRate; 
    String accountNumber;
    String type;
    String ownerId;



    public abstract double getBalance();
    public abstract double getInterestRate();
    public abstract String getAccountNumber();
    public abstract String getType();
    public abstract String setAccountNumber();
    public abstract String setAccountType();
    

    public Account(double balance, String accountNumber, String type, String ownerId, double interestRate ){
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.type = type;
        this.ownerId = ownerId;
        this.interestRate = interestRate;
    }


    public class SavingsAccount extends Account{

        //actnum, actype, balance, interest rate,  date
        public SavingsAccount( double interestRate, double balance, String accountNumber, String type, String ownerId, String date) {
            super(balance, accountNumber, type, ownerId,interestRate);
            
            this.accountNumber = getAccountNumber();
            this.balance = getBalance();
            this.interestRate = getInterestRate(); 
            
        }

        
        @Override
        public double getBalance() {
            return balance;
        }

        @Override
        public double getInterestRate() {
            return interestRate;
        }

        @Override
        public String getAccountNumber() {
            return accountNumber;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String setAccountNumber() {
            return null;
        }

        @Override
        public String setAccountType() {
            return null;
        }
    }

}
