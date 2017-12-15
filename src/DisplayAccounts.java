import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.util.*;

public class DisplayAccounts {
    private JPanel panel1;
    private JButton Submit;
    private JList AccountList;
    private JTextField selectAccountNumber;
    private JTextField SSNTextField;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ObservableList<Object> userAccounts = new ObservableList<Object>() {

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Object> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Object get(int index) {
                return null;
            }

            @Override
            public Object set(int index, Object element) {
                return null;
            }

            @Override
            public void add(int index, Object element) {

            }

            @Override
            public Object remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Object> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Object> listIterator(int index) {
                return null;
            }

            @Override
            public List<Object> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(ListChangeListener<? super Object> listener) {

            }

            @Override
            public void removeListener(ListChangeListener<? super Object> listener) {

            }

            @Override
            public boolean addAll(Object... elements) {
                return false;
            }

            @Override
            public boolean setAll(Object... elements) {
                return false;
            }

            @Override
            public boolean setAll(Collection<?> col) {
                return false;
            }

            @Override
            public boolean removeAll(Object... elements) {
                return false;
            }

            @Override
            public boolean retainAll(Object... elements) {
                return false;
            }

            @Override
            public void remove(int from, int to) {

            }
        };

        //Search for User based on SSN

        //Get Accounts

        //Add accounts to list
        for(int i=0; i<=Main.customers.size(); i++){
            //Find all user accounts
            String account = Main.customers.get(i).ssn;
            if(account.compareToIgnoreCase(SSNTextField.getText()) == 0){
                for(int x = 0; x<= Main.customers.get(i).accounts.size(); x++){
                    if(Main.customers.get(i).accounts.get(x).type.compareToIgnoreCase("Checking") == 0 ||
                            Main.customers.get(i).accounts.get(x).type.compareToIgnoreCase("Savings") == 0 ){
                        userAccounts.add(Main.customers.get(i).accounts.get(x));
                    }//End if
                }//end For
            }//end if

                    //find all checking and savings accounts

            //Add checking and Savings accounts to list userAccounts
        }//End For




    }
}
