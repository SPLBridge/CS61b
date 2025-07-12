package byog.Core;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PositionListSet implements Iterable<PositionList>{
    private Set<PositionList> set = new HashSet<>();

    /** add a list */
    void add(PositionList list) {
        set.add(list);
    }

    /** check if the set contains the given list
     * should be private after testing
     */
    boolean contains(PositionList list) {
        return set.contains(list);
    }

    /** returns the array that contains every element inside */
    PositionList[] toArray() {
        return set.toArray(new PositionList[0]);
    }

    @Override
    public Iterator<PositionList> iterator() {
        return set.iterator();
    }

    /** merges 2 lists, does not modify the lists and returns the merged list */
    private PositionList merge(PositionList list1, PositionList list2) {
        PositionList mergedList = new PositionList();
        for (Position P : list1) {
            mergedList.add(P);
        }
        for (Position P : list2) {
            mergedList.add(P);
        }

        return mergedList;
    }

    /** merges all lists inside without modifying
     * returns the result
     */
    PositionList merge() {
        PositionList mergedList = new PositionList();
        for (PositionList L : this) {
            mergedList = merge(mergedList, L);
        }

        return  mergedList;
    }
}
