package org.example;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.net.Socket;
import java.util.*;

public class Harary {

    // Build the Harary graph H_{r,n}
    //spremeni to funkcijo da bo parameter k in ne r

    //vse mora bit narejeno da je k in ne r in mora bit rezultal  n/k in da je ceil? al je floor? :(((((
    public static Map<Integer, List<Integer>> hararyGraphKN(int r, int n) {
        Map<Integer, List<Integer>> G = new HashMap<>();
        for (int i = 0; i < n; i++) G.put(i, new ArrayList<>());

        // Case 1: r even
        if (r % 2 == 0) {
            int half = r / 2;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= half; j++) {
                    addEdge(G, i, (i + j) % n);
                    addEdge(G, i, (i - j + n) % n);
                }
            }
        }
        // Case 2: r odd and n even
        else if (n % 2 == 0) {
            int half = (r - 1) / 2;
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
        // Case 3: r odd and n odd
        else {
            int half = (r - 1) / 2;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= half; j++) {
                    addEdge(G, i, (i + j) % n);
                    addEdge(G, i, (i - j + n) % n);
                }
            }
            for (int i = 0; i < n; i++) {
                addEdge(G, i, (i + n/2) % n);
            }
        }

        return G;
    }

    // Helper: add edge
    private static void addEdge(Map<Integer, List<Integer>> G, int a, int b) {
        if (!G.get(a).contains(b)) G.get(a).add(b);
        if (!G.get(b).contains(a)) G.get(b).add(a);
    }

    // Infection simulation
    public static Set<Integer> simulateInfection(Map<Integer, List<Integer>> G, Set<Integer> infected) {
        Set<Integer> current = new HashSet<>(infected);
        boolean changed = true;

        while (changed) {
            changed = false;
            Set<Integer> next = new HashSet<>(current);

            for (int v : G.keySet()) {
                if (!current.contains(v)) {
                    int count = 0;
                    for (int u : G.get(v)) {
                        if (current.contains(u)) count++;
                    }
                    if (count >= 2) {
                        next.add(v);
                        changed = true;
                    }
                }
            }
            current = next;
        }

        return current;
    }

    // Compute m2(G)
    public static Pair<Integer, Set<Integer>> infectionNumber(Map<Integer, List<Integer>> G) {
        int n = G.size();
        List<Integer> nodes = new ArrayList<>(G.keySet());

        for (int size = 1; size <= n; size++) {
            for (int[] combo : combinations(n, size)) {
                Set<Integer> S = new HashSet<>();
                for (int idx : combo) S.add(nodes.get(idx));

                Set<Integer> infected = simulateInfection(G, S);

                if (infected.size() == n) {
                    return new Pair<>(size, S);
                }
            }
        }
        return null;
    }

    // Generate combinations
    private static List<int[]> combinations(int n, int k) {
        List<int[]> result = new ArrayList<>();
        int[] combo = new int[k];
        for (int i = 0; i < k; i++) combo[i] = i;

        while (combo[k - 1] < n) {
            result.add(combo.clone());
            int t = k - 1;
            while (t != 0 && combo[t] == n - k + t) t--;
            combo[t]++;
            for (int i = t + 1; i < k; i++) combo[i] = combo[i - 1] + 1;
        }
        return result;
    }

    // Pair class
    public static class Pair<A,B> {
        public A first;
        public B second;
        public Pair(A a, B b) { first = a; second = b; }
    }

    // Group printer
    public static void runGroup(String title, int[][] tests) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("|  H_{k,n}: |      m2     |    S      |");
        System.out.println("--------------------------------------");
        for (int[] test : tests) {
            int r = test[0];
            int n = test[1];

            Map<Integer, List<Integer>> G = hararyGraphKN(r, n);
            Pair<Integer, Set<Integer>> result = infectionNumber(G);


            System.out.printf("|  H_{%d,%d}: |  m2 = %-3d  | S = %s%n ",
                    r, n, result.first, result.second);
        }
    }

    // MAIN
    public static void main(String[] args) {

        int[][] small = {
                {1,5}, {2,5}, {3,5} // {1,5} not possible, r would be 0
        };
        int[][] evenN = {
                {2,10}, {3,10}, {4,12}
        };
        int[][] oddN = {
                {2,11}, {3,11}, {5,11}
        };
        int[][] fixedR = {
                {2,5}, {2,8}, {2,12}, {2,15},
                {3,6}, {3,10}, {3,14}
        };
        int[][] fixedN = {
                {2,10}, {3,10}, {4,10},
                {2,12}, {3,12}, {4,12}, {5,12}
        };
        int[][] large = {
                {4,20}, {5,20}, {6,24}
        };


        //novi primeri


        //case1
        int[][] k2r = {
                {2,5},
                {2,8},
                {2,12},
                {3,6},
                {3,10},
                {3,14}

        };

        //case2-opposite edges, infection spreads faster

        int[][] k2rplus1andNeven = {
                {3,10},
                {5,12},
                {7,16},

        };


        //case3-the tricky case
        int[][] k2rplus1andnOdd = {
                {3,10},
                {5,11},
                {7,15}
        };


        //test za velik primer
        int[][] bigCAse = {
                {6,24},

        };


        //ok torej pride formula kinda n/k in ce pride ostanek das floor  n/k






        runGroup("SMALL", small);
        runGroup("EVEN n", evenN);
        runGroup("ODD n", oddN);
        runGroup("FIXED r", fixedR);
        runGroup("FIXED n", fixedN);
        runGroup("LARGE", large);
        System.out.println("----------------------------");
        System.out.println("NOVI PRIMERI");
        runGroup("CASE 1 :", k2r);
        runGroup("CASE 2", k2rplus1andNeven);
        runGroup("CASE 3", k2rplus1andnOdd);
        runGroup("big", bigCAse); // test z velik primer
    }
}
