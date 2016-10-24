/**
 * Created by Dotinschool6 on 10/23/2016.
 */
public class LongTermDeposit extends Deposit {
    public LongTermDeposit(String customerNumber) {
        super(customerNumber);
        this.setInterestRate(0.2);
    }

}
