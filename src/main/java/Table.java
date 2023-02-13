import java.util.ArrayList;
import java.util.Random;

public class Table {
    private int tableWidth = 20;
    private int tableHeigth = 15;
    private int numOfWords = 35;
    private ArrayList<Word> wordsToFind = new ArrayList<Word>();
    private HorizontalRow[] horizontalRows = new HorizontalRow[tableHeigth];
    private VerticalRow[] verticalRows = new VerticalRow[tableWidth];
    
    public Table() {
        for (int i = 0; i < tableHeigth; i++) {
            horizontalRows[i] = new HorizontalRow(i, tableWidth);
        }
        for (int i = 0; i < tableWidth; i++) {
            verticalRows[i] = new VerticalRow(i, tableHeigth);
        }
        generatewordsToFind();
        populate();
    }

    private void generatewordsToFind() {
        String[] defWords = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "iceberg", "jicama", "kiwi", "lemon", "mango", "nectarine", "orange", "peach", "quince", "raspberry", "strawberry", "tangerine", "ugli", "vanilla", "watermelon", "xigua", "yellow", "zucchini", "apricot", "blueberry", "cantaloupe", "dragonfruit", "eucalyptus", "figs", "grapefruit", "honeyberry", "jackfruit", "kumquat", "lime", "mulberry", "nectarine", "olive", "persimmon", "quince", "raspberry", "strawberry", "tamarillo"};
        
        for (int i = 0; i < numOfWords; i++) {
            Word word = new Word(defWords[i]);
            wordsToFind.add(word);
        }
    } 

    private void populate() {
        for (Word word : wordsToFind) {
            if (word.isInTable()) continue;
            switch(word.getOrientation()) {
                case 0:
                    for (HorizontalRow row : horizontalRows) {
                        row.populate(word);
                    }
                case 1:
                    for (VerticalRow row : verticalRows) {
                        row.populate(word);
                    }
            }
        }
        for (HorizontalRow row : horizontalRows) {
            row.fillTheRest();
        }
    }

    public void printTable() {
        System.out.println("-----------------------------START-----------------------------");
        for (HorizontalRow row : horizontalRows) {
            row.print();
        }
        System.out.println("-----------------------------END-------------------------------");
    }
}
