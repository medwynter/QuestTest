import java.util.Map;
import java.util.TreeMap;

/**
 * @author Created by ritho on 9/25/15.
 */
public class CharCounter {
    /**
     * A word.
     */
    public final String wrd;

    /**
     * Key: A letter in the word.
     * Value: The frequency of a key.
     */
    private Map<Character, Integer> counter = new TreeMap<Character, Integer>();


    /**
     * Constructor to make char counted list.
     *
     * @param wrd - The word to count its character.
     */
    public CharCounter(String wrd) {
        this.wrd = wrd;
        mapChars();
    }

    /**
     * Makes a map of the letters in word and frequency of those letters.
     */
    private void mapChars(){

        for(int i = 0; i <wrd.length(); i++){
            char c = wrd.charAt(i);
            if (Character.isLetter(c)) { //Only keep track of letters
                if (!counter.containsKey(c)) {
                    counter.put(c, 1);
                } else {
                    counter.put(c, counter.get(c) + 1);
                }
            }
        }
    }


    /**
     * Key: A letter from the word.
     * Value: Frequency of the character.
     * @return Map of the letters in word and frequency of those letters
     */
    public Map<Character,Integer> getCountedMap(){

        return counter;
    }

    /**
     *
     * @return The length of word minus punctuation marks/signs
     */
    public int calNumOfLetters() {
        int temp = 0;
        for (int i : counter.values()) {
            temp += i;
        }

        return temp;
    }
}
