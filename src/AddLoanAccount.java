import javax.swing.*;
import java.awt.event.*;

public class AddLoanAccount extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox typeComboBox;
    private JTextField SSNTextField;
    private JTextField interestRateTextField;
    private JTextField monthDueDateTextField;
    private JTextField balanceTextField;
    private JTextField initialDateTextField;
    private JTextField accountNumTextField;

    public AddLoanAccount() {
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
        AddLoanAccount dialog = new AddLoanAccount();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
        int found = LookupCustomer.lookupUserIndex(SSNTextField.getText(), false);
        Loan newLoan;
        if (found != -99) {
            if (typeComboBox.getSelectedItem().toString().equalsIgnoreCase("Long term")) {
                newLoan = new Loan(SSNTextField.getText(),
                        "30",
                        "long",
                        balanceTextField.getText(),
                        interestRateTextField.getText(),
                        monthDueDateTextField.getText(),//Due Date
                        "11102017",//Last payment due date set to null by default
                        "50.00",
                        initialDateTextField.getText(),
                        "0");
                Main.customers.get(found).accounts.add(newLoan);
            } else if (typeComboBox.getSelectedItem().toString().equalsIgnoreCase("Short Term")) {
                newLoan = new Loan(SSNTextField.getText(),
                        "15",
                        "short",
                        balanceTextField.getText(),
                        interestRateTextField.getText(),
                        monthDueDateTextField.getText(),//Due Date
                        "11102017",//Last payment due date set to null by default
                        "50.00",
                        initialDateTextField.getText(),
                        "0");
                Main.customers.get(found).accounts.add(newLoan);
            } else if (typeComboBox.getSelectedItem().toString().equalsIgnoreCase("Credit Card")) {
                CC newCC = new CC(SSNTextField.getText(), accountNumTextField.getText(),
                        balanceTextField.getText(),
                        interestRateTextField.getText(),
                        "12102022",//Date?
                        "11102017",//Last Payment Date
                        monthDueDateTextField.getText(),
                        "10",//Monthly payment as calculated by calcInterest?
                        Main.dateString,//Open date
                        "0"
                );
                Main.customers.get(found).accounts.add(newCC);
            }
        }//end outter if
        dispose();
    }//end on ok

    private void onCancel() {
        dispose();
        FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
        fmm.setSystemDateTime();
        fmm.pack();
        fmm.setVisible(true);
    }
}
