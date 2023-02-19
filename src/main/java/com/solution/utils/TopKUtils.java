package com.solution.utils;

import java.util.*;

public class TopKUtils {

    private TopKUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param input      size:n
     * @param k          int
     * @param comparator @{@link Comparator}
     * @return Top K elements based on the passed comparator
     * Time Complexity: nLogK
     */
    public static <T> List<T> findTopK(List<T> input, int k, Comparator<T> comparator) {
        if (input == null) {
            return Collections.emptyList();
        }
        PriorityQueue<T> pq = new PriorityQueue<>(comparator);

        input.forEach(number -> {
            pq.add(number);
            if (pq.size() > k) {
                pq.poll();
            }
        });
        List<T> topKList = new ArrayList<>(k);
        while (!pq.isEmpty()) {
            topKList.add(pq.poll());
        }
        Collections.reverse(topKList);
        return topKList;
    }
}
