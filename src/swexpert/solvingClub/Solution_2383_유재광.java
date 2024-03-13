package swexpert.solvingClub;

import java.io.*;
import java.util.*;

public class Solution_2383_유재광 {
    static class Person {
        int x, y, stairChoice, timeToStair;

        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Stair {
        int x, y, length;

        public Stair(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }

    static List<Person> persons;
    static Stair[] stairs;
    static int N, minTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            persons = new ArrayList<>();
            stairs = new Stair[2];

            for (int i = 0, stairIndex = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) {
                        persons.add(new Person(i, j));
                    } else if (val > 1) {
                        stairs[stairIndex++] = new Stair(i, j, val);
                    }
                }
            }

            minTime = Integer.MAX_VALUE;
            assignStairs(0);
            System.out.println("#" + tc + " " + minTime);
        }
    }

    static void assignStairs(int index) {
        if (index == persons.size()) {
            simulate();
            return;
        }

        for (int i = 0; i < 2; i++) {
            persons.get(index).stairChoice = i;
            assignStairs(index + 1);
        }
    }

    static void simulate() {
        int[] endTime = new int[2];
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        for (Person p : persons) {
            int dist = Math.abs(p.x - stairs[p.stairChoice].x) + Math.abs(p.y - stairs[p.stairChoice].y);
            if (p.stairChoice == 0) {
                pq1.add(dist + 1); // 계단 입구에 도착하는 시간
            } else {
                pq2.add(dist + 1);
            }
        }

        endTime[0] = calculateTime(pq1, stairs[0].length);
        endTime[1] = calculateTime(pq2, stairs[1].length);

        minTime = Math.min(minTime, Math.max(endTime[0], endTime[1]));
    }

    static int calculateTime(PriorityQueue<Integer> pq, int length) {
        int time = 0;
        int[] stairs = new int[3];

        while (!pq.isEmpty()) {
            int arrivalTime = pq.poll();

            int i = 0;
            for (; i < stairs.length; i++) {
                if (stairs[i] <= arrivalTime) {
                    break;
                }
            }

            if (i < stairs.length) {
                stairs[i] = arrivalTime + length;
            } else {
                Arrays.sort(stairs);
                stairs[0] = stairs[0] + length;
                time = stairs[0] - 1;
            }
        }

        Arrays.sort(stairs);
        return stairs[2]; // 가장 마지막으로 내려간 사람의 시간
    }
}
