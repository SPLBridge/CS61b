package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

public class GameTest {
    private static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final long SEED = 256; // modify to do different tests
    public static void main(String[] args) {
        // test for setPoints

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);


        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // set a rule
        for (int i = 0; i < WIDTH; i++) {
            TETile T = new TETile((char) ('0' + i % 10), new Color(128, 216, 140),
                    Color.darkGray, "wall");
            world[i][0] = T;;
        }
        for (int i = 0; i < HEIGHT; i++) {
            TETile T = new TETile((char) ('0' + i % 10), new Color(128, 216, 140),
                    Color.darkGray, "wall");
            world[0][i] = T;;
        }

        // create a random position list
        PositionList L = new PositionList();
        Random R = new Random(SEED);
        for (int i = 0; i < 15; i++) {
            int x = R.nextInt(0, 90);
            int y = R.nextInt(0, 40);
            Position P = new Position(x, y);
            L.add(P);
            System.out.println("point " + i + " (" + x + ", " + y + ")");
        }

        // set positions
        TETile T = Tileset.FLOOR;
        Game.setPositions(world, L, T);

        // draws the world to the screen
        ter.renderFrame(world);

        // test for generateRooms()
        PositionListSet roomSet = Game.generateRooms();
        PositionList roomList = roomSet.merge();

        // test for generateHalls()
        PositionListSet hallSet = Game.generateHalls(roomSet);
        PositionList hallList = hallSet.merge();

        // clear the world
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // set positions
        Game.setPositions(world, roomList, T);
        Game.setPositions(world, hallList, T);

        Game.generateWalls(world);

        // show the world
        ter.renderFrame(world);
    }
}
