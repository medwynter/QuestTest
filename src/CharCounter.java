import java.util.Map;
import java.util.TreeMap;

/**
 * @author Created by ritho on 9/25/15.
 */
public class CharCounter {

    public final String wrd;
    //public List<Character> usedChars = new ArrayList<Character>();
    private Map<Character, Integer> counter = new TreeMap<Character, Integer>();



        public CharCounter(String wrd){
        this.wrd = wrd;
        mapChars();
    }


    private void mapChars(){

        for(int i = 0; i <wrd.length(); i++){
            char c = wrd.charAt(i);
            if (!counter.containsKey(c) && Character.isLetter(c)) {
                counter.put(c, 1);

                for (int j = i + 1; j < wrd.length(); j++) {
                    if (wrd.charAt(j) == c)
                        counter.put(c, counter.get(c) + 1);
                }
            }
        }
    }



    public Map<Character,Integer> getCountedMap(){
        return counter;
    }

    public int calNumOfLetters() {
        int temp = 0;
        for (int i : counter.values()) {
            temp += i;
        }

        return temp;
    }
}
