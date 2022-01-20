package com.java.collectiveOfThree;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the hacking console game!");
        System.out.println("Input number of lives: ");
        //user input for lives and level
        int lives = Integer.parseInt(in.nextLine());
        int level = getGameLevel(in);

        //create game
        //genEnumFrom method added below to reduce logic up here. very messy
        Game game = new Game(LEVEL.getEnumFromNum(level), lives);
        //get words for game
        List<String> words = game.getWords();

        //set won to false. needs to establish boolean
        boolean won = false;

        while(game.isAlive()) {
            System.out.println("You have " + game.getLivesLeft() + " lives left!");
            System.out.println("Presented are potential passwords. Enter the corresponding index");
            printWords(words);

            //use user input for guessedIndex
            int guessedIndex = Integer.parseInt(in.nextLine());
            if (guessedIndex < 0 || guessedIndex >= words.size()) {
                System.out.println("Incorrect index, please try again!");
            } else {
                //if index is valid, you use that index to play the game
                int similarity = game.play(guessedIndex);

                if (similarity == -2) {
                    System.out.println("Incorrect index, please try again!");
                } else if (similarity == -1) {
                    won = true;
                    break;
                } else {
                    //returns how many letters are the same
                    System.out.println("Good attempt! " + similarity + " letters are same! Better luck next time");
                }
            }
        }

        if (won) {
        System.out.println("Hurray! You won!");
    } else {
        System.out.println("There are not lives left, better hacking next time");
        System.out.println("The correct word is: " + game.revealPassword());
    }
}

    //formatting for printing words to the console
    //necessary because strings are immutable. Just because they are concatenated, they are not really together
    private static void printWords(List<String> words) {
        StringBuilder sb = new StringBuilder("");

        for(int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            sb.append(" : ");
            sb.append(i);

            if (i != words.size() - 1) {
                sb.append(" , ");
            }
        }

        System.out.println(sb);
        //toString is automatically called
    }

    //just a check on if the game level that was input is correct
    private static int getGameLevel(Scanner in) {
        int level = 0;
        while(true) {
            System.out.println("Input desired level. Available levels are: 1, 2, 3, 4, 5");
            level = Integer.parseInt(in.nextLine());
            //if level is not in this range
            if (level < 0 || level > 5) {
                System.out.println("Oops! Incorrect level chosen, try again!");
            } else {
                return level;
            }
        }
    }
}
