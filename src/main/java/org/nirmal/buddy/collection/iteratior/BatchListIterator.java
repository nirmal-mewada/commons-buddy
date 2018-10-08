package org.nirmal.buddy.collection.iteratior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author nirmal.s
 *
 *
 * @param <T>
 */
public class BatchListIterator<T> extends BatchIterator<List<T>, T> {

    public BatchListIterator(Iterator<T> iterator, long pageSize) {
        super(iterator, pageSize);
    }

    @Override
    public List<T> next() {
        return super.next();
    }
    @Override
    public List<T> newCollection() {
        return new ArrayList<>();
    }

}
