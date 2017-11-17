import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ArrayList<Checking> checkings = new ArrayList<Checking>();
        readDB();
        firstMainMenu mainMenu = new firstMainMenu();

        mainMenu.pack();
        mainMenu.setVisible(true);
    }

    public static void readDB() {
        File db = new File("bankdatabase.txt");
        try {
            Scanner input = new Scanner(db);//Open Scanner
            input.useDelimiter("\r");//Setting Delimeter to Tab
            while (input.hasNext()) {
                System.out.println(input.next());//TODO This is where we would seperate the account types into the different arrays.
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
