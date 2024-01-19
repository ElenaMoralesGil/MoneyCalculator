package software.ulpgc.moneyCalculator.mocks;

import software.ulpgc.moneyCalculator.Money;
import software.ulpgc.moneyCalculator.MoneyDisplay;

public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void show(Money money) {
        System.out.println(money);
    }
}
