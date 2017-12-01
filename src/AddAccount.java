import javax.swing.*;
import java.awt.event.*;

public class AddAccount extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox acctTypecomboBox1;
    private JTextField SSNTextField;
    private JTextField accountNumTextField;
    private JTextField balanceTextField;
    private JTextField interestRateTextField;
    private JTextField dateTextField;
    private JCheckBox OAcheckbox;
    public AddAccount() {
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

    public static void main(String[] args) throws Exception {
        AddAccount dialog = new AddAccount();
        dialog.pack();
        dialog.setVisible(true);

    }
    private void onOK() throws Exception {
        //dispose();
        System.out.println("Ok clicked in add Account");
        int found = LookupCustomer.lookupUser(SSNTextField.getText());
        //Check if User Exists
            if(found!=-99) {
                switch (acctTypecomboBox1.getSelectedItem().toString()) {
                    case "Checking":
                        //TODO Figure out how to handle checking overdraft - My Suggestion is translate it to a String - Zach
                        Checking newChecking = new Checking(SSNTextField.getText(),balanceTextField.getText(),interestRateTextField.getText(),
                                accountNumTextField.getText(),checkingStatus(balanceTextField.getText()),dateTextField.getText(),true);
                        Main.customers.get(found).accounts.add(newChecking);
                        break;

                        case "Savings":
                        Savings newSavings = new Savings(SSNTextField.getText(),balanceTextField.getText(),interestRateTextField.getText(),
                                accountNumTextField.getText(),"Savings", dateTextField.getText());
                        Main.customers.get(found).accounts.add(newSavings);
                        break;

                    case "CD":
                        CD newCD = new CD(SSNTextField.getText(), balanceTextField.getText(), interestRateTextField.getText(),
                                accountNumTextField.getText(),dateTextField.getText());
                        Main.customers.get(found).accounts.add(newCD);
                        break;

                    case "CC":
                        CC newCC = new CC(accountNumTextField.getText(),balanceTextField.getText(), interestRateTextField.getText(),dateTextField.getText());
                        Main.customers.get(found).accounts.add(newCC);
                }
            }

        if (acctTypecomboBox1.getSelectedItem() == "Checking") {
            //boolean overdraftProtection = overdraftCheckBox.
            //Check if User Exists
            if (found != -99) {//User Found
                //Create Checking account and add to user account arraylist
                Main.customers.get(found).accounts.add(new Checking(SSNTextField.getText(), balanceTextField.getText(), interestRateTextField.getText(),
                        accountNumTextField.getText(), "TMB", dateTextField.getText(), true));
                //TODO We need to update to allow for the different checking account types. I.E tmb gold diamond. Also, hasOverdraftACCT
            }
        } else if (acctTypecomboBox1.getSelectedItem() == "Savings") {
            //This is where we will set a new Savings account
        }

        System.out.println("Account Added \nSaving to Database...");
        Main.saveDB();
        System.out.println("Saved to Database.");
        dispose();
        FirstMainMenu redirect = new FirstMainMenu(Main.activeUser);
        redirect.pack();
        redirect.setVisible(true);
    }

    private String checkingStatus(String text) {
        String type;
        double initialBalance = Double.valueOf(text);
        double temp = initialBalance;
        if (initialBalance > 1500)
            text = "Diamond";
        else if(initialBalance > 1000)
            text = "Gold";
        else
            text = "TMB";
        return text;
    }

    private void onCancel() {
        //Closes the current box.
        dispose();
        FirstMainMenu mm = new FirstMainMenu(Main.activeUser);
        mm.pack();
        mm.setVisible(true);
    }
}