import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CD extends Account {

    public String ssn, balance, interest, acctNum, maturityDate;

    //Constructor for CD
    public CD(String ssn, String balance, String interest, String acctNum, String date) {
        this.ssn = ssn;
        this.balance = balance;
        this.interest = interest;
        this.acctNum = acctNum;
        this.maturityDate = date;
    }//End of Constructor


    @Override
    public String getBalance() {
        return balance;
    }

    @Override
    public String getInterestRate() {
        return interestRate;
    }

    @Override
    public String getType() {
        return getType();
    }

    @Override
    public void credit() {
        //TODO Only 1 Credit Allowed upon purchase of CD
    }

    @Override
    public void debit() {
        //TODO Check Maturity Date VRS Current Date if Valid -> Dispense Cash and Close Account
    }

    public void cashOutCD(String date) {
        if (compareDates(date, maturityDate)) {
            //Dispense Cash
            System.out.println("Dispense Cash in Amount of " + balance);
            //TODO Close Account

        }
    }


    @Override
    public void debit(double amount) {
        //This is unused by this class;
    }

    @Override
    public double calcInterest() {
        return 0;
    }


    public static boolean compareDates(String currentDate, String maturityDate) {
        try {
            // If you already have date objects then skip 1

            //1
            // Create 2 dates starts
            SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy");
            Date date1 = sdf.parse(currentDate);
            Date date2 = sdf.parse(maturityDate);


            System.out.println("Date1" + sdf.format(date1));
            System.out.println("Date2" + sdf.format(date2));
            System.out.println();

            // Create 2 dates ends
            //1

            // Date object is having 3 methods namely after,before and equals for comparing
            // after() will return true if and only if date1 is after date 2
            if (date1.after(date2) || (date1.equals(date2))) {
                System.out.println("CD is Mature");
                return true;
            }
            // before() will return true if and only if date1 is before date2
            if (date1.before(date2)) {
                System.out.println("CD is not Mature");
                return false;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();

        }
        //default to false;
        return false;
    }

}
