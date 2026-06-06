package org.example;

import java.util.*;

public class HararyGraph {

    public static Map<Integer, List<Integer>> build(int k, int n) {

        Map<Integer, List<Integer>> G = new HashMap<>();

        for (int i = 0; i < n; i++) {
            G.put(i, new ArrayList<>());
        }

        // Case 1: k even
        if (k % 2 == 0) {

            int half = k / 2;

            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= half; j++) {

                    addEdge(G, i, (i + j) % n);
                    addEdge(G, i, (i - j + n) % n);
                }
            }
        }

        // Case 2: k odd, n even
        else if (n % 2 == 0) {

            int half = (k - 1) / 2;

            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= half; j++) {

                    addEdge(G, i, (i + j) % n);
                    addEdge(G, i, (i - j + n) % n);
                }
            }

            for (int i = 0; i < n / 2; i++) {
                addEdge(G, i, (i + n / 2) % n);
            }
        }

        // Case 3: k odd, n odd
        else {

            int half = (k - 1) / 2;

            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= half; j++) {

                    addEdge(G, i, (i + j) % n);
                    addEdge(G, i, (i - j + n) % n);
                }
            }

            for (int i = 0; i < n; i++) {
                addEdge(G, i, (i + n / 2) % n);
            }
        }

        return G;
    }

    private static void addEdge(Map<Integer, List<Integer>> G, int a, int b) {

        if (!G.get(a).contains(b)) {
            G.get(a).add(b);
        }

        if (!G.get(b).contains(a)) {
            G.get(b).add(a);
        }
    }
}