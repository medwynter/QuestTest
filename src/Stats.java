import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Created by ritho on 9/25/15.
 */
public class Stats {
    /**
     * A count of all the words.
     */
    private int totalWordsCount = 0;
    /**
     * List of longest words.
     */
    private List<String> longestWord = new ArrayList<String>();
    /**
     * List of  unique words by use of chars
     */
    private List<String> uw = new ArrayList<String>();

    /**
     * Key: A length of words
     * Value: Num of words that it's length equals the key
     */
    private Map<Integer, Integer> lensOfWords = new TreeMap<Integer, Integer>();

    /**
     * Key: A sorted list of the words.
     * Value: The Frequency of chars in Key.
     */
    private Map<String, CharCounter> freqOfChar = new TreeMap<String, CharCounter>();


    /**
     * Constructor for multiple files.
     *
     * @param file - List of all the files that will be parsed.
     */
    public Stats(List<String> file) {

        for (String i : file) {
            wrdListMaker(i);
        }

    }

    /**
     * Constructor for a single file.
     * @param file - The file that will be parsed.
     */
    public Stats(String file) {
        wrdListMaker(file);

    }


    /**
     * Parses through the file that is provided to create the word list.
     * then sorts the word list.
     *
     * @param file - The file that will be parsed to create the word list.
     */
    private void wrdListMaker(String file) {
        BufferedReader br;
        // The name of the file to open.
        // filename = files;

        try {

            // Reads text files.
            br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                totalWordsCount++;
                //Do some additional calls now while i'm at on each word.
                //int temp = Math.max(longest,str.length());

                makeWordMap(str.length());
                //Takes the word and counts the occurrence of letter that make up the word.
                CharCounter cc = new CharCounter(str);
                freqOfChar.put(str, cc);


            }
            br.close(); // Close reader.

        } catch (IOException ex) {
            System.out.println("Failed to open file");
        }

    }

    /**
     * The results for word list.
     */


    public void result() {
        System.out.println("---------------------------------------------");
        Thread t2 = new Thread() {
            public void run() {

                calUniqueWrd();
            }
        };

        Thread t3 = new Thread() {
            public void run() {

                calLongestWrd();
            }
        };

        t2.start();
        t3.start();


        try {
            System.out.println("Total number words parsed: " + totalWordsCount);
            System.out.println("First word alphabetically: " + getWrdList().keySet().toArray()[0]);
            t2.join();//To wait to make sure unique list is done before printing result.
            System.out.println("Word with most unique letters: " + uw.toString().replaceAll("[\\[\\]]", ""));
            t3.join();
            System.out.println("Longest word: " + getLongestWrd());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * @return The list of words.
     */
    public Map<String, CharCounter> getWrdList() {
        return freqOfChar;
    }

    /**
     * @return The longest word in the List.
     */
    private String getLongestWrd() {
        return longestWord.toString().replaceAll("[\\[\\]]", ""); // Replace all from stackover flow.
    }

    /**
     * Keeps track of words lengths.
     *
     * @param x - A length of a word to update/add
     */
    private void makeWordMap(int x) {
        if (lensOfWords.containsKey(x)) {
            lensOfWords.put(x, lensOfWords.get(x) + 1);
        } else {
            lensOfWords.put(x, 1);
        }

    }

    /**
     * Calculates the longest word.
     *
     */
    private void calLongestWrd() {
        int longest = Integer.MIN_VALUE;//Temp for longest word count

        for (String str : freqOfChar.keySet()) {
            int longW = freqOfChar.get(str).calNumOfLetters();

            if (longest <= longW) {
                if (longest < longW) {
                    longest = longW;
                    longestWord.clear();
                }

                longestWord.add(str);
            }
        }
    }

    /**
     * Calculates the most Unique words.
     */
    private void calUniqueWrd() {

        //Unique means being the only one of its kind -oxford dictionary.
        int mosttemp = Integer.MIN_VALUE;
        int ctemp;

        //for all the words get it's char count map.
        for (String cc : freqOfChar.keySet()) {
            ctemp = 0;//reset counter;
            Map<Character, Integer> cCM = freqOfChar.get(cc).getCountedMap();
            ///ccc = Map<Character, Integer> counter

            //then add up  the number of chars that only appear once.
            for (char c : cCM.keySet()) {
                if (cCM.get(c) == 1) {
                    ctemp++;
                }
            }

            //If the current words has more  unique char it is the new unique word
            if (mosttemp <= ctemp) {
                //More than one word can use the same amount of unique letters
                if (mosttemp < ctemp) {
                    mosttemp = ctemp;
                    uw.clear();
                }

                uw.add(cc);

            }
        }


    }


    /**
     * @param n The word length wanted.
     */
    public void countOfNletterWrd(int n) {

        if (!lensOfWords.containsKey(n)) {

            System.out.println("\nThere are no words of size " + n + ".");
        } else {
            System.out.println("\nThere are " + lensOfWords.get(n) + " words with " + n + " letters. ");
        }


    }

    /**
     * Take a a character and Create a list of word(s) that use the letter the most.
     *
     * @param co - The letter to show what word(s) repeats it the most.
     */
    public void calRepeat(char co) {
        List<String> mostUsage = new ArrayList<String>();//The list of word with CO repeated the most.
        int highestUsage = Integer.MIN_VALUE; // Temp value for the highest usage of  CO
        if (Character.isLetter(co)) {//Check if the char passed is a letter

            //Go through all the words and its CharCounter for  CO
            for (String mySTR : freqOfChar.keySet()) {
                CharCounter my = freqOfChar.get(mySTR);

                //Go through the CharCounter and CO if it exist.
                //Check if it's a higher usage in this word.
                if (my.getCountedMap().containsKey(co)) {
                    int temp = my.getCountedMap().get(co);
                    if (highestUsage <= temp) {//If its the same usage in this word add it most repeated list
                        if (highestUsage < temp) {//If it's less usage clear Usage list and update highest usage count;
                            mostUsage.clear();
                            highestUsage = temp;
                        }
                        mostUsage.add(mySTR);// Add the word usage list.
                    }
                }

            }
            System.out.println("Here is a list of word(s) that repeat '" + Character.toUpperCase(co) + "' the most. " +
                    "\n  \t" + mostUsage.toString().replaceAll("[\\[\\]]", ""));
        } else {
            System.out.println(co + " is not a letter.");
        }
    }
}
