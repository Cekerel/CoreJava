package ChapterFourteen.future.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import static ChapterFourteen.future.CompletableFuture.Util.delay;

public class Shop {

    private String title;

    public Shop(String title) {
        this.title = title;
    }

    private  double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }


    public double getPriceV1(String product) {
        return calculatePrice(product);
    }

    public String getPriceV2(String product) {
        double price = calculatePrice(product);
        Random randmom = new Random();
        Discount.Code code = Discount.Code.values()[randmom.nextInt(Discount.Code.values().length)];
        return String.format("%s:%,2f:%s", title, price, code);
    }

    // public Future<Double> getPriceAsync(String product) {
    // CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    // new Thread(() -> {
    // try {
    // double price = calculatePrice(product);
    // futurePrice.complete(price);
    // } catch (Exception e) {
    // futurePrice.completeExceptionally(e);// TODO: handle exception
    // }
    // }).start();
    // return futurePrice;
    // }
    public Future<Double> getPriceAsync(String product) {
        // return CompletableFuture.supplyAsync(new Supplier<Double>() {
        // @Override
        // public Double get() {
        // return calculatePrice(product);
        // }
        // });
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}