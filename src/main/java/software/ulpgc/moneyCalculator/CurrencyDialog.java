package software.ulpgc.moneyCalculator;



import java.util.List;

public interface CurrencyDialog {
    CurrencyDialog define(List<Currency> currencies);
    Currency get();
}
