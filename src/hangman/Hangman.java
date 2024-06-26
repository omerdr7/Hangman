package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {

    static Scanner s = new Scanner(System.in);
    static ArrayList<String> wordList = new ArrayList<>();
    static String newWord;

    public static void mainMenu() {
        System.out.println("Welcome to hangman game.Enjoyyy!");
        System.out.println("Please select a number ");

        System.out.print("1-" + "add a word\n");
        System.out.print("2-" + "new game\n");
        System.out.print("3-" + "show scores\n");
        System.out.print("4-" + "exit\n");
        System.out.println("Select a number:");
        int chose = s.nextInt();
        if (chose > 0 && chose < 5) {
            switch (chose) {
                case 1:
                    addWord();
                    break;
                default:

            }
        } else {
            System.out.println("Please select a valid number.");
            mainMenu();
        }
    }

    public static void addWord() {
        System.out.println("Enter the word which you want to add to game:");
        newWord = s.next();
        boolean control = true;
        if (newWord.length() <= 3) {
            System.out.println("This word is too short.You should give more than 3 letter.");
            //this if check is word more than 3 letter.
            control = false;
        }
        if (newWord.length() > 3) {
            for (String b : wordList) {
                if (newWord.equalsIgnoreCase(b)) {
                    System.out.println("You cannot add a word two time.");
                    //this if check is word added before.
                    control = false;
                }

            }
        }
        if (newWord.length() > 3) {
            for (char c : newWord.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.out.println("You cannot enter a word which is include digit.");
                    //this if check is word include digit.
                    control = false;
                }
            }
        }
        if (!control) {
            System.out.println("Word cannot add.");
        } else {
            System.out.println("Word is added.");
            wordList.add(newWord);
        }
        //This case(1) check all conditions for example is word include digit.
        for (String string : wordList) {
            System.out.println(string);
        }
        mainMenu();

    }

    public static void main(String[] args) {
        mainMenu();
    }

}
