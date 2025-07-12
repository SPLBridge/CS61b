package byog.Core;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PositionListTest {
    @Test
    public void test() {
        Position position1 = new Position(256, 3225);
        Position position2 = new Position(4778, 2592);
        PositionList positionList = new PositionList();
        positionList.add(position1);
        positionList.add(position2);

        assertEquals(256, position1.x());
        assertEquals(3225, position1.y());
        assertEquals(4778, position2.x());
        assertEquals(2592, position2.y());
        for (Position P : positionList) {
            System.out.println(P.x() + " " + P.y());
        }
    }
}
