import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class CustomerAccountsMenu2 extends JDialog {
    int index;

    JTable jt;

    public CustomerAccountsMenu2() {
        Customer customer = LookupCustomer.getCustomer(index);
        // Columns for table
        String[] columns = {"Account Number", "Account Type", "Interest Rate", "Balance",};

        // 2D array is used for data in table
        int i = 0;
        String[][] data = {
                {
                        customer.accounts.get(i).getAccountNumber(), customer.accounts.get(i).type,
                        customer.accounts.get(i).interestRate, customer.accounts.get(i).balance
                }};//end data
        // Creates Table
        jt = new JTable(data, columns) {
            // Determines if data can be entered by users
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            //  Creates cells for the table
            public Component prepareRenderer(
                    TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);

                // Every even numbers
                if (data % 2 == 0)
                    c.setBackground(Color.WHITE);

                else
                    c.setBackground(Color.LIGHT_GRAY);

                return c;
            }
        };

        // Set size of table
        jt.setPreferredScrollableViewportSize(new Dimension(450, 63));

        // This will resize the height of the table automatically
        // to all data without scrolling.
        jt.setFillsViewportHeight(true);

        JScrollPane jps = new JScrollPane(jt);
        add(jps);
    }

    // Creates Window
    public static void main(String[] args) {
        CustomerAccountsMenu2 dialog = new CustomerAccountsMenu2();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
