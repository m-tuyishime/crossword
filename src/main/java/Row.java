import java.util.Random;

public class Row {
    private Column[] content;
    private int index;
    private int size;

    public Row(int index, int size) {
        if (index < 0) 
            throw new IllegalArgumentException("index must be positive");
        if (size <= 0)
            throw new IllegalArgumentException("size must be greater than 0");
        this.index = index;
        this.size = size;
        content = new Column[size];
    }

    protected int getIndex() {
        return index;
    }

    protected int getSize() {
        return size;
    }

    protected Column getContent(int i) {
        return content[i];
    }

    protected void setContent(int i, Column content) {
        this.content[i] = content;
    }

    public void populateRandomly(Word word, int randomColumn) {
        int space = 0;
        int matchingWords = 0;
        int spaceAfterMatch = 0;
        boolean arrangementSet = false;
        // loops over columns starting from randomColumn
        for (int i = randomColumn; i < getSize(); i++) {
            if (word.isInTable()) break;
            Column column = getContent(i);
            // checks for valid space to insert word
            if (space + matchingWords < word.getWordLength()) {
                boolean ascendingWordMatch = column.getIsFree(word.getWordChar(spaceAfterMatch + matchingWords));
                boolean descendingWordMatch = column.getIsFree(word.getWordChar(word.getWordLength()-1 - (spaceAfterMatch + matchingWords)));

                if (column.getContent() == Character.MIN_VALUE) {
                    space++;
                    if (matchingWords > 0) spaceAfterMatch++;
                } else if ((ascendingWordMatch && !arrangementSet) || (ascendingWordMatch && word.getArrangement() == 0)) {
                    if (!descendingWordMatch) {
                        word.setArrangement(0);
                        arrangementSet = true;
                    }
                    matchingWords++;
                } else if ((descendingWordMatch && !arrangementSet) || (descendingWordMatch && word.getArrangement() == 1)) {
                    if (!ascendingWordMatch) {
                        word.setArrangement(1);
                        arrangementSet = true;
                    }
                    matchingWords++;
                } else {
                    space = 0;
                    matchingWords = 0;
                    spaceAfterMatch = 0;
                }
            // inserts word over valid space depending on arragement
            } else {
                if (word.getArrangement() == 0) {
                    int a = word.getWordLength() - 1;
                    for (int o = i; o > i - word.getWordLength(); o--) {
                        column = getContent(o);
                        column.setContent(word, a);
                        a--;
                    }
                } else {
                    int a = 0;
                    for (int o = i; o > i - word.getWordLength(); o--) {
                        column = getContent(o);
                        column.setContent(word, a);
                        a++;
                    }
                }
                word.setInTable();
            }
        
        }
    }

    public void populate(Word word) {
        Random rand = new Random();
        int randomColumn = rand.nextInt(getSize());
        populateRandomly(word, randomColumn);
        populateRandomly(word, 0);
        if (!word.isInTable()) word.changeOrientation();
    }

    public void fillTheRest() {
        for (int o = 0; o < getSize(); o++) {
           Column column = getContent(o);
           column.setContent(null, 0);
        }
    }

}
