import javax.swing.*;
import java.awt.event.*;

public class AddCDAccount extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField SSNTextField;
    private JTextField accountNumTextField;
    private JTextField balanceTextField;
    private JTextField interestRateTextField;
    private JTextField maturityDateTextField;

    public AddCDAccount() {
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
        AddCDAccount dialog = new AddCDAccount();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() throws Exception {
        int found = LookupCustomer.lookupUserIndex(SSNTextField.getText(), true);
        //Check if User Exists
        if (found != -99) {

            CD newCD = new CD(SSNTextField.getText(), balanceTextField.getText(), interestRateTextField.getText(),
                    accountNumTextField.getText(), maturityDateTextField.getText());

            Main.customers.get(found).accounts.add(newCD);
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

        dispose();

    }

    private void onCancel() {
        dispose();
        FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
        fmm.setSystemDateTime();
        fmm.pack();
        fmm.setVisible(true);
    }
}
