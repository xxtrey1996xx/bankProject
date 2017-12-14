import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static ArrayList<Customer> customers = new ArrayList<>();//Main Data structure for program
    public static String activeUser = null;//Stores user type that logged in
    public static Date myDate = new Date();//Stores Date for the system
    public static String dateString;


    public static void main(String[] args) throws Exception {
        readDB();
        Thread.sleep(100);
        saveDB();
        LogInScreen lis = new LogInScreen();
        lis.pack();
        lis.setVisible(true);
        Thread.sleep(25);
        saveDB();
    }//End main


    //May need to make this a Runnable as well to avoid race condition with save
    public static void readDB() throws Exception {
        File db = new File("bankdatabasePIPE2.txt");
        Scanner input = new Scanner(db);
        //Read in Date

        dateString = input.nextLine();

        input.useDelimiter("\r");//Set Delimiter to returns to get a record from the Database
        System.out.println("\n*************************************************** - Original DB Record - ***************************************************\n");
        while (input.hasNext()) {
            String record = input.next();
            System.out.println(record);
            String[] splitRecord = record.split("\\|");
            updateCustomerArray(splitRecord);
        }
        System.out.println("************************************************* - End Original DB Record - *************************************************\n");

        input.close();
    }

    public static void updateCustomerArray(String[] record) {
        //Checks to see what kind of account we are dealing with
        //Assigns Parsed values to variables
        String ssn = record[0];
        String address = record[1];
        String city = record[2];
        String state = record[3];
        String zip = record[4];
        String fName = record[5];
        String lName = record[6];
        String acctNum = record[7];
        String type = record[8];
        String balance = record[9];
        String interest = record[10];
        String date = record[11];

        //Variables only used in CC, and Loans
        String lastPaymentDate, monthlyPayment, monthlyDueDate, openDate, WTFvariable, length;


        int wasFound = LookupCustomer.lookupUserIndex(ssn, false);

        //Instanciating account types
        Savings newSavings;
        Checking newChecking;
        CC newCC;
        CD newCD;
        Loan newLoan;
        if (wasFound == -99) {
            //Create customer object from parsed values
            Customer newCustomer;
            newCustomer = new Customer(ssn, fName, lName, address, city, state, zip);
            customers.add(newCustomer);

            //Create Account and Add to User Object Based on Account Type in DB Record
            switch (type) {
                case "Savings":
                    newSavings = new Savings(ssn, balance, interest, acctNum, type, date);
                    newCustomer.accounts.add(newSavings);
                    break;

                case "CD":
                    newCD = new CD(ssn, balance, interest, acctNum, date);
                    newCustomer.accounts.add(newCD);
                    break;

                case "TMB":
                case "Gold":
                case "Diamond":
                    newChecking = new Checking(ssn, balance, interest, acctNum, type, date, hasOverdraftAccount(record[12]));
                    newCustomer.accounts.add(newChecking);
                    break;

                case "CC":
                    monthlyDueDate = record[11];
                    lastPaymentDate = record[12];
                    monthlyPayment = record[13];
                    openDate = record[14];
                    WTFvariable = record[15];
                    newCC = new CC(ssn, acctNum, balance, interest, date, lastPaymentDate, monthlyDueDate, monthlyPayment, openDate, WTFvariable);
                    newCustomer.accounts.add(newCC);
                    break;

                case "long":
                case "short":
                    length = record[7];
                    lastPaymentDate = record[12];
                    monthlyPayment = record[13];
                    openDate = record[14];
                    WTFvariable = record[15];
                    newLoan = new Loan(ssn, length, type, balance, interest, date, lastPaymentDate, monthlyPayment, openDate, WTFvariable);
                    newCustomer.accounts.add(newLoan);
                    break;

                default:
                    System.out.println("Invalid Account Type for User: " + newCustomer.firstName + " " + newCustomer.lastName
                            + "\nID: " + newCustomer.ssn);
                    break;
            }//end switch
        } else {
            //Instantiating account types

            //Create Account and Add to User Object Based on Account Type in DB Record
            switch (type) {
                case "Savings":
                    newSavings = new Savings(ssn, balance, interest, acctNum, type, date);
                    //newCustomer.accounts.add(newSavings);
                    customers.get(wasFound).accounts.add(newSavings);
                    break;

                case "CD":
                    newCD = new CD(ssn, balance, interest, acctNum, date);
                    //newCustomer.accounts.add(newCD);
                    customers.get(wasFound).accounts.add(newCD);
                    break;

                case "TMB":
                case "Gold":
                case "Diamond":
                    newChecking = new Checking(ssn, balance, interest, acctNum, type, date, hasOverdraftAccount(record[12]));
                    //newCustomer.accounts.add(newChecking);
                    customers.get(wasFound).accounts.add(newChecking);
                    break;

                case "CC":
                    lastPaymentDate = record[12];
                    monthlyPayment = record[13];
                    monthlyDueDate = record[11];
                    openDate = record[14];
                    WTFvariable = record[15];
                    newCC = new CC(ssn, acctNum, balance, interest, date, lastPaymentDate, monthlyDueDate, monthlyPayment, openDate, WTFvariable);
                    //newCustomer.accounts.add(newCC);
                    customers.get(wasFound).accounts.add(newCC);
                    break;

                case "long":
                case "short":
                    length = record[7];
                    lastPaymentDate = record[12];
                    monthlyPayment = record[13];
                    openDate = record[14];
                    WTFvariable = record[15];
                    newLoan = new Loan(ssn, length, type, balance, interest, date, lastPaymentDate, monthlyPayment, openDate, WTFvariable);
                    //newCustomer.accounts.add(newLoan);
                    customers.get(wasFound).accounts.add(newLoan);
                    break;

                default:
                    System.out.println("Invalid Account Type for User: " + record[5] + " " + record[6]
                            + "\nID: " + record[0]);
                    break;
            }//end switch
        }//end if
    }


    //Checks if Checking account has the flag for overdraft protection
    public static boolean hasOverdraftAccount(String flag) {
        if (flag == "1")
            return true;
        else
            return false;
    }

    //May need to make this a Runnable to avoid Race condition with reading the database
    public static void saveDB() throws Exception {
        String address, city, state, zip, fName, lName, balance, interestRate, type, acctNum, date, ssn, dateDue, rollover, backupAccountFlag, monthlyDueDate, monthlyOverdraftCount, missedPayments, lastPay, monthlyPay, endDate;
        PrintWriter printWriter = new PrintWriter(new File("bankdatabasePIPE2.txt"));
        String dbRecord;

        //printWriter.println("SSN         Address         City        State/Zip   FNam    LName   ACCT#   ACCTTYP Balance Int     OpenDate");

        //Print Date
        printWriter.print(dateString + "\r");
        //Loop through array of users
        for (int i = 0; i <= customers.size() - 1; i++) {
            address = customers.get(i).streetAddress;
            city = customers.get(i).city;
            state = customers.get(i).state;
            zip = customers.get(i).zip;
            fName = customers.get(i).firstName;
            lName = customers.get(i).lastName;
            //Print record for each account that is linked to each user.
            for (int x = 0; x <= customers.get(i).accounts.size() - 1; x++) {
                Customer cus = customers.get(i);
                Account acct = cus.accounts.get(x);
                balance = acct.balance;
                interestRate = acct.interestRate;
                type = acct.type;
                ssn = acct.ownerID;
                acctNum = acct.accountNumber;
                String newRecord = null;

                switch (type) {
                    case "Savings":
                        Savings savings = (Savings) customers.get(i).accounts.get(x);
                        backupAccountFlag = "1";
                        String backupAccountNumber = savings.getBackupAccountNumber();
                        interestRate = savings.interestRate;
                        acctNum = savings.accountNumber;
                        balance = savings.balance;
                        date = savings.date;
                        newRecord = checkingDBRecord(ssn, address, city, state, zip, fName, lName, acctNum, type, balance, interestRate, date, hasOverdraftAccount("0"));
                        printWriter.print(newRecord);
                        printWriter.flush();
                        break;
                    case "TMB":
                    case "Gold":
                    case "Diamond":
                        Checking checking = (Checking) customers.get(i).accounts.get(x);
                        backupAccountFlag = "1";
                        backupAccountNumber = acct.getBackupAccountNumber();
                        acctNum = checking.accountNumber;
                        interestRate = checking.interestRate;
                        balance = checking.balance;
                        date = checking.date;
                        newRecord = checkingDBRecord(ssn, address, city, state, zip, fName, lName, acctNum, type, balance, interestRate, date, hasOverdraftAccount("0"));
                        printWriter.print(newRecord);
                        printWriter.flush();
                        break;

                    case "CC":
                        CC cc = (CC) customers.get(i).accounts.get(x);
                        dateDue = cc.monthlyDueDate;
                        acctNum = cc.cardNumber;
                        monthlyPay = cc.monthlyPayment;
                        lastPay = cc.lastPaymentDate;
                        endDate = cc.expireDate;
                        missedPayments = cc.missedPayment;
                        newRecord = loanDBRecord(ssn, address, city, state, zip, fName, lName, acctNum, type, balance, interestRate, dateDue, lastPay, monthlyPay, endDate, missedPayments);
                        printWriter.print(newRecord);
                        printWriter.flush();

                        break;
                    case "long":
                    case "short":
                        Loan loan = (Loan) customers.get(i).accounts.get(x);
                        acctNum = loan.length;
                        type = customers.get(i).accounts.get(x).type;
                        balance = customers.get(i).accounts.get(x).balance;
                        interestRate = customers.get(i).accounts.get(x).interestRate;
                        dateDue = loan.dueDate;
                        lastPay = loan.lastPayment;
                        monthlyPay = loan.dueDate;
                        endDate = loan.openDate;
                        missedPayments = loan.missedPayment;
                        newRecord = loanDBRecord(ssn, address, city, state, zip, fName, lName, acctNum, type, balance, interestRate, dateDue, lastPay, monthlyPay, endDate, missedPayments);
                        printWriter.print(newRecord);
                        printWriter.flush();
                        break;
                    case "CD":
                        CD cd = (CD) customers.get(i).accounts.get(x);
                        rollover = cd.date;
                        newRecord = cdDBRecord(ssn, address, city, state, zip, fName, lName, acctNum, type, balance, interestRate, rollover);
                        printWriter.print(newRecord);
                        printWriter.flush();
                        break;
                }


                //TODO will also need to include the flag for overdraft coverage
            }
        }//end nested loop
        printWriter.close();
    }//end For
    //end SaveDb

    public static String checkingDBRecord(String ssn, String address, String city, String state, String zip, String fName,
                                          String lName, String acctNum, String type, String balance, String interestRate, String date, boolean overdraft) {
        String overdraftFlag = "0";
        if (overdraft = true)
            overdraftFlag = "1";
        else
            overdraftFlag = "0";
        String dbRecord = (
                ssn + "|" +
                        address + "|" +
                        city + "|" +
                        state + "|" +
                        zip + "|" +
                        fName + "|" +
                        lName + "|" +
                        acctNum + "|" +
                        type + "|" +
                        balance + "|" +
                        interestRate + "|" +
                        date + "|" +
                        overdraftFlag + "\r"
        );

        return dbRecord;
    }

    public static String cdDBRecord(String ssn, String address, String city, String state, String zip, String fName, String lName, String acctNum, String type, String balance, String interestRate, String rollover) {
        String dbRecord = (
                ssn + "|" +
                        address + "|" +
                        city + "|" +
                        state + "|" +
                        zip + "|" +
                        fName + "|" +
                        lName + "|" +
                        acctNum + "|" +
                        type + "|" +
                        balance + "|" +
                        interestRate + "|" +
                        rollover + "\r"
        );
        return dbRecord;
    }

    public static String loanDBRecord(String ssn, String address, String city, String state, String zip, String fName,
                                      String lName, String term, String type, String balance, String intRate, String dueDate, String lastPay, String monthlyPay, String endDate, String missedPayments) {
        String dbRecord = (
                ssn + "|" +
                        address + "|" +
                        city + "|" +
                        state + "|" +
                        zip + "|" +
                        fName + "|" +
                        lName + "|" +
                        term + "|" +
                        type + "|" +
                        balance + "|" +
                        intRate + "|" +
                        dueDate + "|" +
                        lastPay + "|" +
                        monthlyPay + "|" +
                        endDate + "|" +
                        missedPayments + "\r"
        );
        return dbRecord;
    }
/*
    Calculate interest(){
        principal = $$.$$;
        rate = yearlyRate % 2.1
        yearly = principal * rate * .01
        

    }
 */

}
