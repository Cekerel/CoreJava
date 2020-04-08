package ChapterFourteen.future.CompletableFuture;
import static ChapterFourteen.future.CompletableFuture.Util.delay;


public class ExchangeService {
    public enum Money {
        CNY(1.0), EUR(7.8215), USD(7.0841), JPY(0.0639), GBP(8.2572);

        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    public static double getRateWithDelay(Money source, Money destination) {
        delay();
        return destination.rate / source.rate;
    }
}