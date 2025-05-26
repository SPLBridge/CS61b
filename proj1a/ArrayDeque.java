public class ArrayDeque<T> {
    /** point to the next position to set */
    private int head, tail;

    private T[] items;

    /** initializes an empty ArrayDeque */
    public ArrayDeque() {
        head = 0;
        tail = 1;
        items = (T[]) new Object[8];
    }

    /** Returns the number of items in the deque */
    public int size() {
        if (tail > head) {
            return tail - head - 1;
        } else {
            return tail + items.length - head - 1;
        }
    }

    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns true if deque is full, false otherwise */
    private boolean isFull() {
        return size() == items.length - 1;
    }

    /** double items.length */
    private void increase() {
        int length = items.length;
        T[] newItems = (T[]) new Object[2 * length];
        int size = size();
        if (tail > head) {
            System.arraycopy(items, head + 1, newItems, 1, size);
        } else {
            System.arraycopy(items, head + 1, newItems, 1, length - head - 1);
            System.arraycopy(items, 0, newItems, length - head, tail);
        }
        tail = size + 1;
        head = 0;
        items = newItems;
    }

    /** Adds an item of T to the front of the deque */
    public void addFirst(T item) {
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

    /** Adds an item of T to the back of the deque */
    public void addLast(T item) {
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
        } else {
            if (0 == tail) {
                int i = items.length - 1;
                if (i > head) {
                    System.out.print(items[i]);
                    i--;
                }
                for (; i > head; i--) {
                    System.out.print(" " + items[i]);
                }
            } else {
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
    public T removeFirst() {
        // checks in case that the ArrayDeque is empty
        if (isEmpty()) {
            return null;
        }

        tail = (tail - 1 + items.length) % items.length;
        T rtn = items[tail];
        items[tail] = null;
        return rtn;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null
     *  the position will be set to null
     */
    public T removeLast() {
        // checks in case that the ArrayDeque is empty
        if (isEmpty()) {
            return null;
        }

        head = (head + 1) % items.length;
        T rtn = items[head];
        items[head] = null;
        return rtn;
    }

    /** Gets the item at the given index
     *  If no such item exists, returns null
     *  Must not alter the deque
     */
    public T get(int index) {
        // check in case that no such item exists
        if (size() <= index) {
            return null;
        }

        index = (tail - index - 1 + items.length) % items.length;
        return items[index];
    }
}
