import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Stream;


//will need to filter the arraylist for the correct letter
public class Main {
    static Path filePath = Paths.get("textFiles\\Player");
    public static void main(String[] args) throws IOException {
        Charset utf8 =StandardCharsets.UTF_8;
        List<String> list = new ArrayList<>();
        List<String> answer = new ArrayList<>();
        try{
            list =readLines("textFiles\\WordList.txt.");
        }catch(IOException e){
            e.printStackTrace();
        }


        Random obj = new Random();
        int rando = obj.nextInt(15);
        answer.add(list.get(rando));

        String word =list.get(rando);
        word = word.replaceAll("[a-z]", "_");
        String blank = word;


        System.out.println("What's your name? ");
        Scanner inn = new Scanner(System.in);
        String user = inn.nextLine();
        int score =0;
        int counter =0;
        int correct = word.length();

        while(counter <6){
            Stream<String> stream = Stream.of(list.get(rando));
            Scanner input = new Scanner(System.in);
            System.out.println("guess the letter" + "\n "+ blank);
            String guess = input.nextLine(); //guess the letter
//            if(stream.anyMatch(s->s.contains(guess))){//list.stream().filter(s -> s.contains(guess.chars()))
//                System.out.println(list.stream().filter(s->s.contains(guess)));//should print out the areas that has the guess
//                score+=10;
//                correct-=1;//length of the word you're guessing
//                if(correct==0){
//                    System.out.println(user+ " win and your score is "+ score);
//                    break;
//                }
//            }
//            else{
//                counter++;
//                score-=5;
//            }
            if(list.get(0).contains(guess)){
            word = list.stream().filter(s-> s.contains(guess)).toString();
            System.out.println(word);
                score+=10;
                correct-=1;
                if(correct==0){
                    System.out.println(user+ " win and your score is "+ score);
                    break;
                }
            }
            else{
                counter++;
                score-=5;
            }

        }
        if (counter == 6) {
            System.out.println("You reached the limit. "+ counter+ " and the word is " + list.get(rando));
        }
        Files.writeString(filePath,"\n"+user+" "+score,StandardOpenOption.APPEND);
        //Files.write(Paths.get("textFiles\\Player"), user+" "+ score, utf8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
       // writeToAFile(user,score);
        if(findHighest(score)){
            System.out.println(user+" "+score+" is the highest");
        }
    }

    public static boolean findHighest(int score){//need to work on this
        List<String> check = new ArrayList<>();
        try{
            check =readLines("textFiles\\Player");
        }catch(IOException e){
            e.printStackTrace();
        }
        Stream<String> stream = Stream.of(check.toString());
        //stream.anyMatch(s->s.contains(score));

        return false;
    }
    public static List<String> readLines(String filename) throws IOException{
        FileReader fileReader = new FileReader(filename);
        BufferedReader buffer = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line= null;
        while((line = buffer.readLine()) != null){
            lines.add(line);
        }
        buffer.close();
        return lines;
    }


}
//String[] word = {"dragonball", "gundam", "rugrats", "avatar", "jimmyneutron"};
//        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(WordBank.class)
//                .buildSessionFactory();
//
//        //create a session must be needed to save to the database or else it won't work
//        Session session = factory.getCurrentSession();
//        System.out.println("Do you want to add a word to the wordbank? 1 for yes and 2 for no");
//        try {
//            Scanner inn = new Scanner(System.in);
//            int j = inn.nextInt();
//            while (j == 1) {
//
//                System.out.print("What word do you want to add");
//                worder = inn.nextLine();
//                WordBank wb = new WordBank(worder);
//                session.beginTransaction();
//                System.out.println("Beginning transaction...");
//
//                session.save(wb);
//                System.out.println("Saving the new word...");
//
//                System.out.println("Do you still want to add words? 1 for yes and 2 for no");
//                j = inn.nextInt();
//
//
//            }
//        } finally{
//            factory.close();
//        }
//        int j = inn.nextInt();
//        if(j ==1){
//           // Hangman02 hg = Hangman02();
//        }