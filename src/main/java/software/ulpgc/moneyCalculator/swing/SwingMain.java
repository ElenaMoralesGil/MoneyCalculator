package software.ulpgc.moneyCalculator.swing;


import software.ulpgc.moneyCalculator.*;
import software.ulpgc.moneyCalculator.fixerws.FixerCurrencyLoader;
import software.ulpgc.moneyCalculator.fixerws.FixerExchangeRateLoader;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private TimeSeriesDisplay timeSeriesDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {

        SwingMain main = new SwingMain();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                new FixerExchangeRateLoader(),
                main.moneyDisplay());
        main.add("exchange money", command);
        main.setVisible(true);
    }

    public SwingMain() throws HeadlessException {
        setTitle("MoneyCalculator");
        setSize(800, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.add(createMoneyDialog());
        this.add(createCurrencyDialog());
        this.add(createMoneyDisplay());
        this.add(toolbar());
    }

    private Component toolbar() {
        JButton button = new JButton("calculate");
        button.setFont(new Font("Serif", Font.BOLD, 16));
        button.setForeground(Color.BLUE);
        button.setToolTipText("This is an example button");
        button.addActionListener(e -> commands.get("exchange money").execute());
        return button;
    }
    private Component createTimeSeriesDisplay() {
        SwingTimeSeriesDisplay display = new SwingTimeSeriesDisplay();
        this.timeSeriesDisplay = display;
        return display;
    }
    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    private void add(String name, Command command) {
        commands.put(name, command);
    }

    private TimeSeriesDisplay timeSeriesDisplay() {
    return timeSeriesDisplay;
    }
    private MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    private CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    private MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}
