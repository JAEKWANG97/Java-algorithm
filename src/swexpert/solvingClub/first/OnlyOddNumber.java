package swexpert.solvingClub.first;

import java.util.Scanner;

public class OnlyOddNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] numbers = line.split(" ");
            int sum = 0;
            for (int j = 0; j < numbers.length; j++) {
                int cur = Integer.parseInt(numbers[j]);
                if (cur % 2 != 0) {
                    sum += cur;
                }
            }
            System.out.println("#" + (i + 1) + " " + sum);
        }
    }
}
