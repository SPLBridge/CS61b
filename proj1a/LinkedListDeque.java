public class LinkedListDeque<type> {
    /** should be modified the instant add or remove happens */
    private int size;
    private LinkedListNode setinal;

    /** implementation of LinkedListNode
     *  a DLList
     */
    private class LinkedListNode {
        type item;
        LinkedListNode last, next;

        /** initializes a LinkedListNode with a given item */
        public LinkedListNode(type item) {
            this.item = item;
        }

        /** initializes a LinkedListNode without a given item */
        public LinkedListNode() {
            // nothing to set
        }

        /** adds node between last and next */
        public void addbetween(LinkedListNode last, LinkedListNode next) {
            this.last = last;
            this.next = next;
            next.last = this;
            last.next = this;
        }

        public type getRecursive(int index) {
            if (0 == index) {
                return item;
            }
            else {
                return next.getRecursive(index - 1);
            }
        }
    }

    /** initializes a LinkedListDeque */
    public LinkedListDeque() {
        size = 0;
        setinal = new LinkedListNode();
        setinal.next = setinal.last = setinal;
    }

    /** adds item to the front of the LinkedListDeque */
    public void addFirst(type item) {
        LinkedListNode new_node = new LinkedListNode(item);
        new_node.addbetween(setinal, setinal.next);
        size++;
    }

    /** adds item to the rear of the LinkedListDeque */
    public void addLast(type item) {
        LinkedListNode new_node = new LinkedListNode(item);
        new_node.addbetween(setinal.last, setinal);
        size++;
    }

    /** returns the size of the LinkedListDeque instantly */
    public int size() {
        return size;
    }

    /** returns whether the LinkedListDeque is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** prints the items in the LinkedListDeque from first to last, separated by a space */
    public void printDeque() {
        LinkedListNode p = setinal.next;
        if (p == setinal) {
            return;
        }
        else {
            System.out.print(p.item);
            p = p.next;
        }
        for (; p != setinal; p = p.next) {
            System.out.print(" " + p.item);
        }
    }

    /** removes and returns the first item
     *  if isEmpty(), return null
     */
    public type removeFirst() {
        if (size != 0) {
            type rtn = setinal.next.item;
            setinal.next = setinal.next.next;
            setinal.next.last = setinal;
            size--;
            return rtn;
        }
        else {
            return null;
        }
    }

    /** removes and returns the last item */
    public type removeLast() {
        if (size != 0) {
            type rtn = setinal.next.item;
            setinal.last = setinal.last.last;
            setinal.last.next = setinal;
            size--;
            return rtn;
        }
        else {
            return null;
        }
    }

    /** returns the given item at the given index
     *  if no such item exists, returns null
     *  may not alter the LinkedListDeque
     *  uses iteration
     */
    public type get(int index) {
        // check in case the required item doesn't exist
        if (size <= index + 1) {
            return null;
        }

        LinkedListNode p = setinal.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** returns the given item at the given index
     *  if no such item exits, returns null
     *  may not alter the LinkedListDeque
     *  uses recursion
     */
    public type getRecursive(int index) {
        // check in case the required item doesn't exist
        if (size <= index + 1) {
            return null;
        }

        return setinal.next.getRecursive(index);
    }
}
