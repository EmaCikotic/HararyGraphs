package org.example;

import java.util.*;

public class Harary {

    public static int predictedM2(int k, int n) {

        if (k == 2) {
            return (n + 1) / 2;
        }

        if (k == 3) {
            return (n / 4) + 1;
        }

        return 2;
    }

    public static void runGroup(String title, int[][] tests) {

        System.out.println("\n=== " + title + " ===");
        System.out.println("| Graph | m2(G) | Formula | Match | Contagious Set |");
        System.out.println("----------------------------------------------------");

        for (int[] test : tests) {

            int k = test[0];
            int n = test[1];

            Map<Integer, List<Integer>> G =
                    HararyGraph.build(k, n);

            Pair<Integer, Set<Integer>> result =
                    InfectionSimulator.infectionNumber(G);

            int predicted = predictedM2(k, n);

            System.out.printf(
                    "| H_{%d,%d} | %-5d | %-7d | %-5s | %s%n",
                    k,
                    n,
                    result.first,
                    predicted,
                    result.first == predicted ? "YES" : "NO",
                    result.second
            );
        }
    }

    public static void main(String[] args) {

        int[][] k2Cases = {
                {2,5},
                {2,8},
                {2,12},
                {2,15}
        };

        int[][] k3Cases = {
                {3,6},
                {3,10},
                {3,14}
        };

        int[][] kGe4Cases = {
                {4,12},
                {5,12},
                {6,24},
                {7,16}
        };

        System.out.println("=================================");
        System.out.println("HARARY GRAPH INFECTION NUMBERS");
        System.out.println("=================================");

        runGroup("k = 2", k2Cases);
        runGroup("k = 3", k3Cases);
        runGroup("k >= 4", kGe4Cases);
    }
}