import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readDB();
        //test save
        saveDB();
        LogInScreen lis = new LogInScreen();
        lis.pack();
        lis.setVisible(true);
    }

    public static void readDB() throws Exception {
        File db = new File("bankdatabasePIPE.txt");
        Scanner input = new Scanner(db);
        input.nextLine(); //Skip title line.

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
        Customer obj;
        obj = new Customer(record[0], record[5], record[6], record[1], record[2], record[3], record[4]);
        customers.add(obj);
        String accountType = null;
        Double balance = 0.0;
        /*This upcoming code seperates the lack of consistancy in the code for the diffent account types.*/
        if (record[7].compareToIgnoreCase("TMB") == 0) {
            accountType = record[7];
            balance = Double.valueOf(Double.parseDouble(record[8]));
        } else if (record[8].compareToIgnoreCase("savings") == 0) {
            accountType = record[8];
            balance = Double.valueOf(Double.parseDouble(record[7]));
        } else {
            accountType = record[8];
            balance = Double.valueOf(Double.parseDouble(record[9]));
        }
        //String accountType = record[8];
        System.out.println("AccountType should be: " + accountType);
        //Double balance = Double.valueOf(Double.parseDouble(record[9]));
        System.out.println("Balance should be: " + balance);
        //Need to create account here.
        //Account account = new Account();


        //Validate that user has this account already.


    }

    public static void saveDB() throws Exception{
        PrintWriter printWriter = new PrintWriter(new File("CurrentDB.txt"));
        for (int i = 0; i <= customers.size() - 1; i++) {
            String address = customers.get(i).streetAddress;
            String city = customers.get(i).city;
            String state = customers.get(i).state;
            String zip = customers.get(i).zip;
            String fName = customers.get(i).firstName;
            String lName = customers.get(i).lastName;
            for (int x = 0; x <= customers.get(i).accounts.size() - 1; x++) {
                String balance = customers.get(i).accounts.get(x).balance;
                String interestRate = customers.get(i).accounts.get(x).interestRate;
                String type = customers.get(i).accounts.get(x).type;
                String acctNum = customers.get(i).accounts.get(x).accountNumber;
                String date = customers.get(i).accounts.get(x).date;
                String ssn = customers.get(i).accounts.get(x).ownerID;
                //Format and Print Records
                printWriter.println(
                        ssn + "\\|" +
                                address + "\\|" +
                                city + "\\|" +
                                state + "\\|" +
                                zip + "\\|" +
                                fName + "\\|" +
                                lName + "\\|" +
                                acctNum + "\\|" +
                                type + "\\|" +
                                balance + "\\|" +
                                interestRate + "\\|" +
                                date);
            }//end nested loop
        }//end For
    }//end SaveDb

}
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