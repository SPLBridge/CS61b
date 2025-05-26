public class ArrayDeque<type> {
    /** point to the next position to set */
    private int head, tail;

    private type[] items;

    /** initializes an empty ArrayDeque */
    public ArrayDeque() {
        tail = 0;
        head = 1;
        items = (type[]) new Object[8];
    }

    /** Returns the number of items in the deque */
    public int size() {
        if (head > tail) {
            return head - tail - 1;
        }
        else {
            return head + items.length - tail - 1;
        }
    }

    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns true if deque is full, false otherwise */
    public boolean isFull() {
        return size() == items.length - 1;
    }

    /** double items.length */
    private void increase() {
        int length = items.length;
        type[] new_items = (type[]) new Object[2 * length];
        int size = size();
        if (head > tail) {
            System.arraycopy(items, tail + 1, new_items, 1, size);
        }
        else {
            System.arraycopy(items, tail + 1, new_items, 1, length - tail - 1);
            System.arraycopy(items, 0, new_items, length - tail, head);
        }
        head = size + 1;
        tail = 0;
        items = new_items;
    }

    /** Adds an item of type T to the front of the deque */
    public void addFirst(type item) {
        // checks in case that the Array is full
        if (isFull()) {
            increase();
        }

        items[tail++] = item;

        // checks in case that tail is out of index
        if (items.length == tail) {
            tail = 0;
        }
    }

    /** Adds an item of type T to the back of the deque */
    public void addLast(type item) {
        // checks in case that the Array is full
        if (isFull()) {
            increase();
        }

        items[head--] = item;

        // checks in case that tail is out of index
        if (-1 == head) {
            head = items.length - 1;
        }
    }

    /** Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        if (head < tail) {
            int i = tail - 1;
            if (i > head) {
                System.out.print(items[i]);
                i--;
            }
            for (; i > head; i--) {
                System.out.print(" " + items[i]);
            }
        }
        else {
            if (0 == tail) {
                int i = items.length - 1;
                if (i > head) {
                    System.out.print(items[i]);
                    i--;
                }
                for (; i > head; i--) {
                    System.out.print(" " + items[i]);
                }
            }
            else {
                int i = tail - 1;
                if (i >= 0) {
                    System.out.print(items[i]);
                    i--;
                }
                for (; i >= 0; i--) {
                    System.out.print(" " + items[i]);
                }
                for (i = items.length - 1; i > head; i--) {
                    System.out.print(" " + items[i]);
                }
            }
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null
     *  the position will be set to null
     */
    public type removeFirst() {
        // checks in case that the ArrayDeque is empty
        if (isEmpty()) {
            return null;
        }

        tail = (tail - 1 + items.length) % items.length;
        type rtn = items[tail];
        items[tail] = null;
        return rtn;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null
     *  the position will be set to null
     */
    public type removeLast() {
        // checks in case that the ArrayDeque is empty
        if (isEmpty()) {
            return null;
        }

        head = (head + 1) % items.length;
        type rtn = items[head];
        items[head] = null;
        return rtn;
    }

    /** Gets the item at the given index
     *  If no such item exists, returns null
     *  Must not alter the deque
     */
    public type get(int index) {
        // check in case that no such item exists
        if (size() - 1 < index) {
            return null;
        }

        index = (tail - index - 1 + items.length) % items.length;
        return items[index];
    }
}
