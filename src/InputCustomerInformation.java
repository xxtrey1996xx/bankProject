import javax.swing.*;
import java.awt.event.*;

public class InputCustomerInformation extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField SSNTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField addressTextField;
    private JTextField cityTextField;
    private JTextField stateTextField;
    private JTextField zipcodeTextField;

    public InputCustomerInformation() {
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
        InputCustomerInformation dialog = new InputCustomerInformation();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
        dispose();
        //This adds a new customer to the arraylist
        Main.customers.add(new Customer(SSNTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(),
                cityTextField.getText(), stateTextField.getText(), zipcodeTextField.getText()));
        AddAccount acct = new AddAccount();
        acct.pack();
        acct.setVisible(true);
    }

    private void onCancel() {
        dispose();
        FirstMainMenu fmm = new FirstMainMenu();
        fmm.pack();
        fmm.setVisible(true);
    }
}
