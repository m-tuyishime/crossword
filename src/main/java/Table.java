public class Table {
    private static int tableWidth = 15;
    private static int tableHeigth = tableWidth;
    private int numOfWords = 35;
    private Word[] wordsToFind = new Word[numOfWords];
    private HorizontalRow[] horizontalRows = new HorizontalRow[tableHeigth];
    private VerticalRow[] verticalRows = new VerticalRow[tableWidth];
    private DiagonalRow[] leftDiagonalRows = new DiagonalRow[tableHeigth * 2 - 1];
    private DiagonalRow[] rightDiagonalRows = new DiagonalRow[tableHeigth];

    public Table() {
        for (int i = 0; i < tableHeigth; i++) {
            horizontalRows[i] = new HorizontalRow(i, tableWidth);
            verticalRows[i] = new VerticalRow(i, tableHeigth);

        }
        int doubleHeight = tableHeigth * 2;
        for (int i = 0; i < doubleHeight - 1; i++) {
            int size = -Math.abs(i - (doubleHeight - 2) / 2) + tableHeigth;
            leftDiagonalRows[i] = new DiagonalRow(i, (int) size, 1);
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
            wordsToFind[i] = word;
        }
    }

    private void populate() {
        int count = 0;
        for (Word word : wordsToFind) {
            if (word.isInTable())
                continue;
            switch (word.getOrientation()) {
                // case 0:
                // for (HorizontalRow row : horizontalRows) {
                // row.populate(word);
                // }
                // case 1:
                // for (VerticalRow row : verticalRows) {
                // row.populate(word);
                // }
                case 2:
                    for (DiagonalRow row : leftDiagonalRows) {
                        row.populate(word);
                    }
            }
            if (!word.isInTable())
                count++;
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
