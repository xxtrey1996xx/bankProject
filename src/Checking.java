public class Checking extends Account {
    public boolean hasOverdraftAccount;
    public String overdraftAccount;
    public String backupAccountNumber;

    String ownerID, balance, interestRate, accountNumber, type, date, monthlyOverdraftCount;
    boolean HasOverdraftAccount;

    public Checking(String ownerID, String balance, String interestRate, String accountNumber, String type, String date, boolean hasOverdraftAccount) {
        this.setOwnerID(ownerID);
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = accountNumber;
        this.setType(type);
        this.date = date;
        this.hasOverdraftAccount = hasOverdraftAccount;
    }

    public Checking(String ownerID, String balance, String interestRate, String accountNumber, String type, String date, boolean hasOverdraftAccount, String overdraftAccount) {
        this.setOwnerID(ownerID);
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = accountNumber;
        this.setType(type);
        this.setDate(date);
        this.hasOverdraftAccount = hasOverdraftAccount;
        this.overdraftAccount = overdraftAccount;
    }

    public void setBackupAccountNumber(String accountNumber){
        int userIndex = LookupCustomer.lookupUserIndex(ownerID,false);
        Customer thisCustomer = Main.customers.get(userIndex);

        //loop through user accounts to verify Savings account exists matching requested account number.
        for(int i = 0; i<=thisCustomer.accounts.size(); i++){
            if(thisCustomer.accounts.get(i).accountNumber == accountNumber){
                if(thisCustomer.accounts.get(i).type == "Savings"){
                    //This is a valid account. Set pointer to accountNumber
                    backupAccountNumber = accountNumber;
                }
                else//Account exists but is not a savings account
                    {System.out.println("Account Exists but is not a Savings Account");
                }
            }//End Check for account
        }//end For loop

        //TODO Create Alert box for this
        if(backupAccountNumber == null)
            System.out.println("No Account found by account number: " + accountNumber);
    }

    @Override
    public String getBackupAccountNumber(){
        return this.backupAccountNumber;
    }

    @Override
    public String getLastPaymentDate() {
        return null;
    }

    @Override
    public String getBalance() {
        return this.balance;
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
    public void debit(double amount) throws Exception {
        double newBalance = (Double.valueOf(balance) - amount);
        Transaction transaction;

        //Taking money from account

        //Transaction debit = new Transaction("Debit",Main.myDate,balance);

        if (newBalance >= 0) {
            balance = String.valueOf(newBalance);
            System.out.println(Account.accountNumber + " new balance should be " + balance);
            transaction = new Transaction("Debit", Main.myDate.toString(), balance);
            updateTransactionList(transaction);
            try {
                Main.saveDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if ((Double.valueOf(balance) - amount) < 0) {
            //This is where overdrafts are handled
            System.out.println("Overdraft***\t\tAccount#: " + accountNumber);
            balance = String.valueOf(newBalance).format("$999,999,999.99");
        }

    }



    public void stopPay(String checkNumber) {

    }

    @Override
    public double calcInterest() {
        return 0;
    }
}
