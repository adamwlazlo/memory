import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Word {
    public List<String> wordsArray;

    public Word() {
        wordsArray = new ArrayList<>();
        ReadFile();

    }

    public void ReadFile()
    {
        try {
            wordsArray = new ArrayList<>();
            String path = System.getProperty("user.dir") + "\\src\\";
            File myObj = new File(path + "Words.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                assert false;
                this.wordsArray.add(myReader.nextLine());
            }
            System.out.println(this.wordsArray);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> DrawWords(int number_of_words)
    {
        ArrayList<String> arr_drawn_words = new ArrayList<>();

        for(int i = 0; i < number_of_words; i++)
        {
            Random rand = new Random();
            int randNum = rand.nextInt(0, wordsArray.size());

            while (arr_drawn_words.contains(wordsArray.get(randNum)))
            {
                randNum = rand.nextInt(0, wordsArray.size());

            }

            arr_drawn_words.add(wordsArray.get(randNum));

        }
        return arr_drawn_words;
    }

}
