import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.*;
import java.awt.event.*;

public class LookupCustomer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField SSNtextField;

    public LookupCustomer() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main(String[] args) {
        LookupCustomer dialog = new LookupCustomer();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public static int lookupUserIndex(String ssn, boolean alertNeeded) {
        int findIndex = -99;
        double test;
        //System.out.println("Searching for user " + ssn);
        for (int i = 0; i <= Main.customers.size() - 1; i++) {
            test = Main.customers.get(i).getSSN().compareToIgnoreCase(ssn);
            if (Main.customers.get(i).ssn.compareToIgnoreCase(ssn) == 0)
                findIndex = i;
        }
        if (findIndex == -99) {
            if (alertNeeded) {
                System.out.println("User not found SSN: " + ssn);
                handleInvalidUser(ssn);
            }//end alertNeeded if
        } else
        //Shows a popup with a confirmation message
        //todo Error if searched more than one user ID;
        if (alertNeeded) {
            System.out.println("Found User: " + Main.customers.get(findIndex).firstName + " " + Main.customers.get(findIndex).lastName);
            JOptionPane.showMessageDialog(null,
                    ssn + " was found. Belongs to " + Main.customers.get(findIndex).firstName + " " + Main.customers.get(findIndex).lastName,
                    "Found Customer", JOptionPane.INFORMATION_MESSAGE);
        }//end alertNeeded if
        return findIndex;
    }

    public static Customer getCustomer(int index) {
        return Main.customers.get(index);
    }

    private void onCancel() {
        dispose();
        FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
        fmm.setSystemDateTime();
        fmm.pack();
        fmm.setVisible(true);
    }

    private void onOK() {
        //We need to check and see if their SSN is in the system yet.
        int i = lookupUserIndex(SSNtextField.getText(), false);
        if (i != -99) {
            dispose();
            CustomerAccountsMenu2 cam = new CustomerAccountsMenu2();
            cam.setIndex(i);
            cam.pack();
            cam.setVisible(true);

        } else {
            handleInvalidUser(SSNtextField.getText());
        }
    }


    private static void handleInvalidUser(String ssn) {
        JOptionPane.showMessageDialog(null, ssn + " was not found", "Customer Not Found", JOptionPane.ERROR_MESSAGE);
    }
}
