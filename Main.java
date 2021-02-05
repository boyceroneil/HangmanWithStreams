import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//will need to filter the arraylist for the correct letter
public class Main {
    static Path filePath = Paths.get("textFiles\\Player");
    static Path filePath2 = Paths.get("textFiles\\Scores");
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
        String blank = word.replaceAll("[a-z]", "_");
        word = word.replaceAll("[a-z]", "_");

        System.out.println("What's your name? ");
        Scanner inn = new Scanner(System.in);
        String user = inn.nextLine();
        int score =0;
        int counter =6;
        int correct = word.length();

        while(counter >0){
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
//                counter--;
//                score-=5;
//            }
            if(answer.get(0).contains(guess)){

            word = answer.stream().filter(s-> s.contains(guess)).collect(Collectors.joining());
            String p = guess;
            word.replaceFirst("-",p);
            System.out.println(word);
            System.out.println("You've got " +correct +" left to find");
                score+=10;
                correct-=1;
                if(correct==0){
                    System.out.println(user+ " win and your score is "+ score);
                    break;
                }
            }
            else{
                counter--;
                System.out.println("You've got " + counter + " tries left and "+ correct +" letters to find");
                score-=5;
            }

        }
        if (counter == 0) {
            System.out.println("You reached the limit. "+ counter+ " and the word is " + list.get(rando));
        }
        Files.writeString(filePath,"\n"+user  +" "+score,StandardOpenOption.APPEND);
        Files.writeString(filePath2,"\n"+score,StandardOpenOption.APPEND);

        //Files.write(Paths.get("textFiles\\Player"), user+" "+ score, utf8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
       // writeToAFile(user,score);
        if(findHighest(score)){
            System.out.println(user+" has the highest score of "+score);
        }
    }

    public static boolean findHighest(int score){//need to work on this
        List<String> check = new ArrayList<>();
        boolean result = false;
        String buffer="";
        try{
            Scanner file = new Scanner(new File("textFiles\\Scores"));
            while(file.hasNextInt()) {
                int number = file.nextInt();

                if(number > score) {
                    result = false;
                }
                else{
                    result = true;
                }
            }
            //check =readLines("textFiles\\Player");
        file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        Stream<String> stream = Stream.of(check.toString());
        //stream.anyMatch(s->s.contains(score));

        return result;
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