/**
 * Created by Dotinschool6 on 10/23/2016.
 */

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Deposit {

    private String customerNumber ;
    private Integer durationInDays;
    private BigDecimal depositBalance = BigDecimal.ZERO;

    private DepositType depositType;
    private BigDecimal payedInterest;


    public Deposit(String customerNumber, Integer durationInDays, BigDecimal depositBalance,DepositType depositType) {//
        this.customerNumber = customerNumber;
        this.depositType = depositType;
        this.durationInDays = durationInDays;
        this.depositBalance = depositBalance;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

   /* public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }*/

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

    /*public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }*/

    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    public void calculatePayedInterest(){

        BigDecimal Pi = depositBalance.multiply(new BigDecimal(depositType.getInterestRate()).multiply(new BigDecimal(durationInDays) ) ) ;
        payedInterest =  Pi.divide(new BigDecimal(36500), 3, RoundingMode.CEILING);
    }
}
