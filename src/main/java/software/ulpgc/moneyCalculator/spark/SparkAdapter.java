package software.ulpgc.moneyCalculator.spark;

public interface SparkAdapter {
    String getExchangeRate(String fromCurrency, String toCurrency, double amount);
}
