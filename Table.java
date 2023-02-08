public class Table {
    private static String[] wordBank = new String[35];
    private static char[][] area = new char[15][20];
    
    public Table () {
        generateWordBank();
    }

    private static void generateWordBank() {
        String[] defWords = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "iceberg", "jicama", "kiwi", "lemon", "mango", "nectarine", "orange", "peach", "quince", "raspberry", "strawberry", "tangerine", "ugli", "vanilla", "watermelon", "xigua", "yellow", "zucchini", "apricot", "blueberry", "cantaloupe", "dragonfruit", "eucalyptus", "figs"};
        for (int i = 0 ; i < wordBank.length; i++) {
            wordBank[i] = defWords[i];
        }
    }

    public static void printTable () {
        for (int o = 0; o < area.length; o++) {
            System.out.print("\n| ");
            for (int i = 0; i < area[o].length; i++) {
                System.out.print(area[o][i]);
                System.out.print("  ");
            }
            System.out.print("|\n");
        }
    }
}
