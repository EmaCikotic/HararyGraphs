package org.example;

import java.util.*;

public class InfectionSimulator {


    public static Set<Integer> simulateInfection(
            Map<Integer, List<Integer>> G,
            Set<Integer> infected) {

        Set<Integer> current = new HashSet<>(infected);
        boolean changed = true;

        while (changed) {

            changed = false;
            Set<Integer> next = new HashSet<>(current);

            for (int v : G.keySet()) {

                if (!current.contains(v)) {

                    int count = 0;

                    for (int u : G.get(v)) {
                        if (current.contains(u)) {
                            count++;
                        }
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
    public static Pair<Integer, Set<Integer>> infectionNumber(
            Map<Integer, List<Integer>> G) {

        int n = G.size();
        List<Integer> nodes = new ArrayList<>(G.keySet());

        for (int size = 1; size <= n; size++) {

            for (int[] combo : combinations(n, size)) {

                Set<Integer> S = new HashSet<>();

                for (int idx : combo) {
                    S.add(nodes.get(idx));
                }

                Set<Integer> infected =
                        simulateInfection(G, S);

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

        for (int i = 0; i < k; i++) {
            combo[i] = i;
        }

        while (combo[k - 1] < n) {

            result.add(combo.clone());

            int t = k - 1;

            while (t != 0 &&
                    combo[t] == n - k + t) {
                t--;
            }

            combo[t]++;

            for (int i = t + 1; i < k; i++) {
                combo[i] = combo[i - 1] + 1;
            }
        }

        return result;
    }
}