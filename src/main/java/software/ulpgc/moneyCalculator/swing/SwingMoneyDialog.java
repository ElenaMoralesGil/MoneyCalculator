package software.ulpgc.moneyCalculator.swing;


import software.ulpgc.moneyCalculator.Currency;
import software.ulpgc.moneyCalculator.CurrencyDialog;
import software.ulpgc.moneyCalculator.Money;
import software.ulpgc.moneyCalculator.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {

        setLayout(new FlowLayout()); // Set a BorderLayout with gaps
        amountField = new JTextField(10);
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        amountField.setForeground(Color.DARK_GRAY);
        amountField.setToolTipText("Enter amount");
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createAmountField());
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createAmountField() {
        JTextField textField = new JTextField();
        textField.setColumns(5);
        this.amountField = textField;
        return textField;
    }

    @Override
    public Money get() {
        String inputText = amountField.getText();
        Currency currency = currencyDialog.get();
        Money newMoney = new Money(0, currency);
        if (inputText.isEmpty()) {
            // Display an error dialog for empty input
            JOptionPane.showMessageDialog(null, "Please enter an amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                double amount = Double.parseDouble(inputText);
                // Create a new Money object if the input is a valid number
                newMoney = new Money(amount, currency);
            } catch (NumberFormatException e) {
                // Display an error dialog for invalid input
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return newMoney;
    }

    private double toDouble(String text) {
        return Double.parseDouble(text);
    }
}
