public class Palindrome {
    /**
     * Given a String, wordToDeque should return a Deque
     * where the characters appear in the same order as in the String
     * if the word is “persiflage”
     * the returned Deque should have ‘p’ at the front, followed by ‘e’
     * and so forth
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            wordDeque.addLast(letter);
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque wordDeque = wordToDeque(word);
        while (!wordDeque.isEmpty()) {
            if (wordDeque.size() == 1) {
                return true;
            }
            Character c1 = (Character) wordDeque.removeFirst();
            Character c2 = (Character) wordDeque.removeLast();
            if (!c1.equals(c2)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque wordDeque = wordToDeque(word);
        while (!wordDeque.isEmpty()) {
            if (wordDeque.size() == 1) {
                return true;
            }
            Character c1 = (Character) wordDeque.removeFirst();
            Character c2 = (Character) wordDeque.removeLast();
            if (!cc.equalChars(c1, c2)) {
                return false;
            }
        }
        return true;
    }
}
