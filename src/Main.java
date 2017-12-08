import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static ArrayList<Customer> customers = new ArrayList<>();//Main Data structure for program
    public static String activeUser = null;//Stores user type that logged in
    public static Date myDate = new Date();//Stores Date for the system


    public static void main(String[] args) throws Exception {
        readDB();
        Thread.sleep(25);
        saveDB();
        LogInScreen lis = new LogInScreen();
        lis.pack();
        lis.setVisible(true);
        Thread.sleep(25);
        saveDB();
    }//End main



    //May need to make this a Runnable as well to avoid race condition with save
    public static void readDB() throws Exception {
        File db = new File("bankdatabasePIPE.txt");
        Scanner input = new Scanner(db);
        //Skip title line.
        input.nextLine();
        input.useDelimiter("\n");//Set Delimiter to returns to get a record from the Database
        while (input.hasNext()) {
            String record = input.next();
            System.out.println("This is the original DB Record: \n" + record);
            String[] splitRecord = record.split("\\|");
            updateCustomerArray(splitRecord);
        }
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
        String lastPaymentDate, monthlyPayment, openDate, WTFvariable, length;

        //Create customer object from parsed values
        Customer newCustomer;
        newCustomer = new Customer(ssn, fName, lName, address, city, state, zip);
        customers.add(newCustomer);
        //Instanciating account types
        Savings newSavings;
        Checking newChecking;
        CC newCC;
        CD newCD;
        Loan newLoan;

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
                lastPaymentDate = record[12];
                monthlyPayment = record[13];
                openDate = record[14];
                WTFvariable = record[15];
                newCC = new CC(acctNum, balance, interest, date, lastPaymentDate, monthlyPayment, openDate, WTFvariable);
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
        }
    }


    //Checks if Checking account has the flag for overdraft protection
    public static boolean hasOverdraftAccount(String flag){
        if (flag == "1")
            return true;
        else
            return false;
    }

    //May need to make this a Runnable to avoid Race condition with reading the database
    public static void saveDB() throws Exception {
        String address, city, state, zip, fName, lName, balance, interestRate, type, acctNum, date, ssn;
        PrintWriter printWriter = new PrintWriter(new File("currentDB.txt"));
        String dbRecord;
        //Print Title Line
        printWriter.println("SSN         Address         City        State/Zip   FNam    LName   ACCT#   ACCTTYP Balance Int     OpenDate");
        for (int i = 0; i <= customers.size() - 1; i++) {
            address = customers.get(i).streetAddress;
            city = customers.get(i).city;
            state = customers.get(i).state;
            zip = customers.get(i).zip;
            fName = customers.get(i).firstName;
            lName = customers.get(i).lastName;
            for (int x = 0; x <= customers.get(i).accounts.size() - 1; x++) {
                balance = customers.get(i).accounts.get(x).balance;
                interestRate = customers.get(i).accounts.get(x).interestRate;
                type = customers.get(i).accounts.get(x).type;

                ssn = customers.get(i).accounts.get(x).getOwnerID();
                //TODO Will need a print function written for each type of account since Credit and loans have extra fields.

                //TODO will also need to include the flag for overdraft coverage.


                //dbRecord = checkingDBRecord(printWriter, ssn,address,city,state,zip,fName,lName,acctNum,type,balance,interestRate,date,hasOverdraftAccount("0"));
                //printWriter.println(dbRecord);
                System.out.println(i + " " + x);
                if (type.equalsIgnoreCase("tmb") || type.equalsIgnoreCase("gold") || type.equalsIgnoreCase("diamond") || type.equalsIgnoreCase("savings")) {
                    acctNum = customers.get(i).accounts.get(x).getAccountNumber();
                    date = customers.get(i).accounts.get(x).getDate();
                    checkingDBRecord(printWriter, ssn, address, city, state, zip, fName, lName, acctNum, type, balance, interestRate, date, hasOverdraftAccount("0"));
                } else if (type.equalsIgnoreCase("long") || type.equalsIgnoreCase("short") || type.equalsIgnoreCase("cc")) {
                    String lastPaymentDate, monthlyPayment, openDate, WTFvariable;
                    System.out.println("Loan printed");
                } else if (type.equalsIgnoreCase("cd")) {
                    System.out.println("printed cd record");
                } else {
                    System.out.println("Tried to write invalid record");
                }
                /*switch(type){
                    case "Savings":
                    case "TMB":
                    case "Gold":
                    case "Diamond":
                    case "CD": dbRecord = checkingDBRecord(printWriter,ssn,address,city,state,zip,fName,lName,acctNum,type,
                            balance,interestRate,date, (Boolean) customers.get(i).accounts.get(x).getOverdraftAccount());
                            printWriter.println(dbRecord);
                    break;
                    default : System.out.println("Invalid Type");*/
                }
            }//end nested loop
        printWriter.close();
    }//end For
    //end SaveDb

    public static void checkingDBRecord(PrintWriter printWriter, String ssn, String address, String city, String state, String zip, String fName,
                                          String lName, String acctNum, String type, String balance, String interestRate, String date, boolean overdraft){
        String overdraftFlag = "0";
        if(overdraft = true)
            overdraftFlag = "1";
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
                        date);
        printWriter.println(dbRecord);
        printWriter.flush();
        String record = "test";
    }

    public String creditDBRecord(){
        String record = "test";
        return record;
    }

/*
    Calculate interest(){
        principal = $$.$$;
        rate = yearlyRate % 2.1
        yearly = principal * rate * .01
        

    }
 */

}
