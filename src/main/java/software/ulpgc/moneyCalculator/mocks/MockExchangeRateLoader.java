package software.ulpgc.moneyCalculator.mocks;

import software.ulpgc.moneyCalculator.Currency;
import software.ulpgc.moneyCalculator.ExchangeRate;
import software.ulpgc.moneyCalculator.ExchangeRateLoader;

import java.time.LocalDate;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return new ExchangeRate(from, to, LocalDate.now(), 1.218);
    }
}
