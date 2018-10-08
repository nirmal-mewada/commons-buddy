package org.nirmal.buddy.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {

        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 5));
        Set<Integer> b = new HashSet<>(Arrays.asList(1, 2,3));
        //SetView<Integer> add = Sets.difference(a, b);
        //SetView<Integer> del = Sets.difference(b, a);
        // Collection<Integer> add = CollectionUtils.subtract(a, b);
        // Collection<Integer> del = CollectionUtils.subtract(b, a);

        // System.out.println(Lists.newArrayList(add));
        // System.out.println(Lists.newArrayList(del));
    }
}
