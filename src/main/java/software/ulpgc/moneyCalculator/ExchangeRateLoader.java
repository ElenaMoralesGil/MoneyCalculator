package software.ulpgc.moneyCalculator;


public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
