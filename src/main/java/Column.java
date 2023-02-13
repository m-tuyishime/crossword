import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Column {
    private Coordinates coordinates;
    private Word word;
    private char content;

    private Column(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    private static final Map<Coordinates, Column> POOL = new ConcurrentHashMap<>();

    public static Column getInstance(Coordinates coordinates) {
        Column column = POOL.get(coordinates);
        if (column == null) {
            column = new Column(coordinates);
            POOL.put(coordinates, column);
        }
        return column;
    }

    public boolean getIsFree(char charToInsert) {
        if (content == charToInsert || content == Character.MIN_VALUE) return true;
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
            } else 
                this.content = 'O';
        }
    }
}
