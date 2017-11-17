import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> users = new ArrayList<>();//The Array for users

    public static void main(String[] args) {

        readDB();
        firstMainMenu mainMenu = new firstMainMenu();

        mainMenu.pack();
        mainMenu.setVisible(true);
    }

    public static void readDB() {
        File db = new File("src/bankdatabase.txt");//connecting to the database
        try {
            Scanner input = new Scanner(db);//Open Scanner
            input.useDelimiter("\r");//Setting Delimeter to Tab
            while (input.hasNext()) {//Checking if their is another record in the file
                System.out.println(input.next());//TODO This is where we would seperate the account types into the different arrays.
                String record = input.next();
                String[] splitRecord = record.split("\t");//splitting the line by tabs
                if (!users.contains(splitRecord[0])) {//Checking to see if the users group has that SSN in it already
                    users.add(splitRecord[0]);//If not, then add it
                    System.out.println("Found New User");
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
