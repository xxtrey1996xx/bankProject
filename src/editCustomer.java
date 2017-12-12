import javax.swing.*;
import java.awt.event.*;

public class editCustomer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField SSNTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField addressTextField;
    private JTextField cityTextField;
    private JTextField stateTextField;
    private JTextField zipCodeTextField;
    private Customer customer;

    public editCustomer() {

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
        editCustomer dialog = new editCustomer();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() throws Exception {
        dispose();
        //Setting new values
        customer.firstName = firstNameTextField.getText();
        customer.lastName = lastNameTextField.getText();
        customer.streetAddress = addressTextField.getText();
        customer.city = cityTextField.getText();
        customer.state = stateTextField.getText();
        customer.zip = zipCodeTextField.getText();
        FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
        Main.saveDB();
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomerDetails() {
        SSNTextField.setText(customer.ssn);
        SSNTextField.setEnabled(false);
        firstNameTextField.setText(customer.firstName);
        lastNameTextField.setText(customer.lastName);
        addressTextField.setText(customer.streetAddress);
        cityTextField.setText(customer.city);
        stateTextField.setText(customer.state);
        zipCodeTextField.setText(customer.zip);
    }
}
