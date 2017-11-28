import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstMainMenu extends JDialog {
    private JPanel contentPane;
    private JButton openAccountButton;
    private JButton closeSystemButton;
    private JButton closeAccountButton;

    public FirstMainMenu() {
        setContentPane(contentPane);
        setModal(true);

        openAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOpenAccount();
            }
        });

        closeAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCloseAccount();
            }
        });

        closeSystemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCloseSystem();
            }
        });
    }

    public static void main(String[] args) {
        FirstMainMenu dialog = new FirstMainMenu();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOpenAccount() {
        System.out.println("open account clicked");
        //Going to open a new menu that allows data to be entered
        dispose();
        AddAccount acct = new AddAccount();
        acct.pack();
        acct.setVisible(true);
    }

    private void onCloseAccount() {
        System.out.println("close account clicked");
        //Going to open a new menu that allows accounts to be closed
        dispose();
    }

    private void onCloseSystem() {
        System.out.println("close system clicked");
        //Going to close system, possibly will be the force save
        dispose();
        System.exit(0);
    }
}