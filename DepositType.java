/**
 * Created by Yasi on 10/24/2016.
 */
public abstract class DepositType
{
    private Double interestRate;

    public DepositType(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getInterestRate() {
        return interestRate;
    }
}
