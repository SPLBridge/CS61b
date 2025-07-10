package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static long SEED = 0; // mutable after testing
    private static Random RANDOM;
    private static final int BOUND = 7;
    private static final int ACCOUNT = 20; // maybe to be changed

    /** take in a 2D TETile array, a list of positions and a tile
     *  set every position to the tile
     *  should be private after testing
     *  if a position is out of the world, it is supposed to be ignored
     */
    static void setPositions(
            TETile[][] world, PositionList positionList, TETile tile) {
        for (Position P : positionList) {
            int x = P.x();
            int y = P.y();
            // ignore positions outside the world
            if (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT) {
                world[x][y] = tile;
            }
        }
    }

    /** returns a rectangle (by now) of positions in a list
     *  that does not take the place of edges of the world
     *  should be private after testing
     */
    private static PositionList generateRoom() {
        // new a position list
        PositionList room = new PositionList();
        // randomly generate 2 positive int (inside the world of course) as startX and startY (left-lower)
        int x = RANDOM.nextInt(1, WIDTH - 1 - 1); // 1 for the index case
        int y = RANDOM.nextInt(1, HEIGHT - 1 - 1); // 1 for the wall case
        // randomly generate 2 int as the length and width, check if the upper-right position is out of the world
        // the lengths and widths remain to be tested
        // maybe in the range of 1 to 6, to which 2 private static final variables should be set
        int width;
        do {
            width = RANDOM.nextInt(1, BOUND);
        } while (width + x > WIDTH - 1 - 1);
        int height;
        do {
            height = RANDOM.nextInt(1, BOUND);
        } while (height + y > HEIGHT - 1 - 1);
        // for i in startX, width:
        //     for j in startY, length:
        //         position = (i, j)
        //         positionList.add(position)
        // return positionList
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                Position P = new Position(i, j);
                room.add(P);
            }
        }
        return room;
    }

    /** returns a set of rooms
     *  should be private after testing
     */
    static PositionListSet generateRooms() {
        PositionListSet rooms = new PositionListSet();
        for (int i = 0; i < ACCOUNT;) {
            PositionList room = generateRoom();
            boolean continueFlag = false;
            for (PositionList P : rooms) {
                if (PositionList.overlap(room, P)) {
                    continueFlag = true;
                    break;
                }
            }
            if (!continueFlag) {
                rooms.add(room);
                i++;
            } // else: continue
        }
        return rooms;
    }

    /** given 2 rooms
     *  return a hall PositionList that links them
     */
    private static PositionList link(PositionList room1, PositionList room2) {
        PositionList hall = new PositionList();
        Position position1 = null;
        while (true) {
            boolean access = false;
            for (Position P : room1) {
                if (RANDOM.nextInt(10) == 0) {
                    position1 = P;
                    access = true;
                    break;
                }
            }
            if (access) {
                break;
            }
        }
        Position position2 = null;
        while (true) {
            boolean access = false;
            for (Position P : room2) {
                if (RANDOM.nextInt(10) == 0) {
                    position2 = P;
                    access = true;
                    break;
                }
            }
            if (access) {
                break;
            }
        }
        int x1 = position1.x();
        int y1 = position1.y();
        int x2 = position2.x();
        int y2 = position2.y();
        if (x1 < x2) {
            for (int x = x1; x <= x2; x++) {
                hall.add(new Position(x, y1));
            }
            if (y1 < y2) {
                for (int y = y1 + 1; y <= y2; y++) {
                    hall.add(new Position(x2, y));
                }
            } else {
                for (int y = y1 - 1; y >= y2; y--) {
                    hall.add(new Position(x2, y));
                }
            }
        } else {
            for (int x = x1; x >= x2; x--) {
                hall.add(new Position(x, y1));
            }
            if (y1 < y2) {
                for (int y = y1 + 1; y <= y2; y++) {
                    hall.add(new Position(x2, y));
                }
            } else {
                for (int y = y1 - 1; y >= y2; y--) {
                    hall.add(new Position(x2, y));
                }
            }
        }
        return hall;
    }

    /** generates a hall linking 2 random rooms
     *  sets corresponding position of visited to true
     */
    private static PositionList randomlyVisitAndLink(PositionList[] roomArray, boolean[] visited) {
        // randomly visit
        int num1 = RANDOM.nextInt(roomArray.length);
        int num2 = RANDOM.nextInt(roomArray.length);
        PositionList room1 = roomArray[num1];
        PositionList room2 = roomArray[num2];
        visited[num1] = true;
        visited[num2] = true;

        // link
        return link(room1, room2);
    }

    /** returns a set of halls
     *  should be private after testing
     */
    static PositionListSet generateHalls(PositionListSet rooms) {
        PositionListSet halls = new PositionListSet();
        PositionList[] roomArray = rooms.toArray();
        boolean[] visited = new boolean[ACCOUNT];
        while (true) {
            boolean breakFlag = true;
            for (boolean b : visited) {
                if (!b) {
                    breakFlag = false;
                    halls.add(randomlyVisitAndLink(roomArray, visited));
                }
            }
            if (breakFlag) {
                break;
            }
        }
        return halls;
    }

    /** add walls to positions beside the given position */
    private static void addWalls(TETile[][] world, int x, int y) {
        TETile nothing = Tileset.NOTHING;
        TETile wall = Tileset.WALL;
        if (world[x - 1][y] == nothing) {
            world[x - 1][y] = wall;
        }
        if (world[x + 1][y] == nothing) {
            world[x + 1][y] = wall;
        }
        if (world[x][y - 1] == nothing) {
            world[x][y - 1] = wall;
        }
        if (world[x][y + 1] == nothing) {
            world[x][y + 1] = wall;
        }
    }

    /** generate the walls that wrap the paths */
    static void generateWalls(TETile[][] world) {
        for (int i = 1; i < WIDTH - 1; i ++) {
            for (int j = 1; j < HEIGHT - 1; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    addWalls(world, i, j);
                }
            }
        }
    }

    /** generate an exit at a random wall */
    static void generateExit(TETile[][] world) {
        while (true) {
            int x = RANDOM.nextInt(WIDTH - 1);
            int y = RANDOM.nextInt(HEIGHT - 1);
            if (world[x][y] == Tileset.WALL) {
                world[x][y] = Tileset.UNLOCKED_DOOR;
                return;
            }
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        boolean stop = false;
        int i = 0;
        TETile[][] world = null;
        while (!stop) {
            char c = Character.toLowerCase(input.charAt(i));
            if (c == 'l' || c == 'n') {
                stop = true;
            }
            i++;
        }
        if (Character.toLowerCase(input.charAt(i - 1)) == 'n') {
            i++;
            while (Character.toLowerCase(input.charAt(i)) != 's') {
                if (Character.isDigit(input.charAt(i))) {
                    SEED = SEED * 10 + (long) (input.charAt(i) - '0');
                }
                i++;
            }
            RANDOM = new Random(SEED);

            TERenderer ter = new TERenderer();
            ter.initialize(WIDTH, HEIGHT);

            world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }

            PositionListSet roomSet = Game.generateRooms();
            PositionList roomList = roomSet.merge();

            PositionListSet hallSet = Game.generateHalls(roomSet);
            PositionList hallList = hallSet.merge();

            Game.setPositions(world, roomList, Tileset.FLOOR);
            Game.setPositions(world, hallList, Tileset.FLOOR);

            Game.generateWalls(world);

            Game.generateExit(world);

            ter.renderFrame(world);
        }
        return world;
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    void playWithKeyboard() {
    }

}
