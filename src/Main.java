import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readDB();
        LogInScreen lis = new LogInScreen();
        lis.pack();
        lis.setVisible(true);
        Thread.sleep(25);
        //Race Condition for Saving Database. Main is not finished reading before saving is implemented.
        saveDB();
    }//End main

    //May need to make this a Runnable as well to avoid race condition with save
    public static void readDB() throws Exception {
        File db = new File("testDB.txt");
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

        //Creates customer object from parsed values
        Customer newCustomer;
        newCustomer = new Customer(ssn, fName, lName, address, city, state, zip);
        customers.add(newCustomer);

        //Creates Account object and assigns to Customer objects Account Array.
        //if(type == "Savings")
        newCustomer.accounts.add(new Savings(ssn, balance, interest, acctNum, type, date));
        System.out.println("Account: " + acctNum + "-" + type + " was added to user " + newCustomer.firstName + " " + newCustomer.lastName);

        //Validate that user has this account already.


    }

    //May need to make this a Runnable to avoid Race condition with reading the database
    public static void saveDB() throws Exception {
        String address, city, state, zip, fName, lName, balance, interestRate, type, acctNum, date, ssn;

        FileWriter fileWriter = new FileWriter("currentDB.txt");

        PrintWriter printWriter = new PrintWriter(new File("CurrentDB.txt"));
        for (int i = 0; i <= customers.size() - 1; i++) {
            address = customers.get(i).streetAddress;
            city = customers.get(i).city;
            state = customers.get(i).state;
            zip = customers.get(i).zip;
            fName = customers.get(i).firstName;
            lName = customers.get(i).lastName;
            for (int x = 0; x <= customers.get(i).accounts.size() - 1; x++) {
                balance = customers.get(i).accounts.get(x).getBalance();
                interestRate = customers.get(i).accounts.get(x).getInterestRate();
                type = customers.get(i).accounts.get(x).getType();
                acctNum = customers.get(i).accounts.get(x).getAccountNumber();
                date = customers.get(i).accounts.get(x).getDate();
                ssn = customers.get(i).accounts.get(x).getOwnerID();

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

                fileWriter.write(dbRecord);
                fileWriter.flush();
            }//end nested loop
        }//end For
        fileWriter.close();
    }//end SaveDb

}
//TODO remove all this once we get done looking at it

     /*   launch(args);
        readDB();




   }


    public static void readDB() throws Exception {
        File db = new File("testDB.txt");
        Scanner input = new Scanner(db);
        input.nextLine(); //Skip title line.

      input.useDelimiter("\n");//Set Delimiter to returns to get a record from the Database
        while(input.hasNext()) {
            String record = input.next();
            System.out.println("This is the original DB Record: \n" + record);
            String[] splitRecord = record.split("\\|");
            updateUserArray(splitRecord);


            //Scanner temp = new Scanner(String.valueOf(input));
            //int count = 0;
            //while (input.hasNext()) {
            //    splitRecord[count] = input.next();
            //    count++;
            }
        input.close();
        }


    public static void updateUserArray(String[] record) {
        Customer obj;
        obj = new Customer(record[0], record[5], record[6], record[1],record[2],record[3],record[4]);
        users.add(obj);
        String accountType = record[8];
        System.out.println("AccountType should be: " + accountType);
        Double balance = Double.valueOf(Integer.parseInt(record[9]));
        System.out.println("Balance should be: " + balance);
        //Need to create account here.
        //Account account = new Account();


        //Validate that user has this account already.


    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Login Screen");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root1));
        stage.show();
    }
}

*/