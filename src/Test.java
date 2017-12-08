import javax.swing.*;
import java.awt.*;

public class Test {
    private JPanel panel1;
    private JTable table;
    protected Customer customer;

    public Test(Customer customer){
        this.customer = customer;
    }

    private void createUIComponents() {
        int numRows = customer.accounts.size();
        int numCols = 4;

        //Create Column Headings for Table
        String [] colNames = {"Account Number", "Account Type", "Interest Rate", "Balance"};

        //Create Table Object
        Object [][] data = new Object[numRows][numCols-1];
            //Populate table
                for(int i=0; i<=numRows; i++){
                    data[i][0] = customer.accounts.get(i).accountNumber;
                    data[i][1] = customer.accounts.get(i).type;
                    data[i][2] = customer.accounts.get(i).interestRate;
                    data[i][3] = customer.accounts.get(i).balance;
        }


       table = new JTable(data, colNames);





        // TODO: place custom component creation code here
    }
}
