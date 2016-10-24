/**
 * Created by Dotinschool6 on 10/23/2016.
 */

import java.math.BigDecimal;

public abstract class Deposit {

    private String customerNumber ;
    private Integer durationInDays;
    private BigDecimal depositBalance = BigDecimal.ZERO;
    private Double interestRate;


    public Deposit(String customerNumber) {
        this.customerNumber = customerNumber;
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

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer calculatePayedInterest(){
        BigDecimal Pi = (depositBalance.multiply(new BigDecimal(interestRate)).multiply(new BigDecimal(durationInDays)));
        return Pi.intValue()/36500;
    }
}
