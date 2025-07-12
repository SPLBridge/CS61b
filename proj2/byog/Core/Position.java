package byog.Core;

/** represents a position in a TETile
 *  consists of 2 ints representing x&y coordinate separately
 *  has 2 methods that returns x&y separately
 */
public class Position {
    private final int x;
    private final int y;
    public Position(int xPosition, int yPosition) {
        x = xPosition;
        y = yPosition;
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }

    /** returns if this is at the same position with another position
     *  for checking if 2 lists of positions overlap
     */
    boolean equals(Position that) {
        assert that != null;
        return x == that.x() && y == that.y();
    }
}
