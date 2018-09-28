package example.buddy.collection;

import java.util.ArrayList;
import java.util.List;

import org.nirmal.buddy.collection.DifferentialListView;

public class TestCollectionView {
    public static void main(String[] args) {

        List<SampleDTO> lstNew = new ArrayList<>();

        lstNew.add(new SampleDTO("new1"));
        lstNew.add(new SampleDTO("new2"));
        lstNew.add(new SampleDTO("new3"));

        lstNew.add(new SampleDTO(1L, "unchanged1"));
        lstNew.add(new SampleDTO(2L, "unchanged2"));
        lstNew.add(new SampleDTO(3L, "unchanged3"));

        lstNew.add(new SampleDTO(4L, "changed1"));
        lstNew.add(new SampleDTO(5L, "changed2"));
        lstNew.add(new SampleDTO(6L, "changed3"));

        List<SampleDTO> dbNew = new ArrayList<>();

        dbNew.add(new SampleDTO(1L, "unchanged1"));
        dbNew.add(new SampleDTO(2L, "unchanged2"));
        dbNew.add(new SampleDTO(3L, "unchanged3"));

        dbNew.add(new SampleDTO(4L, "changed1."));
        dbNew.add(new SampleDTO(5L, "changed2."));
        dbNew.add(new SampleDTO(6L, "changed3."));

        dbNew.add(new SampleDTO(7L, "Deleted1"));
        dbNew.add(new SampleDTO(8L, "Deleted2"));
        dbNew.add(new SampleDTO(9L, "Deleted3"));

        long t = System.currentTimeMillis();
        DifferentialListView<SampleDTO> view = DifferentialListView.of(lstNew, dbNew);
        System.out.println(System.currentTimeMillis() - t);
        System.out.println("New: " + view.getAddedElements());
        System.out.println("Deleted: " + view.getRemovedElements());
        System.out.println("updated: " + view.getUpdatedElements());
        System.out.println("unchanged: " + view.getUnChangedElements());
        System.out.println("final: " + view.buildMergedElements());

    }
}
