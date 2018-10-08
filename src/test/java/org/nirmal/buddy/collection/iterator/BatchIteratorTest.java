package org.nirmal.buddy.collection.iterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nirmal.buddy.collection.iteratior.BatchListIterator;
import org.nirmal.buddy.collection.iteratior.BatchSetIterator;

/**
 *
 * @author nirmal.s
 *
 */
public class BatchIteratorTest {
    public static void main(String[] args) {
        List<String> lst = new ArrayList<>();
        lst.add("1");
        lst.add("2");
        lst.add("3");
        lst.add("4");
        lst.add("5");
        // BatchIterator<List<String>, String> itr = new BatchListIterator<String>(lst.iterator(), 2L);
        BatchListIterator<String> itr = new BatchListIterator<String>(lst.iterator(), 2L);
        while (itr.hasNext()) {
            List<String> list = itr.next();
            System.out.println(list);
        }

        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");
        BatchSetIterator<String> itr1 = new BatchSetIterator<String>(lst.iterator(), 2L);
        while (itr1.hasNext()) {
            Set<String> list = itr1.next();
            System.out.println(list);
        }
    }
}
