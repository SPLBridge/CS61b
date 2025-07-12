package byog.Core;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionListSetTest {
    @Test
    /** tests multiple methods */
    public void SetTest() {
        Position position1 = new Position(256, 3225);
        Position position2 = new Position(4778, 2592);
        PositionList positionList1 = new PositionList();
        PositionList positionList2 = new PositionList();
        positionList1.add(position1);
        positionList2.add(position2);
        PositionListSet set = new PositionListSet();

        // test 1 contains()
        assertFalse(set.contains(positionList1));
        assertFalse(set.contains(positionList2));

        set.add(positionList1);
        set.add(positionList2);

        // test 2 add() and contains()
        assertTrue(set.contains(positionList1));
        assertTrue(set.contains(positionList2));

        // test 3 iterator() manually
        for (PositionList list : set) {
            for (Position P : list) {
                System.out.println(P.x() + " " + P.y());
            }
        }

        // test 4 merge() manually
        PositionList mergedList = set.merge();
        for (Position P : mergedList) {
            System.out.println(P.x() + " " + P.y());
        }
    }
}
