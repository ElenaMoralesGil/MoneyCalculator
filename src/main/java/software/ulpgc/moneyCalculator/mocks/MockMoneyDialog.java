package software.ulpgc.moneyCalculator.mocks;

import software.ulpgc.moneyCalculator.Currency;
import software.ulpgc.moneyCalculator.Money;
import software.ulpgc.moneyCalculator.MoneyDialog;

import java.util.List;


public class MockMoneyDialog implements MoneyDialog {
    private List<Currency> currencies;

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    @Override
    public Money get() {
        return new Money(200, currencies.get(0));
    }
}
