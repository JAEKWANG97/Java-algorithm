package swexpert.solvingClub.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MedianSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine();
        String[] numbersStr = line.split(" ");
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(numbersStr[i]);
        }

// 배열 정렬
        Arrays.sort(numbers);

        int median = numbers[n / 2];
        System.out.println("median = " + median);

        sc.close();
    }
}
