import java.util.Random;

public class Table {
    private static int tableWidth = 15;
    private static int tableHeigth = tableWidth;
    private int numOfWords = 35;
    private Word[] wordBank = new Word[numOfWords];
    private Word[] wordsToFind;
    private HorizontalRow[] horizontalRows = new HorizontalRow[tableHeigth];
    private VerticalRow[] verticalRows = new VerticalRow[tableWidth];
    private DiagonalRow[] leftDiagonalRows = new DiagonalRow[tableHeigth * 2 - 1];
    private DiagonalRow[] rightDiagonalRows = new DiagonalRow[tableHeigth * 2 - 1];

    public Table() {
        for (int i = 0; i < tableHeigth; i++) {
            horizontalRows[i] = new HorizontalRow(i, tableWidth);
            verticalRows[i] = new VerticalRow(i, tableHeigth);

        }
        int doubleHeight = tableHeigth * 2;
        for (int i = 0; i < doubleHeight - 1; i++) {
            int size = -Math.abs(i - (doubleHeight - 2) / 2) + tableHeigth;
            leftDiagonalRows[i] = new DiagonalRow(i, size, 0);
            rightDiagonalRows[i] = new DiagonalRow(i, size, 1);
        }
        generatewordsToFind();
        populate();
    }

    public static int getTableWidth() {
        return tableWidth;
    }

    public static int getTableHeigth() {
        return tableHeigth;
    }

    private void generatewordsToFind() {
        String[] defWords = { "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "iceberg",
                "jicama", "kiwi", "lemon", "mango", "nectarine", "orange", "peach", "quince", "raspberry", "strawberry",
                "tangerine", "ugli", "vanilla", "watermelon", "xigua", "yellow", "zucchini", "apricot", "blueberry",
                "cantaloupe", "dragonfruit", "eucalyptus", "figs", "grapefruit", "honeyberry", "jackfruit", "kumquat",
                "lime", "mulberry", "nectarine", "olive", "persimmon", "quince", "raspberry", "strawberry",
                "tamarillo" };

        for (int i = 0; i < numOfWords; i++) {
            Word word = new Word(defWords[i]);
            wordBank[i] = word;
        }
    }

    private void populate() {
        int count = 0;
        for (Word word : wordBank) {
            if (word.isInTable())
                continue;
            for (int i = 0; i < 3; i++) {
                switch (word.getOrientation()) {
                    case 0:
                        populateRandomly(horizontalRows, word);
                        break;
                    case 1:
                        populateRandomly(verticalRows, word);
                        break;
                    case 2:
                        populateRandomly(leftDiagonalRows, word);
                        break;
                    case 3:
                        populateRandomly(rightDiagonalRows, word);
                }
                if (!word.isInTable())
                    break;
            }
            if (!word.isInTable())
                count++;
        }
        wordsToFind = new Word[count];
        int i = 0;
        for (Word word : wordBank) {
            if (!word.isInTable()) {
                wordsToFind[i] = word;
                i++;
            }
        }
        for (HorizontalRow row : horizontalRows) {
            row.fillTheRest();
        }
    }

    public void populateRandomly(Row[] rows, Word word) {
        Random rand = new Random();
        int[] rowsIndexes = new int[rows.length];
        for (int i = 0; i < rand.nextInt(100); i++) {
            int index = rand.nextInt(rows.length);
            for (int o = rand.nextInt(rows.length); o < rows.length; o += rand.nextInt(rows.length)) {
                rowsIndexes[index] = o;
            }
        }
        for (int i : rowsIndexes) {
            rows[i].populate(word);
        }
    }

    public void printTable() {
        System.out.println("-----------------------------START-----------------------------");
        for (HorizontalRow row : horizontalRows) {
            row.print();
        }
        System.out.println("-----------------------------END-------------------------------");
    }

    public void printWordsToFind() {
        System.out.print("Words to find: ");
        for (Word word : wordsToFind) {
            if (word.equals(wordsToFind[wordsToFind.length - 1])) {
                System.out.printf("%s.", word.getWord());
            } else
                System.out.printf("%s, ", word.getWord());
        }
        System.out.println();
    }
}
