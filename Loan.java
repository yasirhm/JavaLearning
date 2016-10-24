/**
 * Created by Dotinschool6 on 10/23/2016.
 */
public class Loan extends Deposit {
    public Loan(String customerNumber) {
        super(customerNumber);
        this.setInterestRate(0.0);
    }
}
