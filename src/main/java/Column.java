import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Column {
    private Coordinates coordinates;
    private boolean isFree;
    private Word word;
    private char content;

    private Column(Coordinates coordinates) {
        this.coordinates = coordinates;
        isFree = true;
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

    public boolean getIsFree() {
        return isFree;
    }

    public Word getWord() {
        return word;
    }

    public char getContent() {
        return content;
    }

    public void setContent(Word word, char content) {
        this.word = word;
        this.content = content;
        isFree = false;
    }
}
