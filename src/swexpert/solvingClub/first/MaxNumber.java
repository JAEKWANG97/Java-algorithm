package swexpert.solvingClub.first;

import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();  // 숫자 입력 후 남은 줄바꿈 문자를 소비합니다.
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] numbers = line.split(" ");
            int max = Integer.MIN_VALUE; // 최대값 초기화
            for (String numStr : numbers) {
                int cur = Integer.parseInt(numStr);
                if (max < cur) {
                    max = cur;
                }
            }
            System.out.println("#" + (i + 1) + " " + max);
        }
        sc.close();
    }
}
