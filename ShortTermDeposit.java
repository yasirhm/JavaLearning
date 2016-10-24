/**
 * Created by Dotinschool6 on 10/23/2016.
 */
public class ShortTermDeposit extends Deposit {
    public ShortTermDeposit(String customerNumber) {
        super(customerNumber);
        this.setInterestRate(0.1);
    }
}
