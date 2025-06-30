// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int head;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int tail;
    /* Array for storing the buffer data. */
    private final T[] items;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        head = 0;
        tail = 1;
        this.capacity = capacity;
        fillCount = 0;
        items = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        items[tail++] = x;
        if (capacity == tail) {
            tail = 0;
        }
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        head = (head + 1) % capacity;
        T rtn = items[head];
        items[head] = null;
        fillCount--;
        return rtn;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return items[(head + 1) % capacity];
    }

    private class ArraryRingBufferIterator implements Iterator<T> {
        private int pointer;
        public ArraryRingBufferIterator() {
            pointer = (head + 1) % capacity;
        }
        @Override
        public boolean hasNext() {
            return pointer != tail;
        }
        @Override
        public T next() {
            T rtn = items[pointer];
            pointer = (pointer + 1) % capacity;
            return rtn;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArraryRingBufferIterator();
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
