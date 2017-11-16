import javax.swing.*;
import java.awt.event.*;

public class addAccount extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField SSNTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JComboBox acctTypecomboBox1;

    public addAccount() {
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

    private void onOK() {
        System.out.println("Ok clicked in add Account");
        if (acctTypecomboBox1.getSelectedItem() == "Checking") {
            //This is where we will set a new Checking Account
            new Checking();
        } else if (acctTypecomboBox1.getSelectedItem() == "Savings") {
            //This is where we will set a new Savings account
            new Savings();
        }
        //TODO Finish updating this-
        dispose();
    }

    private void onCancel() {
        //Closes the current box.
        dispose();
        firstMainMenu mm = new firstMainMenu();
        mm.pack();
        mm.setVisible(true);
    }

    public static void main(String[] args) {
        addAccount dialog = new addAccount();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
