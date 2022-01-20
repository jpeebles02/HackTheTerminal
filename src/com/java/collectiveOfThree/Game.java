package com.java.collectiveOfThree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Game {

    //instance methods
    private LEVEL level;
    private List<String> words;
    private String actualPassword;
    private int livesLeft;
    private HashMap<Character, Integer> passwordFreqMap;


    //constructors
    public Game() {
        this(LEVEL.LEVEL_1, 5);
    }
    public Game(int lives) {
        this(LEVEL.LEVEL_1, lives);
    }

    public Game(LEVEL level, int lives) {
        this.level = level;
        this.livesLeft = lives;
        this.words = new ArrayList<>();

        loadLevel();
        //loadLevel = read the file and load the words ArrayList with the words (1 will be the password)
        choosePassword();
        this.passwordFreqMap = computeFreqMap(this.actualPassword);
    }

        //first thing someone does in the game is? play
        //user provides index
        // -2 : incorrect input. Could be game is dead or incorrect index
        // -1: if the chosen index is the same as chosen password
        //any non-negative number: number of characters matched
        public int play(int index) {

            if (!isAlive() || index < 0 || index >= this.words.size()) {
                return -2;
            }

            int similarity = computeSimilarity(this.words.get(index));
            // if the words are the same, you guessed correct and won
            if (similarity == -1) {
                return -1;
            }

            //if the guess was incorrect, lives will be deducted and the similarity int is returned to Main()
            this.livesLeft--;
            return similarity;
        }

        //checks if there are any lives left
        public boolean isAlive() {
            return this.livesLeft > 0;
        }

        public int getLivesLeft() {
        return livesLeft;
    }

        public List<String> getWords() {
        return words;
    }

    //if password is being revealed, we set the password to 0, thats cheating
        public String revealPassword() {
        this.livesLeft = 0;
        return this.actualPassword;
    }

        //finds number of same letters between guess and actual password Strings
        //if so -1 will be sent back tp the play method
        private int computeSimilarity(String guess) {
            if (this.actualPassword.equals(guess)) {
                return -1;
            }
            //if actual password is not equal to guess ...
            //freqMap = letters and count of letters in word
            HashMap<Character, Integer> guessFreqMap = computeFreqMap(guess);
            // aaabbbccd -> { 'a': 3, 'b':3, 'c':2, 'd':1}

            int count = 0;
            //start at 0 and iterate through all of the keys in the keyset <Character> to calculate their frequency
            for(char c: guessFreqMap.keySet()) {
                // calculate whatever characters are in the guess word,
                int freqInGuessedWord = guessFreqMap.get(c);
                //posible that whatever letters that the guess word contains, might not appear in passwordFreqMap
                //getOrDefault = if you dont find c, give me a default value of 0
                int freqInActualWord = this.passwordFreqMap.getOrDefault(c, 0);
                //take the minimum of the two integers. If guess has 3 a's and actual had 4 a's, count should be 3
                //if freqInActualWord is equal to 0, count should be 0
                count += Math.min(freqInGuessedWord, freqInActualWord);
        }

        return count;
        }

    private HashMap<Character, Integer> computeFreqMap(String word) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        // aabca -> a : 3

        //pass a word into the map, and iterate through every character in the word
        for(int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            //it assigns each character in the word to the map with a key
            //if a character is found more than once the key value is increased by 1
            if (!freqMap.containsKey(letter)) {
                freqMap.put(letter, 1);
            } else {
                freqMap.put(letter, 1 + freqMap.get(letter));
            }
        }

        return freqMap;
    }

    private void choosePassword(){
        //create random number from the size of the words ArrayList or 0 - (size-1)
        int index = new Random().nextInt(words.size());
        //stores the word thats at that random index into the actualPassword String
        this.actualPassword = this.words.get(index);
    }

    private void loadLevel(){
        //get filename
        String filename = "com/java/resources/" + this.level.getFilename();
        //use class loader to get the resource
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        try {
            File file = new File(resource.toURI());
            //read file line by line
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            //at each line we read whats on the line and store the word into the words ArrayList
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //strictly for testing
    public String getActualPasswordForTest(){
        return this.actualPassword;
    }

}


