import javax.swing.*;/*
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CustomerAccountsMenu2 extends JDialog {
    int index;
    public Customer customer = LookupCustomer.getCustomer(index);

    public CustomerAccountsMenu2() {
        DefaultTableModel model = new DefaultTableModel();
        JTable jt = new JTable(model);
        model.addColumn("Header");
        int numRows = customer.accounts.size();
        int numCols = 4;

        //Create Column Headings for Table
        String [] colNames = {"Account Number", "Account Type", "Interest Rate", "Balance"};

        //Create Table Object
        Object [][] data = new Object[numRows][numCols];
        //Populate table
        for(int i=0; i<=numRows; i++){
            data[i][0] = customer.accounts.get(i).accountNumber;
            data[i][1] = customer.accounts.get(i).type;
            data[i][2] = customer.accounts.get(i).interestRate;
            data[i][3] = customer.accounts.get(i).balance;
        }

/*
        for (int col = 1; col < (data.length + 1); col++) {
            model.addColumn("col" + col);
        }
        for (int h = 0; h < colNames.length; h++) {
            model.addRow(new Object[]{colNames[h]});
        }
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                model.setValueAt(data[r][c], c, r + 1);
            }
        }


        // Creates Table
        jt = new JTable(data, colNames) {
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
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CustomerAccountsMenu2 extends JDialog {
    int index;


    public CustomerAccountsMenu2() {
        Customer customer = LookupCustomer.getCustomer(index);
        DefaultTableModel model = new DefaultTableModel();
        JTable jt = new JTable(model);
        model.addColumn("Header");
        // Columns for table
        String[] columns = {"Account Number", "Account Type", "Interest Rate", "Balance",};
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 2D array is used for data in table
        String[][] data = new String[][]{
                {
                        customer.accounts.get(0).accountNumber,
                        customer.accounts.get(0).type,
                        customer.accounts.get(0).interestRate,
                        customer.accounts.get(0).balance
                },
                {
                        customer.accounts.get(1).accountNumber,
                        customer.accounts.get(1).type,
                        customer.accounts.get(1).interestRate,
                        customer.accounts.get(1).balance
                },
                {
                        customer.accounts.get(2).accountNumber,
                        customer.accounts.get(2).type,
                        customer.accounts.get(2).interestRate,
                        customer.accounts.get(2).balance
                }
        };


        for (int col = 1; col < (data.length + 1); col++) {
            model.addColumn("col" + col);
        }
        for (int h = 0; h < columns.length; h++) {
            model.addRow(new Object[]{columns[h]});
        }
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                model.setValueAt(data[r][c], c, r + 1);
            }
        }


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

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
