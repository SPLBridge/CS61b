import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test(timeout = 1000)
    public void testIsPalindrome() {
        // generate instances for testing

        // test 1 base case
        String word1 = "love61b";
        boolean gotten1 = palindrome.isPalindrome(word1);
        boolean expected1 = false;

        // test 2 base case
        String word2 = "racecar";
        boolean gotten2 = palindrome.isPalindrome(word2);
        boolean expected2 = true;

        // test 3 one letter
        String word3 = "a";
        boolean gotten3 = palindrome.isPalindrome(word3);
        boolean expected3 = true;

        // test 4 empty
        String word4 = "";
        boolean gotten4 = palindrome.isPalindrome(word4);
        boolean expected4 = true;

        // perform tests
        assertEquals(expected1, gotten1);
        assertEquals(expected2, gotten2);
        assertEquals(expected3, gotten3);
        assertEquals(expected4, gotten4);
    }

    @Test(timeout = 1000)
    public void testIsPalindrome2() {
        // generate instances for testing

        // instantiate a comparator
        OffByOne cc = new OffByOne();

        // test 1 base case
        String word1 = "a%&b";
        boolean gotten1 = palindrome.isPalindrome(word1, cc);
        boolean expected1 = true;

        // test 2 base case
        String word2 = "abba";
        boolean gotten2 = palindrome.isPalindrome(word2, cc);
        boolean expected2 = false;

        // test 3 one char
        String word3 = "a";
        boolean gotten3 = palindrome.isPalindrome(word3, cc);
        boolean expected3 = true;

        // test 4 empty
        String word4 = "";
        boolean gotten4 = palindrome.isPalindrome(word4, cc);
        boolean expected4 = true;

        // perform tests
        assertEquals(expected1, gotten1);
        assertEquals(expected2, gotten2);
        assertEquals(expected3, gotten3);
        assertEquals(expected4, gotten4);
    }
}
