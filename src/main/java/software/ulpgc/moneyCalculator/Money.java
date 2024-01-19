package software.ulpgc.moneyCalculator;

public record Money(double amount, Currency currency) {
    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
