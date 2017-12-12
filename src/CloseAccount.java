import javax.swing.*;
import java.awt.event.*;

public class CloseAccount extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField customersSSNTextField;
    private JTextField accountNumberTextField;
    private Customer customer;

    public CloseAccount() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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
        CloseAccount dialog = new CloseAccount();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() throws Exception {
        dispose();
        int index = LookupCustomer.lookupUserIndex(customersSSNTextField.getText(), false);
        if (index != -99) {
            Customer customer = Main.customers.get(index);
            Boolean wasAccountFound = false;

            for (int i = 0; i < customer.accounts.size(); i++) {
                //Going through only the accounts registered to the ssn
                if (customer.accounts.get(i).accountNumber.equalsIgnoreCase(accountNumberTextField.getText())) {
                    //checking to see if the acct num exsists for the customer
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this account?\n"
                                    + "Social Security Num: " + customer.ssn + "\n" +
                                    " Account Number: " + customer.accounts.get(i).accountNumber, "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.NO_OPTION) {
                        onCancel();
                    } else if (response == JOptionPane.YES_OPTION) {
                        customer.accounts.remove(i);
                        System.out.println("Account Closed");
                        Main.saveDB();
                    } else if (response == JOptionPane.CLOSED_OPTION) {
                        onCancel();
                    }
                    wasAccountFound = true;
                }
                if (!wasAccountFound) {
                    JOptionPane.showMessageDialog(null, "That Account was not found", "Account Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, customersSSNTextField.getText() + " was not found", "Customer Not Found", JOptionPane.ERROR_MESSAGE);
        }

        FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
        fmm.setSystemDateTime();
        fmm.pack();
        fmm.setVisible(true);
    }

    private void onCancel() {
        dispose();
        FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
        fmm.setSystemDateTime();
        fmm.pack();
        fmm.setVisible(true);
    }

}
