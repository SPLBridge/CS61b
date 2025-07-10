import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        // generate instances for testing

        // test 1 base case
        char c11 = 'a';
        char c12 = 'e';
        boolean gotten1 = offByOne.equalChars(c11, c12);
        boolean expected1 = false;

        // test 2 base case
        char c21 = 'a';
        char c22 = 'b';
        boolean gotten2 = offByOne.equalChars(c21, c22);
        boolean expected2 = true;

        // test 3 other chars
        char c31 = '&';
        char c32 = '%';
        boolean gotten3 = offByOne.equalChars(c31, c32);
        boolean expected3 = true;

        // test 4 other chars
        char c41 = '$';
        char c42 = '&';
        boolean gotten4 = offByOne.equalChars(c41, c42);
        boolean expected4 = false;

        // test 5 upper case
        char c51 = 'A';
        char c52 = 'b';
        boolean gotten5 = offByOne.equalChars(c51, c52);
        boolean expected5 = false;

        // test 6 upper case
        char c61 = 'A';
        char c62 = 'B';
        boolean gotten6 = offByOne.equalChars(c61, c62);
        boolean expected6 = true;

        // perform tests
        assertEquals(expected1, gotten1);
        assertEquals(expected2, gotten2);
        assertEquals(expected3, gotten3);
        assertEquals(expected4, gotten4);
        assertEquals(expected5, gotten5);
        assertEquals(expected6, gotten6);
    }
}
