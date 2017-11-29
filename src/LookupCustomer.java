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

    private void onOK() {
        //We need to check and see if their SSN is in the system yet.
        int i = lookupUser(SSNtextField.getText());
        if (i != -99) {
            //user exists

        }
    }

    private void onCancel() {
        dispose();
        FirstMainMenu fmm = new FirstMainMenu();
        fmm.pack();
        fmm.setVisible(true);
    }

    public static int lookupUser(String ssn) {
        int findIndex = -99;
        System.out.println("Searching for user " + ssn);
        for (int i = 0; i < Main.customers.size() - 1; i++) {
            if (Main.customers.get(i).ssn.equals(ssn))
                findIndex = i;
        }
        if (findIndex == -99) {
            System.out.println("User not found SSN: " + ssn);
            handleInvalidUser(ssn);
        } else
            System.out.println("Found User: " + Main.customers.get(findIndex).firstName + " " + Main.customers.get(findIndex).lastName);
        //Shows a popup with a confirmation message
        JOptionPane.showMessageDialog(null,
                ssn + " was found. Belongs to " + Main.customers.get(findIndex).firstName + " " + Main.customers.get(findIndex).lastName,
                "Found Customer", JOptionPane.INFORMATION_MESSAGE);

        return findIndex;
    }


    private static void handleInvalidUser(String ssn) {
        JOptionPane.showMessageDialog(null, ssn + " was not found", "Customer Not Found", JOptionPane.ERROR_MESSAGE);
    }
}
