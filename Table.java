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
        
    }

    private void populateHorizontally() {
        // looop over all the words to find
        for (int x = 0; x < wordsToFind.size(); x++) {
            boolean done = false;
            Word word = wordsToFind.get(x);

            //creates a list of random column indexes
            List<Integer> rowIndexes = IntStream.rangeClosed(0, area.length -1)
                .boxed()
                .collect(Collectors.toList());
            Collections.shuffle(rowIndexes);

            int numbOfTries = 0;
            while (!done) {
                for (int randomRow : rowIndexes) {
                    int randomColumn = rand.nextInt(area[randomRow].length);
                    boolean enoughRoom = area[randomRow].length - randomColumn - 1 >= word.getWordLength();
                    boolean notTaken = true;
                    for (int i = randomColumn; i < area[randomRow].length; i++) {
                        if (area[randomRow][i] != Character.MIN_VALUE) {
                            notTaken = false;
                            break;
                        }
                    }
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


                // shared space
                for (int randomRow : rowIndexes) {
                    int space = 0;
                    int matchingWords = 0;
                    boolean orientationSet = false;

                    if (done) break;

                    for (int i = 0; i < area[randomRow].length; i++) {

                        if (space + matchingWords < word.getWordLength()) {
                            boolean ascendingWordMatch = area[randomRow][i] == word.getWordChars()[space + matchingWords];
                            boolean descendingWordMatch = area[randomRow][i] == word.getWordChars()[word.getWordLength()-1 - (space + matchingWords)];

                            if (area[randomRow][i] == Character.MIN_VALUE) {
                                space++;
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
                            }
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
                            done = true;
                            break;
                        }
                    }
                }
                word.changeOrientation();
                numbOfTries++;
                if (numbOfTries > 1) {
                    System.out.println("I GIVE UP!");
                    done = true;
                    break;
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
