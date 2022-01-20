import com.java.collectiveOfThree.Game;
import com.java.collectiveOfThree.LEVEL;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    //static variables
    private static final int INCORRECT_INPUT = -2;
    private static final int PASSWORD_MATCH = -1;
    private static final List<String > WORDS = Arrays.asList("a&f-!", "*d#]t", "$-52q", "t&d].", "&d]-2");

    @Test
    public void testDefaultConstructorOne() {
        //this one tests the
        Game game = new Game();
        List<String> actualWords = game.getWords();
        assertEquals(5, game.getLivesLeft());
    }

    @Test
    public void testDefaultConstructorTwo() {
        //this one tests the level
        Game game = new Game();
        List<String> actualWords = game.getWords();
        assertArrayEquals(WORDS.toArray(), actualWords.toArray());
    }

    @Test
    public void testConstructorInputLivesOne() {
        //this one tests the lives
        int lives = 5;
        Game game = new Game(lives);
        List<String> actualWords = game.getWords();
        assertEquals(lives, game.getLivesLeft());
    }
    @Test
    public void testConstructorInputLivesTwo() {
        //this one tests the level
        int lives = 5;
        Game game = new Game(lives);
        List<String> actualWords = game.getWords();
        assertArrayEquals(WORDS.toArray(), actualWords.toArray());
    }

    @Test
    public void testConstructorInputLivesAndLevelOne() {
        int lives = 5;
        Game game = new Game(LEVEL.LEVEL_1, lives);
        List<String> actualWords = game.getWords();
        assertEquals(lives, game.getLivesLeft());
    }

    @Test
    public void testConstructorInputLivesAndLevelTwo() {
        int lives = 5;
        Game game = new Game(LEVEL.LEVEL_1, lives);
        List<String> actualWords = game.getWords();
        assertArrayEquals(WORDS.toArray(), actualWords.toArray());
    }

    @Test
    public void testIsAlive() {
        Game g = new Game(5);
        boolean expected = true;
        boolean actual = g.isAlive();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsAliveFail() {
        Game g = new Game(0);
        boolean expected = false;
        boolean actual = g.isAlive();
        assertEquals(expected, actual);

    }

    @Test
    public void getLivesLeft() {
        Game g = new Game(5);
        int expected = 5;
        int actual = g.getLivesLeft();
        assertEquals(expected, actual);
    }

    @Test
    public void getWords() {
        Game g = new Game();
        List<String> words = g.getWords();
        assertTrue(words.size()> 0);
        assertEquals(this.WORDS, words);
    }

    @Test
    public void testRevealPassword() {
        Game g = new Game(5);
        String actualPass = g.revealPassword();
        int livesLeft = g.getLivesLeft();
        assertEquals(0, livesLeft);
        assertNotNull(actualPass);
    }

    //ADDITONAL TESTS
    @Test
    public void testRevealPasswordMakesGameEnd() {
        int lives = 7;
        Game game = new Game(lives);
        game.revealPassword();
        assertFalse(game.isAlive());
        assertEquals(INCORRECT_INPUT, game.play(0));
    }

    @Test
    public void testGameWhenGuessedRight(){
        int initialLives = 7;
        Game game = new Game(initialLives);
        assertEquals(initialLives, game.getLivesLeft());

        List<String> words = game.getWords();
        String actualPassword = game.getActualPasswordForTest();
        int correctGuessIndex = words.indexOf(actualPassword);

        assertEquals(PASSWORD_MATCH, game.play(correctGuessIndex));
        assertEquals(initialLives, game.getLivesLeft());
        assertTrue(game.isAlive());
    }

    @Test
    public void testGameAfterSingleWrongGuess(){

    }

    @Test
    public void testGameUntilAllLivesLost(){

    }

    @Test
    public void testGameWhenAllLivesLost(){

    }




}