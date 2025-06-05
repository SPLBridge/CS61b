import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    private int testCount = 100;
    private int upperBound = 32767;
    private int lowerBound = -32767;
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> studentArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> array = new ArrayDequeSolution<>();
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < testCount; i++) {
            int testType = StdRandom.uniform(2); // 1 for add, 0 for remove
            int location = StdRandom.uniform(2); // 1 for first, 0 for last
            int nestTestCounts = StdRandom.uniform(testCount);

            if (testType == 1) {
                if (location == 1) {
                    for (int j = 0; j < nestTestCounts; j++) {
                        int addInt = StdRandom.uniform(lowerBound, upperBound);
                        studentArray.addFirst(addInt);
                        array.addFirst(addInt);
                        msg.append("addFirst(" + addInt + ")\n");
                    }
                } else {
                    for (int j = 0; j < nestTestCounts; j++) {
                        int addInt = StdRandom.uniform(lowerBound, upperBound);
                        studentArray.addLast(addInt);
                        array.addLast(addInt);
                        msg.append("addLast(" + addInt + ")\n");
                    }
                }
            } else {
                if (location == 1) {
                    for (int j = 0; j < nestTestCounts; j++) {
                        Integer studentInt = studentArray.removeFirst();
                        Integer arrayInt = array.removeFirst();
                        msg.append("removeFirst(): " + studentInt + "\n");
                        assertEquals(msg.toString(), arrayInt, studentInt);
                    }
                } else {
                    for (int j = 0; j < nestTestCounts; j++) {
                        Integer studentInt = studentArray.removeLast();
                        Integer arrayInt = array.removeLast();
                        msg.append("removeLast(): " + studentInt + "\n");
                        assertEquals(msg.toString(), arrayInt, studentInt);
                    }
                }
            }
        }
    }
}
