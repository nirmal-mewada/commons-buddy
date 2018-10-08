package org.nirmal.buddy.collection.iteratior;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author nirmal.s
 *
 *
 * @param <T>
 */
public class BatchSetIterator<T> extends BatchIterator<Set<T>, T> {

    public BatchSetIterator(Iterator<T> iterator, long pageSize) {
        super(iterator, pageSize);
    }

    @Override
    public Set<T> next() {
        return super.next();
    }
    @Override
    public Set<T> newCollection() {
        return new HashSet<>();
    }

}
