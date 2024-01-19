package software.ulpgc.moneyCalculator.spark;

import software.ulpgc.moneyCalculator.Currency;
import software.ulpgc.moneyCalculator.ExchangeRate;
import software.ulpgc.moneyCalculator.fixerws.FixerExchangeRateLoader;

public class FixerSparkAdapter implements SparkAdapter {

    private final FixerExchangeRateLoader loader;

    public FixerSparkAdapter() {
        this.loader = new FixerExchangeRateLoader();
    }

    public String getExchangeRate(String fromCurrencyCode, String toCurrencyCode, double amount) {
        Currency fromCurrency = new Currency(fromCurrencyCode, "");
        Currency toCurrency = new Currency(toCurrencyCode, "");
        ExchangeRate rate = loader.load(fromCurrency, toCurrency);

        if (rate == null) {
            return "Unable to find exchange rate.";
        }

        double exchangedAmount = rate.rate() * amount;
        return String.format("The amount from %s to %s is %.2f", fromCurrencyCode, toCurrencyCode, exchangedAmount);
    }
}
