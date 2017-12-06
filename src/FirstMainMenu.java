import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class FirstMainMenu extends JDialog {
    private JPanel contentPane;
    private JButton openAccountButton;
    private JButton closeSystemButton;
    private JButton closeAccountButton;
    private JButton lookupCustomerButton;
    private JButton hideButton;
    private JButton withdrawelButton;
    private JButton depositButton;
    private JTextField systemDateAndTimeTextField;
    private String activeUser;

    public FirstMainMenu(String activeUser) {
        this.activeUser = activeUser;
        //This is for controlling what the tellers can see as apposed to managers
        if (activeUser.compareToIgnoreCase("teller") == 0) {
        } else if (activeUser.compareToIgnoreCase("manager") == 0) {
        } else if (activeUser.compareToIgnoreCase("customer") == 0) {
            openAccountButton.setVisible(false);
            closeAccountButton.setVisible(false);
            lookupCustomerButton.setVisible(false);
        }
        setContentPane(contentPane);
        setModal(true);


        openAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOpenAccount();
            }
        });

        closeAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCloseAccount(activeUser);
            }
        });

        closeSystemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCloseSystem();
            }
        });

        lookupCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLookupCustomer();//Performs button event
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCloseSystem();
            }
        });

    }

    public void main(String[] args) {
        FirstMainMenu dialog = new FirstMainMenu(Main.activeUser);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void checkCredentials(String activeUser) {
        if (activeUser.compareToIgnoreCase("teller") == 0) {
            hideButton.setVisible(false);

        }
    }

    //todo here zach
    public void setSystemDateTime() {
        systemDateAndTimeTextField.setText(Main.systemDate.toString());
    }

    public void updateSystemDateTime(int year, int month, int day) {
        Main.systemDate.setYear(year);
        Main.systemDate.setMonth(month);
        Main.systemDate.setDate(day);
    }

    //Change Detected

    private void onOpenAccount() {
        System.out.println("open account clicked");
        //Going to open a new menu that allows data to be entered
        dispose();
        New_Existing ne = new New_Existing();
        ne.pack();
        ne.setVisible(true);
    }

    private void onCloseAccount(String activeUser) {
        System.out.println("close account clicked");
        checkCredentials(activeUser);
        //Going to open a new menu that allows accounts to be closed
        dispose();
    }

    private void onCloseSystem() {
        System.out.println("close system clicked");
        //Going to close system, possibly will be the force save
        dispose();
        System.exit(0);
    }

    private void onLookupCustomer() {
        dispose();
        LookupCustomer luc = new LookupCustomer();
        luc.pack();
        luc.setVisible(true);
    }
}