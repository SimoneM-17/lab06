package it.unibo.collections;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int FIRST_INDEX = 0;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        List<Integer> intList = new ArrayList<>();
        for (int i = 1000; i < 2000 ; i++) {
            intList.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        List<Integer> intList2 = new LinkedList<>(intList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        Integer firstElem = intList.get(FIRST_INDEX);
        intList.set(FIRST_INDEX, intList.get(intList.size() - 1));
        intList.set(intList.size() - 1, firstElem);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */

         /*
        for (Integer elem: intList) {
            System.out.println(elem);
        }
        */

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

         // ArrayList
        long time = System.nanoTime();
        for (int i = 1; i <= 100_000; i++) {
            intList.add(FIRST_INDEX, i);
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "inserting "
            + 100_000
            + " integers in an ArrayList took ("
            + millis + ")ms"
        );
        
        // LinkedList
        long time2 = System.nanoTime();
        for (int i = 1; i <= 100_000; i++) {
            intList2.add(FIRST_INDEX, i);
        }
        time2 = System.nanoTime() - time2;
        var millis2 = TimeUnit.NANOSECONDS.toMillis(time2);
        System.out.println(
            "inserting "
            + 100_000
            + " integers in a LinkedList took ("
            + millis2 + ")ms"
        );
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */

        // ArrayList
        time = System.nanoTime();
        for (int i = 0; i <= 1000; i++) {
            intList.get(intList.size() / 2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "reading "
            + 1000
            + " elements in an ArrayList took ("
            + millis + ")ms"
        );
        
        // LinkedList
        time2 = System.nanoTime();
        for (int i = 1; i <= 1000; i++) {
            intList2.get(intList2.size() / 2);
        }
        time2 = System.nanoTime() - time2;
        millis2 = TimeUnit.NANOSECONDS.toMillis(time2);
        System.out.println(
            "reading "
            + 1000
            + " elements in a LinkedList took ("
            + millis2 + ")ms"
        );
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> map = new HashMap<>();
        map.put("Africa", (long) 1_110_635_000);
        map.put("Americas", Long.valueOf(972_005_000));
        map.put("Antartica", 0L);
        map.put("Asia", 4_298_273_000L);
        map.put("Europe", 742_452_000L);
        map.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */
        Long worldPopulation = 0L;
        for (Long elem: map.values()) {
            worldPopulation += elem;
        }
        System.out.println("popolazione del mundo: " + worldPopulation);
        System.out.println("pop asia: " + map.get("Asia"));
    }
}
