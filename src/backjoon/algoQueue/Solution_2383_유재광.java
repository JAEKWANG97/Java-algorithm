package backjoon.algoQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2383_유재광 {


    static class Person implements Comparable<Person> {
        int row, col, time;
        int stair; //1번 계단, 2번 계단

        public Person(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Person o) {
            // TODO Auto-generated method stub
            return this.time - o.time;
        }

        @Override
        public String toString() {
            return "Person [row=" + row + ", col=" + col + ", time=" + time + ", stair=" + stair + "]" + "\n";
        }

    }

    static class Stair {
        int row, col, time;

        public Stair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Stair [row=" + row + ", col=" + col + ", time=" + time + "]";
        }

    }

    static int N;
    static int min = Integer.MAX_VALUE;
    static Stair[] stairs;
    static List<Person> people;
    static List<Person> offStair1;
    static List<Person> offStair2;
    static boolean[] caseCheck;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#" + t + " ");
            N = Integer.parseInt(br.readLine());
            stairs = new Stair[2];
            people = new ArrayList<>();
            offStair1 = new ArrayList<>();
            offStair2 = new ArrayList<>();
            int sNum = 0;
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp >= 2) {
                        stairs[sNum] = new Stair(r, c, tmp);
                        sNum++;
                    } else if (tmp == 1) {
                        people.add(new Person(r, c));
                    }
                }
            }
            caseCheck = new boolean[people.size()];
            min = Integer.MAX_VALUE;
            cases(0);
            sb.append(min).append("\n");
        }
        System.out.println(sb.toString());

    }

    private static void cases(int idx) {
        if (idx == people.size()) {
            setPeople();
            min = Math.min(min, getTime());
            return;
        }
        caseCheck[idx] = true;
        cases(idx + 1);
        caseCheck[idx] = false;
        cases(idx + 1);
    }

    private static int getTime() {
        int time = 0;
        List<Person> onStair1 = new ArrayList<>();
        List<Person> onStair2 = new ArrayList<>();
        Collections.sort(offStair1);
        Collections.sort(offStair2);

        while (true) {
            if (onStair1.isEmpty() && onStair2.isEmpty() && offStair1.isEmpty() && offStair2.isEmpty()) {
                break;
            }
            time++;
            for (int l = 0; l < onStair1.size(); l++) {
                onStair1.get(l).time--;
                if (onStair1.get(l).time == 0) {
                    onStair1.remove(l);
                    l--;
                }
            }
            for (int l = 0; l < onStair2.size(); l++) {
                onStair2.get(l).time--;
                if (onStair2.get(l).time == 0) {
                    onStair2.remove(l);
                    l--;
                }
            }
            for (int l = 0; l < offStair1.size(); l++) {
                offStair1.get(l).time--;
                if (offStair1.get(l).time < 0 && onStair1.size() < 3) {
                    offStair1.get(l).time = stairs[0].time;
                    onStair1.add(offStair1.get(l));
                    offStair1.remove(l);
                    l--;
                }
            }
            for (int l = 0; l < offStair2.size(); l++) {
                offStair2.get(l).time--;
                if (offStair2.get(l).time < 0 && onStair2.size() < 3) {
                    offStair2.get(l).time = stairs[1].time;
                    onStair2.add(offStair2.get(l));
                    offStair2.remove(l);
                    l--;
                }
            }
        }
        return time;

    }

    private static void setPeople() {
        offStair1.clear();
        offStair2.clear();
        for (int i = 0; i < people.size(); i++) {
            if (caseCheck[i] == true) {
                people.get(i).time =
                        Math.abs(people.get(i).row - stairs[0].row) + Math.abs(people.get(i).col - stairs[0].col);
                offStair1.add(people.get(i));
            } else {
                people.get(i).time =
                        Math.abs(people.get(i).row - stairs[1].row) + Math.abs(people.get(i).col - stairs[1].col);
                offStair2.add(people.get(i));
            }
        }
    }

}
