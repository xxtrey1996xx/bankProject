import javax.swing.*;
import java.awt.event.*;

public class LogInScreen extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField userNameTextField;
    private JPasswordField passwordPasswordField;

    public LogInScreen() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main(String[] args) {
        LogInScreen dialog = new LogInScreen();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(1);
    }

    //This method checks credentials
    public static void checkCredentials(String userName, String password) {
        if (userName.compareToIgnoreCase("teller") == 0 && password.compareToIgnoreCase("password") == 0) {
            //This means that a teller has inputted their creds
            Main.activeUser = "teller";
            FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
            fmm.setSystemDateTime();
            fmm.pack();
            fmm.setVisible(true);
        } else if (userName.compareToIgnoreCase("manager") == 0 && password.compareToIgnoreCase("password") == 0) {
            //THis means that a manager has inputted their creds
            Main.activeUser = "manager";
            FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
            fmm.setSystemDateTime();
            fmm.pack();
            fmm.setVisible(true);
        } else if (userName.compareToIgnoreCase("customer") == 0 && password.compareToIgnoreCase("0000") == 0) {
            //THis means that a customer has inputted their creds
            Main.activeUser = "customer";
            FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
            fmm.setSystemDateTime();
            fmm.pack();
            fmm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "The username or password entered was incorrect.", "Incorrect credentials", JOptionPane.ERROR_MESSAGE);
            LogInScreen lis = new LogInScreen();
            lis.pack();
            lis.setVisible(true);

        }
    }

    private void onOK() {
        dispose();
        checkCredentials(userNameTextField.getText(), passwordPasswordField.getText());
    }

    private void onCancel() {
        dispose();
    }
}
