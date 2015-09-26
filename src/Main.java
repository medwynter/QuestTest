import java.util.ArrayList;
import java.util.List;
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



        //Call to create list and display output.

        Stats fr;
        fr = new Stats(files);
        fr.reuslt();

        Scanner console = new Scanner(System.in);
        int number;

        System.out.print("\nPlease enter size of words: ");
        while (!console.hasNextInt()) {
            System.out.println("That's not a number!");
            console.next();
        }
        number = console.nextInt();
        fr.countOfNletterWrd(number);
        char c;
        System.out.print("\nPlease enter letter to find word with it repeated the most: ");
        while (!console.hasNext()) {
            System.out.println("That's not a Letter!");
            console.next();
        }
        c = console.next().charAt(0);
        fr.repeat(c);


    }


}



