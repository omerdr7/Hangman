package hangman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hangman {

    static Scanner s = new Scanner(System.in);
    static ArrayList<String> wordList = new ArrayList<>();
    static ArrayList<Character> knownLetter = new ArrayList<>();
    static String newWord;
    static String selectedWord;
    static int userRights;
    static int userPoints;
    static ArrayList<Integer> scores = new ArrayList<>();

    public static String getMaskedWord(String word, ArrayList<Character> knownLetter) {
        //it is return the hidden word with updates.
        String result = "";
        for (int i = 0; i < word.length(); i++) {

            Character currentChar = word.charAt(i);
            //it is take currentchar of word.
            if (knownLetter.contains(currentChar)) {
                result = result.concat(currentChar.toString());

            } else {
                result = result.concat("_ ");
            }
        }//it is create the word if selected word contains current char with _'s and current char.
        return result;
    }

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
                case 2:
                    game();
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Game is closing.");
                    System.exit(0);
                    break;
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
        mainMenu();

    }

    public static void game() {
        System.out.println("Game is starting.");
        Collections.shuffle(wordList);//this is shuffle all word in words arraylist.
        selectedWord = wordList.get(0);//this take first member of words arraylist. 
        userRights = selectedWord.length() / 2;
        userPoints = 0;
        knownLetter.clear();
        System.out.println("Selected word: " + selectedWord.replaceAll(".", "_ "));
        //this show the gamer selected word but with _'s.
        System.out.println("You have " + userRights + " rights.");
        String maskedWord = null;
        while (userRights > 0) {

            maskedWord = getMaskedWord(selectedWord, knownLetter);
            //it is call getMaskedWord method.
//                        System.out.println("Selected word: " + selectedWord.replaceAll(".", "_ "));
            System.out.println("Select one char: ");
            String guess1 = s.next();
            //it takes input from user to guess the hidden word.
            knownLetter.add(guess1.charAt(0));
            //it takes character which user enter in known letter arraylist.

            if (selectedWord.contains(guess1)) {
                for (int i = 0; i < selectedWord.length(); i++) {

                    maskedWord = getMaskedWord(selectedWord, knownLetter);

                    if (maskedWord.equals(selectedWord)) {
                        //if user know the word true it finish the loop. 
                        break;
                    }
                }
            }
            System.out.println(maskedWord);
            //it show the updated hidden word every guess.
            if (!maskedWord.contains("_")) {
                userPoints += 10;
                System.out.println("You won.");
                System.out.println("Your point is= " + userPoints);
                //if the guess is true gamer takes 10 points and loop finish.
                break;
            } else if (!selectedWord.contains(guess1)) {
                --userRights;
                //if guess is not true gamer lose 1 right.
                System.out.println("There is not " + guess1);
                System.out.println("You have " + userRights + " rights.");
                if (userRights == 0) {
                    System.out.println("You do not have any rights so you lost the game.");
                }

            }
        }
        mainMenu();
    }

    public static void main(String[] args) {
        mainMenu();
    }

}
