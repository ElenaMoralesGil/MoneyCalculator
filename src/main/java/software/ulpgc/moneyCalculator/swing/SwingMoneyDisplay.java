package software.ulpgc.moneyCalculator.swing;

import software.ulpgc.moneyCalculator.Money;
import software.ulpgc.moneyCalculator.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    @Override
    public void show(Money money) {
        this.setText(money.toString());
    }
}
