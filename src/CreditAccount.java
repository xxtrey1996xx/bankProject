import javax.swing.*;
import java.awt.event.*;

public class CreditAccount extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField SSNTextField;
    private JTextField accountNumberTextField;
    private JTextField amountTextField;

    public CreditAccount() {
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
        CreditAccount dialog = new CreditAccount();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public static boolean isDouble(String text) {
        try {
            new Double(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void onOK() {
        int index = LookupCustomer.lookupUserIndex(SSNTextField.getText(), false);
        boolean wasCredited = false;
        if (index != -99) {
            Customer customer = Main.customers.get(index);
            Boolean wasAccountFound = false;


            for (int i = 0; i < customer.accounts.size(); i++) {
                Account acct = customer.accounts.get(i);
                switch (acct.type) {
                    case "Savings":
                        Savings savings = (Savings) customer.accounts.get(i);
                        //Going through only the accounts registered to the ssn
                        if (savings.accountNumber.equalsIgnoreCase(accountNumberTextField.getText())) {
                            //checking to see if the acct num exsists for the customer
                            int response = JOptionPane.showConfirmDialog(null, "Are these details correct?\n"
                                            + "Social Security Num: " + customer.ssn + "\n" +
                                            " Account Number: " + savings.accountNumber + "\n" +
                                            "Current Balance: " + savings.balance + "\n" +
                                            "Balance to Debit: " + amountTextField.getText() + "\n" +
                                            "Final Balance: " + (Double.valueOf(savings.balance) + Double.valueOf(amountTextField.getText())), "Confirm",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (response == JOptionPane.NO_OPTION) {
                                onCancel();
                            } else if (response == JOptionPane.YES_OPTION) {
                                if (isDouble(amountTextField.getText())) {
                                    //checking to see if double or not
                                    try {
                                        acct.credit(Double.valueOf(amountTextField.getText()));
                                        wasCredited = true;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dispose();

                                } else {
                                    //not a double
                                    JOptionPane.showMessageDialog(null, "Please enter a valid number for debiting", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                }//end double if
                            } else if (response == JOptionPane.CLOSED_OPTION) {
                                onCancel();
                            }
                            wasAccountFound = true;
                        }

                        break;
                    case "Gold":
                    case "TMB":
                    case "Diamond":
                        Checking checking = (Checking) customer.accounts.get(i);
                        //Going through only the accounts registered to the ssn
                        if (checking.accountNumber.equalsIgnoreCase(accountNumberTextField.getText())) {
                            //checking to see if the acct num exsists for the customer
                            int response = JOptionPane.showConfirmDialog(null, "Are these details correct?\n"
                                            + "Social Security Num: " + customer.ssn + "\n" +
                                            " Account Number: " + checking.accountNumber + "\n" +
                                            "Current Balance: " + checking.balance + "\n" +
                                            "Balance to Debit: " + amountTextField.getText() + "\n" +
                                            "Final Balance: " + (Double.valueOf(checking.balance) + Double.valueOf(amountTextField.getText())), "Confirm",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (response == JOptionPane.NO_OPTION) {
                                onCancel();
                            } else if (response == JOptionPane.YES_OPTION) {
                                if (isDouble(amountTextField.getText())) {
                                    //checking to see if double or not
                                    try {
                                        acct.credit(Double.valueOf(amountTextField.getText()));
                                        wasCredited = true;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dispose();

                                } else {
                                    //not a double
                                    JOptionPane.showMessageDialog(null, "Please enter a valid number for debiting", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                }//end double if
                            } else if (response == JOptionPane.CLOSED_OPTION) {
                                onCancel();
                            }
                            wasAccountFound = true;
                        }
                        break;
                    case "CC":
                        CC cc = (CC) customer.accounts.get(i);
                        //Going through only the accounts registered to the ssn
                        if (cc.accountNumber.equalsIgnoreCase(accountNumberTextField.getText())) {
                            //checking to see if the acct num exsists for the customer
                            int response = JOptionPane.showConfirmDialog(null, "Are these details correct?\n"
                                            + "Social Security Num: " + customer.ssn + "\n" +
                                            " Account Number: " + cc.accountNumber + "\n" +
                                            "Current Balance: " + cc.balance + "\n" +
                                            "Balance to Debit: " + amountTextField.getText() + "\n" +
                                            "Final Balance: " + (Double.valueOf(cc.balance) + Double.valueOf(amountTextField.getText())), "Confirm",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (response == JOptionPane.NO_OPTION) {
                                onCancel();
                            } else if (response == JOptionPane.YES_OPTION) {
                                if (isDouble(amountTextField.getText())) {
                                    //checking to see if double or not
                                    try {
                                        acct.credit(Double.valueOf(amountTextField.getText()));
                                        wasCredited = true;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dispose();

                                } else {
                                    //not a double
                                    JOptionPane.showMessageDialog(null, "Please enter a valid number for debiting", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                }//end double if
                            } else if (response == JOptionPane.CLOSED_OPTION) {
                                onCancel();
                            }
                            wasAccountFound = true;
                        }
                        break;
                }

            }
            if (!wasAccountFound) {
                JOptionPane.showMessageDialog(null, "That Account was not found", "Account Not Found", JOptionPane.ERROR_MESSAGE);
            }
            if (wasCredited) {
                JOptionPane.showMessageDialog(null,
                        "Account Credited!",
                        "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            }
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
