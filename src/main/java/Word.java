import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Word {
    private String word;
    private int arrangement;
    private int orientation;
    private ArrayList<Integer> possibleOrientations;
    private boolean found;
    private int[] startPosition = {Integer.MIN_VALUE, Integer.MIN_VALUE};

    private Random rand = new Random();


    public Word (String word) {
        this.word = word;
        arrangement = rand.nextInt(2); // {0 = forwards, 1 = backwards}
        possibleOrientations = new ArrayList<Integer>(Arrays.asList(0, 1, 2)); // {0 = horizontal, 1 = vertical, 2 = diagonal}
        orientation = possibleOrientations.get(rand.nextInt(possibleOrientations.size()));
        found = false;
    }

    public String getWord() {
        return word;
    }

    public int getWordLength() {
        return word.length();
    }

    public char[] getWordChars() {
        return word.toCharArray();
    }

    public int getArrangement() {
        return arrangement;
    }

    public int getOrientation() {
        return orientation;
    }

    public boolean getFoundStatus() {
        return found;
    }

    public int[] getStartPosition() {
        return startPosition;
    }

    public boolean isInTable() {
        if (startPosition[0] == Integer.MIN_VALUE && startPosition[1] == Integer.MIN_VALUE) 
            return false;
        return true;
    }

    public void setArrangement(int arrangement) {
        if (arrangement < 0 || arrangement > 1)
            throw new IllegalArgumentException("arrangement must be 0 (forwards) or 1 (backwards)");
        this.arrangement = arrangement;
    }

    public void setStartPosition(int[] startPosition) {
        if (startPosition.length != 2)
            throw new IllegalArgumentException("startPosition array must be of size 2");
        this.startPosition[0] = startPosition[0];
        this.startPosition[1] = startPosition[1];
    }

    public void changeFoundStatus() {
        found = !found;
    }

    public void changeArrangement() {
        if (arrangement == 0) arrangement = 1;
        else arrangement = 0;
    }

    public int changeOrientation() {
        if (possibleOrientations.size() < 1) {
            possibleOrientations.remove(Integer.valueOf(orientation));
            orientation = possibleOrientations.get(rand.nextInt(possibleOrientations.size()));
            return orientation;
        } else {
            return -1;
        }
    }
}
