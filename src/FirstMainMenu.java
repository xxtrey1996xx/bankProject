import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstMainMenu extends JDialog {
    private JPanel contentPane;
    private JButton openAccountButton;
    private JButton closeSystemButton;
    private JButton closeAccountButton;
    private JButton lookupCustomerButton;
    private JButton hideButton;
    private JButton withdrawalButton;
    private JButton depositButton;
    private JTextField systemDateAndTimeTextField;
    private JButton updateDateButton;
    public static String acctType = null;//This is a "flag" used to determine which accttype button was used for new and exsisting page
    private JButton openLoanButton;
    private String activeUser;
    private JButton openCDButton;
    public FirstMainMenu(String activeUser) {
        this.activeUser = activeUser;
        //This is for controlling what the tellers can see as apposed to managers
        if (activeUser.compareToIgnoreCase("teller") == 0) {
        } else if (activeUser.compareToIgnoreCase("manager") == 0) {
            openLoanButton.setVisible(true);
            openCDButton.setVisible(true);
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
        updateDateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dateStr = systemDateAndTimeTextField.getText();
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date newDate = Main.myDate;
                try {
                    newDate = formatter.parse(dateStr);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                try {
                    onUpdateDate(newDate);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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
        openCDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOpenCD();
            }
        });
        openLoanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOpenLoan();
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

    public void setSystemDateTime() {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = date.format(Main.myDate);
        systemDateAndTimeTextField.setText(strDate);
    }



    //Change Detected

    private void onOpenAccount() {
        acctType = "bank";
        System.out.println("open account clicked");
        //Going to open a new menu that allows data to be entered
        dispose();
        New_Existing ne = new New_Existing();
        ne.pack();
        ne.setVisible(true);
    }

    private void onUpdateDate(Date newDate) throws Exception {
        Date previousDate = Main.myDate;
        int difference = previousDate.compareTo(newDate);

        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        String strPrevDate = date.format(previousDate);
        String strCurrentDate = date.format(newDate);

        JOptionPane.showMessageDialog(null, "Date Changed Successfully\nNew Date: " + strCurrentDate +
                "\nPrevious Date was " + strPrevDate, "Successful Date Change", JOptionPane.INFORMATION_MESSAGE);
        Main.myDate = newDate;
        Main.dateString = strCurrentDate;
        Main.saveDB();
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

    private void onOpenCD() {
        dispose();
        acctType = "CD";
        New_Existing ne = new New_Existing();
        ne.pack();
        ne.setVisible(true);
    }

    private void onOpenLoan() {
        dispose();
        acctType = "loan";
        New_Existing ne = new New_Existing();
        ne.pack();
        ne.setVisible(true);
    }
}