package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.util.Objects.isNull;


public class App {
    public static void main(String[] args) {
        HangmanTest.run();
    }
}


class HangmanTest  {

    private static void switchChoice (int choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1 -> HangmanUtil.setWord(HangmanApp.words, scanner.nextLine());
            case 2 -> { System.out.print("Enter word length: ");
                HangmanApp.words.add(HangmanUtil.createRandomWords(Integer.parseInt(scanner.nextLine())));
                game(scanner);
            }
            case 3-> game(scanner);
            case 4->System.out.println(HangmanApp.scores);
            default -> System.out.println("Invalid choice");
        }
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        HangmanUtil.getMenuOne();
        switchChoice(Integer.parseInt(scanner.nextLine()));
    }

    public static void game(Scanner scanner) {
        HangmanApp hangmanApp = new HangmanApp();
        boolean isRunning = true;
        if(isNull(HangmanApp.words) || HangmanApp.words.isEmpty()) {
            while(isRunning) {
                System.out.println("You have not entered a word.");
                HangmanUtil.getMenuTwo();
                int choice = Integer.parseInt(scanner.nextLine());
                if(choice == 1 || choice == 2 || choice == 5) {
                    switchChoice(choice);
                }
                System.out.println("You have entered a choice.");
            }
        }
        String word = HangmanUtil.selectRandomWord(HangmanApp.words);
        String maskedWord = HangmanUtil.maskWord(word.toCharArray(),'_');
        while (maskedWord.contains("_")) {
            System.out.println(maskedWord);
            System.out.println(":");
            Character s = scanner.nextLine().charAt(0);
            int score ;
            if((score = HangmanUtil.checkCharacter(word, s)) > 0) {
                maskedWord = HangmanUtil.notMaskedCharacter(word,maskedWord.toCharArray(),s);
                HangmanUtil.updateScore(hangmanApp.scores, score);
            }
            else{
                System.out.println("You have entered an invalid word.");
            }
        }

    }
}

class HangmanApp {
    static List<String> words = new ArrayList<>();
    static int scores;
}



class HangmanUtil {

    public final static String TR_CHARACTER ="ABCÇDEFGĞHİIJKLMNOÖPRSŞTUÜVYZ";
    public final static String TR_CHARACTER_LOWER ="abcçdefgğhiıjklmnoöprsştuüvyz";

   public static void setWord(List<String> words, String word) {
        words.add(word);
   }

   public static void deleteWord(List<String> words, String word) {
        words.remove(word);
   }

   public static char[] maskCharacter(char[] word, Character c, int index) {
             word[index] = c;
        return word;
   }
    public static String maskWord(char[] word, Character c) {
       int index =  word.length;
       while(index-- > 0)
           word[index] = c;
       return word.toString();
    }


   public static int checkCharacter(String word, Character c) {
        return word.contains(c.toString()) ? 10 : 0 ;
   }

   public static int getIndexCharacter(String word, Character c) {
        return word.indexOf(c.toString());
   }

   public static int updateScore(int scores, int score) {
       return scores + score;
   }

   public static String wordTRToLower(String word) {
        for (Character c : word.toCharArray())
            if(TR_CHARACTER.contains(c.toString()))
                word.replace(c, TR_CHARACTER_LOWER.charAt(TR_CHARACTER.indexOf(c)));
        return word;
   }

   public static String createRandomWords(int countToCharacter) {
        String word = "";
        for(int i = 0; i < countToCharacter; i++)
            word = word + TR_CHARACTER_LOWER.charAt(new Random().nextInt(countToCharacter));
        return word;
   }

   public static String selectRandomWord(List<String> words) {
       return words.get(new Random().nextInt(words.size()));
   }

    public static void getMenuOne ()  {
        System.out.print("Welcome to hangman game.Enjoyyy! \n" +
                "1- add a word \n" +
                "2- add a random word \n" +
                "3- new game \n" +
                "4- show scores \n" +
                "5- exit \n"+
                "Enter your choice: ");
    }
    public static void getMenuTwo ()  {
        System.out.print(
                "1- add a word \n" +
                "2- add a random word \n" +
                "5- exit \n"+
                "Enter your choice: ");
    }

    public static String notMaskedCharacter(String word, char[] maskedWord, Character c) {
       int index = getIndexCharacter(word,c);
       return maskCharacter(maskedWord,c, index).toString();
    }


}

