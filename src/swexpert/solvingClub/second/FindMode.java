package swexpert.solvingClub.second;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FindMode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            int test_case = sc.nextInt();
            sc.nextLine();
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            int[] numbers = new int[st.countTokens()];
            int index = 0;

            HashMap<Integer, Integer> map = new HashMap<>();
            while (st.hasMoreTokens()) {
                // 각 토큰을 정수로 변환하여 배열에 저장합니다.
                int number = Integer.parseInt(st.nextToken());
                int currentFrequency = map.getOrDefault(number, 0);

                map.put(number, currentFrequency + 1);
            }

            int answer = findMode(map);
            System.out.println("#" + (i + 1) + " " + answer);


        }
    }

    private static int findMode(HashMap<Integer, Integer> map) {
        int maxFrequency = 0;
        int mode = 0;
        for (int s : map.keySet()) {
            int frequency = map.get(s);
            if (frequency >= maxFrequency) {
                maxFrequency = frequency;
                if (mode < s || mode ==0){
                    mode = s;
                }

            }
        }
        return mode;

    }
}
