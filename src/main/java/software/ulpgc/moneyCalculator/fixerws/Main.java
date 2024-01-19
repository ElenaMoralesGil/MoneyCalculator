package software.ulpgc.moneyCalculator.fixerws;


import software.ulpgc.moneyCalculator.Currency;
import software.ulpgc.moneyCalculator.CurrencyLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new FixerCurrencyLoader();
        List<Currency> currencies = currencyLoader.load();
        for (Currency currency : currencies) {
            System.out.println(currency);
        }
    }
}
