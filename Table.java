import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table {
    private Random rand = new Random();

    private int tableWidth = 20;
    private int tableHeigth = 15;
    private int numOfWords = 35;
    private char[][] area = new char[tableHeigth][tableWidth];
    private ArrayList<Word> wordsToFind = new ArrayList<Word>();
    
    public Table () {
        generatewordsToFind();
        populatewordsToFind();
    }

    private void generatewordsToFind() {
        String[] defWords = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "iceberg", "jicama", "kiwi", "lemon", "mango", "nectarine", "orange", "peach", "quince", "raspberry", "strawberry", "tangerine", "ugli", "vanilla", "watermelon", "xigua", "yellow", "zucchini", "apricot", "blueberry", "cantaloupe", "dragonfruit", "eucalyptus", "figs", "grapefruit", "honeyberry", "jackfruit", "kumquat", "lime", "mulberry", "nectarine", "olive", "persimmon", "quince", "raspberry", "strawberry", "tamarillo"};
        
        for (int i = 0; i < numOfWords; i++) {
            Word word = new Word(defWords[i]);
            wordsToFind.add(word);
        }
    }

    private void populateVertically() {
        // looop over all the words to find
        for (int x = 0; x < wordsToFind.size(); x++) {
            Word word = wordsToFind.get(x);
            Boolean done = false;
            //creates a list of random column indexes
            List<Integer> columnIndexes = IntStream.rangeClosed(0, area[0].length -1)
                .boxed()
                .collect(Collectors.toList());
            Collections.shuffle(columnIndexes);

            // loops randomly over columns
            for (int randomColumn : columnIndexes) {
                // picks a random row to start at
                int randomRow = rand.nextInt(area.length);
                // checks if there's enough room and the space is not taken by another word
                boolean enoughRoom = area.length - randomRow >= word.getWordLength();
                boolean notTaken = true;
                for (int i = randomRow; i < area.length; i++) {
                    if (area[i][randomColumn] != Character.MIN_VALUE) {
                        notTaken = false;
                        break;
                    }
                }
                // add word characters in the table depending on orientation (frontward/backwards)
                if (enoughRoom && notTaken) {
                    if (word.getOrientation() == 0) {
                        int a = 0;
                        for (int i = randomRow; i < randomRow + word.getWordLength(); i++) {
                            area[i][randomColumn] = word.getWordChars()[a];
                            a++;
                        }
                    } else {
                        int a = word.getWordLength() - 1;
                        for (int i = randomRow; i < randomRow + word.getWordLength(); i++) {
                            area[i][randomColumn] = word.getWordChars()[a];
                            a--;
                        }
                    }
                    done = true;
                    break;
                }
            }
            
            if (done) continue;

            // looks for leftover space and space that can be shared
            // loops randomly over columns
            for (int randomColumn : columnIndexes) {
                int space = 0;
                int matchingWords = 0;
                int spaceAfterMatch = 0;
                boolean orientationSet = false;
                // loops over rows
                for (int i = 0; i < area.length; i++) {
                    // checks for valid space to insert word
                    if (space + matchingWords + spaceAfterMatch < word.getWordLength()) {
                        boolean ascendingWordMatch = area[i][randomColumn] == word.getWordChars()[spaceAfterMatch + matchingWords];
                        boolean descendingWordMatch = area[i][randomColumn] == word.getWordChars()[word.getWordLength()-1 - (spaceAfterMatch + matchingWords)];

                        if (area[i][randomColumn] == Character.MIN_VALUE) {
                            space++;
                            if (matchingWords > 0) spaceAfterMatch++;
                        } else if ((ascendingWordMatch && !orientationSet) || (ascendingWordMatch && word.getOrientation() == 0)) {
                            if (!descendingWordMatch) {
                                word.setOrientation(0);
                                orientationSet = true;
                            }
                            matchingWords++;
                        } else if ((descendingWordMatch && !orientationSet) || (descendingWordMatch && word.getOrientation() == 1)) {
                            if (!ascendingWordMatch) {
                                word.setOrientation(1);
                                orientationSet = true;
                            }
                            matchingWords++;
                        } else {
                            space = 0;
                            matchingWords = 0;
                            spaceAfterMatch = 0;
                        }
                    // inserts word over valid space depending on orientation
                    } else {
                        if (word.getOrientation() == 0) {
                            int a = word.getWordLength() - 1;
                            for (int o = i; o > i - word.getWordLength(); o--) {
                                area[o][randomColumn] = word.getWordChars()[a];
                                a--;
                            }
                        } else {
                            int a = 0;
                            for (int o = i; o > i - word.getWordLength(); o--) {
                                area[o][randomColumn] = word.getWordChars()[a];
                                a++;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private void populateHorizontally() {
        // looop over all the words to find
        for (int x = 0; x < wordsToFind.size(); x++) {
            Word word = wordsToFind.get(x);
            Boolean done = false;
            //creates a list of random row indexes
            List<Integer> rowIndexes = IntStream.rangeClosed(0, area.length -1)
                .boxed()
                .collect(Collectors.toList());
            Collections.shuffle(rowIndexes);

            // loops randomly over rows
            for (int randomRow : rowIndexes) {
                // picks a random column to start at
                int randomColumn = rand.nextInt(area[randomRow].length);
                // checks if there's enough room and the space is not taken by another word
                boolean enoughRoom = area[randomRow].length - randomColumn >= word.getWordLength();
                boolean notTaken = true;
                for (int i = randomColumn; i < area[randomRow].length; i++) {
                    if (area[randomRow][i] != Character.MIN_VALUE) {
                        notTaken = false;
                        break;
                    }
                }
                // add word characters in the table depending on orientation (frontward/backwards)
                if (enoughRoom && notTaken) {
                    if (word.getOrientation() == 0) {
                        int a = 0;
                        for (int i = randomColumn; i < randomColumn + word.getWordLength(); i++) {
                            area[randomRow][i] = word.getWordChars()[a];
                            a++;
                        }
                    } else {
                        int a = word.getWordLength() - 1;
                        for (int i = randomColumn; i < randomColumn + word.getWordLength(); i++) {
                            area[randomRow][i] = word.getWordChars()[a];
                            a--;
                        }
                    }
                    done = true;
                    break;
                }
            }
            
            if (done) continue;

            // looks for leftover space and space that can be shared
            // loops randomly over rows
            for (int randomRow : rowIndexes) {
                int space = 0;
                int matchingWords = 0;
                int spaceAfterMatch = 0;
                boolean orientationSet = false;
                // loops over columns
                for (int i = 0; i < area[randomRow].length; i++) {
                    // checks for valid space to insert word
                    if (space + matchingWords < word.getWordLength()) {
                        boolean ascendingWordMatch = area[randomRow][i] == word.getWordChars()[spaceAfterMatch + matchingWords];
                        boolean descendingWordMatch = area[randomRow][i] == word.getWordChars()[word.getWordLength()-1 - (spaceAfterMatch + matchingWords)];

                        if (area[randomRow][i] == Character.MIN_VALUE) {
                            space++;
                            if (matchingWords > 0) spaceAfterMatch++;
                        } else if ((ascendingWordMatch && !orientationSet) || (ascendingWordMatch && word.getOrientation() == 0)) {
                            if (!descendingWordMatch) {
                                word.setOrientation(0);
                                orientationSet = true;
                            }
                            matchingWords++;
                        } else if ((descendingWordMatch && !orientationSet) || (descendingWordMatch && word.getOrientation() == 1)) {
                            if (!ascendingWordMatch) {
                                word.setOrientation(1);
                                orientationSet = true;
                            }
                            matchingWords++;
                        } else {
                            space = 0;
                            matchingWords = 0;
                            spaceAfterMatch = 0;
                        }
                    // inserts word over valid space depending on orientation
                    } else {
                        if (word.getOrientation() == 0) {
                            int a = word.getWordLength() - 1;
                            for (int o = i; o > i - word.getWordLength(); o--) {
                                area[randomRow][o] = word.getWordChars()[a];
                                a--;
                            }
                        } else {
                            int a = 0;
                            for (int o = i; o > i - word.getWordLength(); o--) {
                                area[randomRow][o] = word.getWordChars()[a];
                                a++;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private void populatewordsToFind() {
        populateVertically();
    }

    public void printTable() {
        System.out.println("-------------------------START-------------------------");
        for (int o = 0; o < area.length; o++) {
            System.out.print("\n| ");
            for (int i = 0; i < area[o].length; i++) {
                System.out.print(area[o][i]);
                System.out.print("  ");
            }
            System.out.print("|\n");
        }
        System.out.println("-------------------------END---------------------------");
    }
}
