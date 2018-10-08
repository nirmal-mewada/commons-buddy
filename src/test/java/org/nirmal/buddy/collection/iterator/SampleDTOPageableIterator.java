package org.nirmal.buddy.collection.iterator;

import java.util.ArrayList;
import java.util.List;

import org.nirmal.buddy.collection.SampleDTO;
import org.nirmal.buddy.collection.iteratior.AbstractLazyPageableIterator;
import org.nirmal.buddy.collection.iteratior.Page;

/**
 *
 *
 *
 *
 */
public class SampleDTOPageableIterator extends AbstractLazyPageableIterator<SampleDTO> {

    public SampleDTOPageableIterator() {
        super(new Page());
    }

    public SampleDTOPageableIterator(Page page) {
        super(page);
    }

    /*
     *
     * Return totalNoOfRows that will be exported.
     *
     */
    @Override
    public Long getTotalRowCount() {
        return 10L;
    }

    /*
     * Return next list of cases.
     */
    @Override
    public List<SampleDTO> getNextPage() {
        ArrayList<SampleDTO> lst = new ArrayList<>();
        System.out.println("\nLoading page : " + page);
        for (int i = 1; i <= page.getPageSize(); i++) {
            lst.add(new SampleDTO(((page.getPageNo() - 1) * page.getPageSize()) + i, "" + System.currentTimeMillis()));

        }
        return lst;
    }

    public static void main(String[] args) {
        SampleDTOPageableIterator iterator = new SampleDTOPageableIterator(new Page(1l, 2l, null));
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
