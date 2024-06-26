package hangman;

import java.util.Scanner;

public class Hangman {

    public static void mainMenu() {
        System.out.print("1-" + "add a word\n");
        System.out.print("2-" + "new game\n");
        System.out.print("3-" + "show scores\n");
        System.out.print("4-" + "exit\n");
        System.out.println("Select a number:");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to hangman game.Enjoyyy!");

        System.out.println("Please select a number ");
        int chose = s.nextInt();
        if (chose > 0 && chose < 5) {
            switch (chose) {
                case 1:

                    break;
                default:

            }
        } else {
            System.out.println("Please select a valid number.");
        }

    }

}
