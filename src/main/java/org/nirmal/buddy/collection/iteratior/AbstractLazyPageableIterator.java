package org.nirmal.buddy.collection.iteratior;

import static java.util.Objects.isNull;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author nirmal.s
 *
 *         This is abstract class which provides basic implementation for lazy iterator for large collections , We can use this lazy iterator. It brings data in
 *         chunk instead of getting all data in single fetch. Implementation class requires to implement fetch logic for specified chunk
 *
 * @param <T>
 */
public abstract class AbstractLazyPageableIterator<T> implements Iterator<T> {

    protected Page page;
    private List<T> records;

    private Long rowNo = -1L;
    private int rowNoInPage = -1;
    private Long totalRowCount = -1L;

    public AbstractLazyPageableIterator(Page page) {
        page.setPageNo(Page.DEFAULT_PAGE_NUMBER);
        if (isNull(page.getPageSize())) {
            page.setPageSize(Page.DEFAULT_PAGE_SIZE);
        }
        this.page = page;
    }

    /*
     * Implement this method and return totalRowCount.
     */
    public abstract Long getTotalRowCount();

    /*
     * Implement this method and return next page.
     */
    public abstract List<T> getNextPage();

    @Override
    public boolean hasNext() {
        if (rowNo == -1) {
            this.rowNo = 0L;
            this.rowNoInPage = 0;
            this.totalRowCount = getTotalRowCount();
            this.records = getNextPage();
        }

        if (rowNo < totalRowCount) {
            return true;
        }
        return false;
    }

    /*
     * This method fetches data in chunk size. It will load next page of data from database only once current page data has been consumed.
     *
     */
    @Override
    public T next() {
        if (rowNoInPage == page.getPageSize()) {
            Long nextPageNo = page.getPageNo() + 1L;
            page.setPageNo(nextPageNo);
            records = getNextPage();
            rowNoInPage = 0;
        }

        T t = records.get(rowNoInPage);
        rowNo++;
        rowNoInPage++;
        return t;
    }

}
