package org.nirmal.buddy.collection.iteratior;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * Split iterator into multiple iterators having specified batch size.
 * 
 * @see {@link BatchListIterator}
 * @see {@link BatchSetIterator}
 *
 * @author nirmal.s
 *
 * @param <T>
 * @param <E>
 * @param <T>
 */
public abstract class BatchIterator<E extends Collection<T>, T> implements Iterator<E>
{

    private Long pageSize = 10L;
    private Iterator<T> iterator;

    public BatchIterator(Iterator<T> iterator, long pageSize) {
        this.iterator = iterator;
        this.pageSize = pageSize;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();

        E data = newCollection();
        for (int i = 0; i < pageSize && iterator.hasNext(); i++) {
            data.add(iterator.next());
        }
        return data;
    }

    public abstract E newCollection();

}
