package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        // Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        // Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int idx = rand.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[idx]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(width / 2.0, height / 2.0, s);
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.textLeft(1, height - 1, "Round: " + round);
        StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[3]);
        StdDraw.text(width / 2.0, height - 1, "Watch!");

        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        char[] lettersArray = letters.toCharArray();
        for (char letter : lettersArray) {
            String letterString = String.valueOf(letter);

            // Each character should be visible on the screen for 1 second
            // there should be a brief 0.5 second break between characters where the screen is blank.
            drawFrame(letterString);
            StdDraw.show();
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n;) {
            if (StdDraw.hasNextKeyTyped()) {
                char letter = StdDraw.nextKeyTyped();
                sb.append(letter);

                i++;
            }
        }
        drawFrame(sb.toString());
        StdDraw.pause(1000);
        return sb.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        String string;
        String inputString; 
        do {
            drawFrame("Round: " + round);
            string = generateRandomString(round);
            StdDraw.pause(1500);
            flashSequence(string);
            inputString = solicitNCharsInput(round);
            round++;
        } while (string.equals(inputString));
        string = "Game Over! You made it to round:" + (round - 1);
        drawFrame(string);

        //TODO: Establish Game loop
    }

}
