import javax.swing.*;
import java.awt.event.*;

public class AddBankAccount extends JDialog {
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

    public AddBankAccount() {
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
        AddBankAccount dialog = new AddBankAccount();
        dialog.pack();
        dialog.setVisible(true);

    }
    private void onOK() throws Exception {
        //dispose();
        System.out.println("Ok clicked in add Account");
        int found = LookupCustomer.lookupUserIndex(SSNTextField.getText(), true);
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
                }
            }

        System.out.println("Account Added \nSaving to Database...");
        Thread.sleep(25);
        Main.saveDB();
        System.out.println("Saved to Database.");
        dispose();
        FirstMainMenu redirect = new FirstMainMenu(Main.activeUser);
        redirect.setSystemDateTime();
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
        mm.setSystemDateTime();
        mm.pack();
        mm.setVisible(true);
    }
}