import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Word {
    private String word;
    private int orientation;
    private int position;
    private ArrayList<Integer> positions = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
    private boolean found;

    private Random rand = new Random();


    public Word (String word) {
        this.word = word;
        orientation = rand.nextInt(2);
        position = positions.get(rand.nextInt(positions.size()));
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

    public int getOrientation() {
        return orientation;
    }

    public int getPosition() {
        return position;
    }

    public boolean getFoundStatus() {
        return found;
    }

    public void setFoundStatus(boolean found) {
        this.found = found;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void changeOrientation() {
        if (orientation == 0) orientation = 1;
        else orientation = 0;
    }

    public int changePosition() {
        if (positions.size() < 1) {
            positions.remove(Integer.valueOf(position));
            position = positions.get(rand.nextInt(positions.size()));
            return position;
        } else {
            return -1;
        }
    }
}
