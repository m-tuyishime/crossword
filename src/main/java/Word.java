import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Word {
    private String word;
    private int arrangement;
    private int orientation;
    private ArrayList<Integer> possibleOrientations;
    private boolean found;
    private boolean isInTable;

    private Random rand = new Random();


    public Word (String word) {
        this.word = word;
        arrangement = rand.nextInt(2); // {0 = forwards, 1 = backwards}
        possibleOrientations = new ArrayList<Integer>(Arrays.asList(0, 1, 2)); // {0 = horizontal, 1 = vertical, 2 = diagonal}
        orientation = possibleOrientations.get(rand.nextInt(possibleOrientations.size()));
        found = false;
        isInTable = false;
    }

    public String getWord() {
        return word;
    }

    public int getWordLength() {
        return word.length();
    }

    public char getWordChar(int i) {
        return word.toCharArray()[i];
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

    public boolean isInTable() {
        return isInTable;
    }

    public void setInTable() {
        isInTable = true;
    }

    public void setArrangement(int arrangement) {
        if (arrangement < 0 || arrangement > 1)
            throw new IllegalArgumentException("arrangement must be 0 (forwards) or 1 (backwards)");
        this.arrangement = arrangement;
    }

    public void changeFoundStatus() {
        found = !found;
    }

    public void changeArrangement() {
        if (arrangement == 0) arrangement = 1;
        else arrangement = 0;
    }

    public void changeOrientation() {
        if (possibleOrientations.size() < 1) {
            possibleOrientations.remove(Integer.valueOf(orientation));
            orientation = possibleOrientations.get(rand.nextInt(possibleOrientations.size()));
        }
    }
}
