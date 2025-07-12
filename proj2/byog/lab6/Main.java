package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;



public class Main {
    public static void main(String[] args) {
        MemoryGame game = new MemoryGame(40, 40, 5115);
        game.startGame();
        game.drawFrame("233");
    }
}
