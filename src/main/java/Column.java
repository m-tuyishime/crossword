import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

public class Column {
    private Word word;
    private char content;

    private static final Map<Coordinates, Column> POOL = new ConcurrentHashMap<>();

    public static Column getInstance(Coordinates coordinates) {
        Column column = POOL.get(coordinates);
        if (column == null) {
            column = new Column();
            POOL.put(coordinates, column);
        }
        return column;
    }

    public boolean getIsFree(char charToInsert) {
        if (content == charToInsert || content == Character.MIN_VALUE)
            return true;
        return false;
    }

    public Word getWord() {
        return word;
    }

    public char getContent() {
        return content;
    }

    public void setContent(Word word, int wordIndex) {
        this.word = word;
        if (content == Character.MIN_VALUE) {
            if (word != null) {
                this.content = word.getWordChar(wordIndex);
            } else {
                Random rand = new Random();
                char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
                this.content = alphabet[rand.nextInt(alphabet.length)];
            }
        }
    }
}
