package byog.Core;

import java.util.Iterator;

/** a list of positions
 *  allows adding new positions and iterating
 */
public class PositionList implements Iterable<Position> {
    private ArrayDeque<Position> positions;
    public PositionList() {
        positions = new ArrayDeque<>();
    }

    /** returns if 2 lists overlap */
    static boolean overlap(PositionList list1, PositionList list2) {
        for (int i = 0; i < list1.positions.size(); i++) {
            for (int j = 0; j < list2.positions.size(); j++) {
                Position position1 = list1.positions.get(i);
                Position position2 = list2.positions.get(j);
                if (position1.equals(position2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** adds a new position into the list */
    void add(Position p) {
        positions.addLast(p);
    }

    private class iterator implements Iterator<Position> {
        int pointer;
        public iterator() {
            pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return pointer < positions.size();
        }

        @Override
        public Position next() {
            return positions.get(pointer++);
        }
    }

    @Override
    public Iterator<Position> iterator() {
        return new iterator();
    }
}
