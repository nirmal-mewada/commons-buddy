package org.nirmal.buddy.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class is not thread safe. Helps to identify updated,added and deleted values from list compare to existing list.
 *
 * @author nirmal.s
 *
 * @param <T>
 */
public class DifferentialListView<T extends Identifiable> {

    List<T> requestedElements;
    List<T> dbElements;
    List<T> addedElements = new ArrayList<>();
    List<T> removedElements = null;
    List<T> updatedElements = new ArrayList<>();
    List<T> finalElements = null;

    private DifferentialListView(List<T> newElements, List<T> dbElements) {
        super();
        this.requestedElements = newElements;
        this.dbElements = dbElements;
    }

    public static <T extends Identifiable> DifferentialListView<T> of(List<T> newElements, List<T> dbElements) {
        DifferentialListView<T> view = new DifferentialListView<>(newElements, dbElements);
        view.build();
        return view;
    }

    @SuppressWarnings("unchecked")
    public void build() {
        Map<Long, T> dbMap = new HashMap<>();
        for (T e : dbElements) {
            dbMap.put(e.getID(), e);
        }

        Iterator<T> itr = requestedElements.iterator();
        while (itr.hasNext()) {
            Identifiable e = itr.next();
            if (e.getID() == null) {
                addedElements.add((T) e);
                itr.remove();
                continue;
            }
            Identifiable dbElem = dbMap.get(e.getID());
            if (dbElem == null) throw new IllegalArgumentException("Invalid Request ID:" + e.getID());

            if (!dbElem.equals(e)) {
                updatedElements.add((T) e);
                itr.remove();
            }
            dbMap.remove(e.getID());
        }
        removedElements = new ArrayList<>(dbMap.values());
    }

    public List<T> getAddedElements() {
        return addedElements;
    }

    public List<T> getRemovedElements() {
        return removedElements;
    }

    public List<T> getUpdatedElements() {
        return updatedElements;
    }

    public List<T> getUnChangedElements() {
        return requestedElements;
    }

    public List<T> buildMergedElements() {
        if (finalElements == null) {
            finalElements = new ArrayList<>();
            finalElements.addAll(requestedElements);
            finalElements.addAll(updatedElements);
            finalElements.addAll(addedElements);
        }
        return finalElements;
    }

}
