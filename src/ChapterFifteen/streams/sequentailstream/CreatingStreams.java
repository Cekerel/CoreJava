package ChapterFifteen.streams.sequentailstream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * CreatingStreams
 */
public class CreatingStreams {

    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.print(title + ": ");
        int size = firstElements.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                System.out.println(", ");
            }
            if (i < SIZE) {
                System.out.println(firstElements.get(i));
            } else
                System.out.println("...");
        }
        System.out.println();
    }

    public static <T> void showElements(String title, Stream<T> stream) {
        System.out.println(title + ": ");
        List<T> list = stream.collect(Collectors.toList());
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n");
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\ChapterSeven\\LoggingImageViewer.java");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);
        Stream<String> songs = Stream.of("gently", "down", "the", "stream");
        show("songs", songs);
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<BigInteger[]> fibonacciStream = Stream.iterate(new BigInteger[] {BigInteger.ZERO, BigInteger.ONE}, t -> new BigInteger[] {t[1], t[0].add(t[1])});
        showElements("Fibonacci Stream", fibonacciStream.map(t -> t[0]).limit(100));

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);
        
        try (Stream<String> line = Files.lines(path, StandardCharsets.UTF_8)){
            show("line", line);
        }
    }
}