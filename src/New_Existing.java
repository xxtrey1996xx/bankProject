import javax.swing.*;
import java.awt.event.*;

public class New_Existing extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton newCustomerButton;
    private JButton existingCustomerButton;

    public New_Existing() {
        setContentPane(contentPane);
        setModal(true);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        newCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNewCustomer();
                System.out.println("New Customer Clicked");
            }
        });

        existingCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExistingCustomer();
                System.out.println("Existing Customer Clicked");
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
        New_Existing dialog = new New_Existing();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onCancel() {
        //Goes backwards
        dispose();
        FirstMainMenu fmm = new FirstMainMenu(Main.activeUser);
        fmm.setSystemDateTime();
        fmm.pack();
        fmm.setVisible(true);
    }

    private void onNewCustomer() {
        //launches new InputCustomer menu
        dispose();
        InputCustomerInformation ici = new InputCustomerInformation();
        ici.pack();
        ici.setVisible(true);
    }

    private void onExistingCustomer() {
        //Launches new Add account menu
        dispose();
        if (FirstMainMenu.acctType.equalsIgnoreCase("bank")) {
            AddBankAccount aa = new AddBankAccount();
            aa.pack();
            aa.setVisible(true);
        } else if (FirstMainMenu.acctType.equalsIgnoreCase("CD")) {
            AddCDAccount ac = new AddCDAccount();
            ac.pack();
            ac.setVisible(true);
        } else if (FirstMainMenu.acctType.equalsIgnoreCase("loan")) {
            AddLoanAccount al = new AddLoanAccount();
            al.pack();
            al.setVisible(true);
        } else {
            System.exit(1);
        }
    }
}
