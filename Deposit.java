/**
 * Created by Yasi on 10/23/2016.
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Deposit
{
    private String customerNumber;
    private Integer durationInDays;
    private BigDecimal depositBalance = BigDecimal.ZERO;
    private DepositType depositType;
    private BigDecimal payedInterest;

    public Deposit(String customerNumber, Integer durationInDays, BigDecimal depositBalance, DepositType depositType) throws BalanceException, DurationDaysException {
        this.customerNumber = customerNumber;
        this.depositType = depositType;

        if (depositBalance.compareTo(new BigDecimal(0)) == -1) {
            throw new BalanceException("Deposit balance for customer " + customerNumber + " is less than zero!!");
        } else if (durationInDays <= 0) {
            throw new DurationDaysException("Duration Days for customer " + customerNumber + " is not valid!!");
        } else {
            this.depositBalance = depositBalance;
            this.durationInDays = durationInDays;
        }
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Integer getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Integer durationInDays) {
        this.durationInDays = durationInDays;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    public void calculatePayedInterest() {

        BigDecimal payedInterests = depositBalance.multiply(new BigDecimal(depositType.getInterestRate()).multiply(new BigDecimal(durationInDays)));
        payedInterest = payedInterests.divide(new BigDecimal(36500), 3, RoundingMode.CEILING);
    }
}
