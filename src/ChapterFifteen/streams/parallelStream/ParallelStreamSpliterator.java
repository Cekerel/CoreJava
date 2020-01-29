package ChapterFifteen.streams.parallelStream;

import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * ParallelStreamSpliterator
 */
public class ParallelStreamSpliterator {

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulator,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        // final String SENTENCE = "  Nel  mezzo del cammin di nostra vita  " + "mi  ritroval in una  selva oscura"
        //         + " ché la   dritta via era   smarrita ";
        // System.out.println("Found " + countWordsIteratively(SENTENCE) + " words.");

        // Stream<Character> charStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        // System.out.println("Found " + countWords(charStream) + " words.");

        // Stream<Character> parallelCharStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt)
        //         .parallel();
        // System.out.println("Found " + countWords(parallelCharStream) + " words.");

        System.out.println("a df rew rewq fhdksafhdskahflkdshakfdhsa".length());

        Spliterator<Character> spliterator = new WordCounterSpliterator("a df rew rewq fhdksafhdskahflkdshakfdhsa");
        Stream<Character> spliteratorStream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(spliteratorStream) + " words.");

    }
}

class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulator(char c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}

class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    /**
     * 该方法用于顺序处理每个元素，类似Iterator，如果还有元素要处理，则返回true，否则返回false
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));  //处理当前字符, 处理完之后下标自增
        return currentChar < string.length(); //倘若还有字符需要处理,则返回true, 否则返回false
    }

    /**
     * 这是为Spliterator专门设计的方法，区分与普通的Iterator，该方法会把当前元素划分一部分出去创
     * 建一个新的Spliterator作为返回，两个Spliterator变会并行执行，如果元素个数小到无法划分则返
     * 回null
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar; //获取还需要的字符子串的长度
        if (currentSize < 10) //当还需处理字符子串的长度足够小,则表示可以顺序处理
            return null;
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * 该方法用于表示该Spliterator有哪些特性,用于更好地控制和优化Spliterator的使用
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    /**
     * 该方法用于估算还剩下多少个元素需要遍历
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }
}