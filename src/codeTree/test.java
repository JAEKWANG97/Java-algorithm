package codeTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int height = sc.nextInt();
            list.add(height);
        }
        Collections.sort(list);
        sc.close();
        System.out.println(solve(list));
    }

    private static int solve(List<Integer> list) {
        int minCost = Integer.MAX_VALUE;

        for (int minH = list.get(0); minH <= list.get(list.size() - 1) - 17; minH++) {
            int cost = calculateCost(minH, minH + 17, list);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    private static int calculateCost(int minH, int maxH, List<Integer> list) {
        int cost = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < minH) {
                cost += (minH - list.get(i)) * (minH - list.get(i));
            } else if (list.get(i) > maxH) {
                cost += (list.get(i) - maxH) * (list.get(i) - maxH);
            }
        }
        return cost;
    }
}
