package ChapterFourteen.future.CompletableFuture;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ChapterFourteen.future.CompletableFuture.ExchangeService.Money;

public class CompletableFutureTest {

    private static final List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"), new Shop("BuyItAll"), new Shop("Whatever"));

    private static final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    });

    public static void main(String[] args) {
        try {
            Class classTemp = Class.forName("ChapterFourteen.future.CompletableFuture.CompletableFutureTest");
            Method[] method = classTemp.getMethods();
            List<Method> methodList = Arrays.asList(method);
            List<String> methodNames = Arrays.asList("test10");
            methodList.stream().filter(m -> methodNames.contains(m.getName())).forEach(m -> {
                try {
                    m.invoke(m, null);
                    System.out.println();
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            ;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch blocke
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public static void test1() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("My favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returnes after " + invocationTime + " msecs");
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
        long retrievelTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievelTime + " msecs.");
    }

    public static void test2() {
        long start = System.nanoTime();
        System.out.println(findPricesV1("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");

    }

    public static void test3() {
        long start = System.nanoTime();
        System.out.println(findPricesParallelV1("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    public static void test4() {
        long start = System.nanoTime();
        System.out.println(findPricesFutureV1("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    public static void test5() {
        long start = System.nanoTime();
        System.out.println(findPricesFutureImprovedV1("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    public static void test6() {
        long start = System.nanoTime();
        System.out.println(findPricesFutureImprovedV2("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    public static void test7() {
        long start = System.nanoTime();
        System.out.println(findPricesV3("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    public static void test8() {
        long start = System.nanoTime();
        System.out.println(findPricesFutureV3("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    public static void test9() {
        long start = System.nanoTime();
        System.out.println(findPriceInUSD("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs.");
    }

    public static void test10() {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone27s")
                .map(f -> f.thenAccept(
                        s -> System.out.println(s + " (done in " + (System.nanoTime() - start) / 1_000_000 + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join(); // 4XXX msecs.
        // findPricesStream("myPhone27s").map(future ->
        // future.thenAccept(s -> System.out.println(s + "Done in " + (System.nanoTime() - start) / 1_000_000 + " msecs"))).forEach(future -> future.join()); //
        // 15XXX msecs
        System.out.println("All shops has responded in " + (System.nanoTime() - start) / 1_000_000 + " msecs.");
    }

    public static void test11() {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone27s")
                .map(future -> future.thenAccept(s -> System.out.println(s + " (done in " + (System.nanoTime() - start) / 1_000_000 + " msecs)"))).toArray(size -> new CompletableFuture[size]);
        CompletableFuture.anyOf(futures).join();
    }

    /**
     * 并行流处理
     * 
     * @param product
     * @return
     */
    public static List<String> findPricesParallelV1(String product) {
        return shops.parallelStream().map(s -> String.format("%s, Price is %.2f", s.getTitle(), s.getPriceV1(product)))
                .collect(Collectors.toList());
    }

    /**
     * 顺序流处理
     * 
     * @param product
     * @return
     */
    public static List<String> findPricesV1(String product) {
        return shops.stream().map(s -> String.format("%s, Price is %.2f", s.getTitle(), s.getPriceV1(product)))
                .collect(Collectors.toList());
    }

    public static List<String> findPricesV2(String product) {
        return shops.stream().map(s -> s.getPriceV2(product)).collect(Collectors.toList());
    }

    /**
     * 并行流创建多个Future
     * 
     * @param product
     * @return
     */
    public static List<String> findPricesFutureV1(String product) {
        List<CompletableFuture<String>> list = shops.parallelStream()
                .map(s -> CompletableFuture
                        .supplyAsync(() -> String.format("%s, Price is %.2f", s.getTitle(), s.getPriceV1(product))))
                .collect(Collectors.toList());

        return list.parallelStream().map(l -> l.join()).collect(Collectors.toList());
    }

    /**
     * 并行流创建多个Future
     * 
     * @param product
     * @return
     */
    public static List<String> findPricesFutureImprovedV1(String product) {
        List<CompletableFuture<String>> list = shops.stream()
                .map(s -> CompletableFuture.supplyAsync(
                        () -> String.format("%s, Price is %.2f", s.getTitle(), s.getPriceV1(product)), executor))
                .collect(Collectors.toList());

        return list.stream().map(l -> l.join()).collect(Collectors.toList());

    }

    /**
     *
     * @param product
     */
    public static List<String> findPricesFutureImprovedV2(String product) {
        List<CompletableFuture<String>> list = shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> s.getPriceV2(product), executor))
                .collect(Collectors.toList());
        return list.stream().map(l -> l.join()).collect(Collectors.toList());
    }

    /**
     *
     * @param product
     */
    public static List<String> findPricesV3(String product) {
        return shops.stream().map(shop -> shop.getPriceV2(product)).map(Quote::parse).map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public static List<String> findPricesFutureV3(String product) {
        List<CompletableFuture<String>> list = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceV2(product), executor))
                .map(future -> future.thenApply(Quote::parse).thenCompose( // thenCompose方法允许对两个异步操作进行流水线, 前一个操作完成时,
                                                                           // 则将前一个结果作为参数传递给后一个操作
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return list.stream().map(l -> l.join()).collect(Collectors.toList());
    }

    public static List<String> findPriceInUSD(String product) {
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            Future<Double> futurePriceInUSD = CompletableFuture.supplyAsync(() -> shop.getPriceV1(product)).thenCombine(
                    CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.CNY, Money.USD)),
                    (price, rate) -> price * rate);
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(" price is " + priceFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return prices;
    }

    /**
     * This method is used to demonstrate the way to combine two relavant future in
     * Java 7 And today I install the extension to remind me of taking a rest after
     * a long time of coding
     */
    public static List<String> findPricesInUSDJava7(String product) {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = service.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return ExchangeService.getRate(Money.CNY, Money.USD);
                }
            });
            Future<Double> futurePriceInUSD = service.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    try {
                        double priceInCNY = shop.getPriceV1(product);
                        return priceInCNY * futureRate.get();
                    } catch (InterruptedException | ExecutionException e) {
                        // TODO: handle exception
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(" price is " + priceFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return prices;
    }

    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceV2(product), executor)) // 获取价格信息范式
                .map(future -> future.thenApply(Quote::parse)).map(future -> future.thenCompose( // 解析信息范式
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }
}
