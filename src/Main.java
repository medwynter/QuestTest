import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * Main call out to get result on list of files.
 * Created by ritho on 9/25/15.
 */
public class Main {



    public static void main(String[] args)  {

        //List of all the files with words.
        List<String> files = new ArrayList<String>();
        files.add("words1.txt");
        files.add("words2.txt");
        files.add("words3.txt");
        files.add("words4.txt");
        //files.add("words5.txt");//TEST VARIABLE THAT WILL NEED TO BE DELETED

        Scanner console = new Scanner(System.in);
        //Get a number to see word(s) that  has N letter's.
        int number;
        System.out.print("Please enter size of words to find words of that size: ");
        while (!console.hasNextInt()) {
            System.out.println("That's not a number! Try Again!");
            console.next();
        }
        number = console.nextInt();

        //Get a char to returns word(s) with the most uses of that Char
        char c;
        System.out.print("Please enter a letter to find word(s) with it repeated the most: ");
        c = console.next().charAt(0);

        //Call to create list and display output.
        Stats fr;
        fr = new Stats(files);
        fr.result();
        fr.countOfNletterWrd(number);
        fr.calRepeat(c);

        //Random word selector.
        Random rand = new Random();
        ArrayList<String> wrds = new ArrayList<>(fr.getWrdList().keySet());
        System.out.println("A random word : " + wrds.get(rand.nextInt(wrds.size())));
    }


}



