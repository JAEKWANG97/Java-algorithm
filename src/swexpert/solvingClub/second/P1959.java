package swexpert.solvingClub.second;

import java.util.Scanner;

public class P1959 {
    public static void main(String[] args) {
        //A열이나 B열을 자유롭게 움직여서 마주보는 수의 곱의 합이 최대
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] A = new int[N];
            int[] B = new int[M];
            for(int j = 0 ; j < A.length ; j ++) A[j] = sc.nextInt();
            for(int k = 0 ; k < B.length ; k ++) B[k] = sc.nextInt();
            int answer = (A.length < B.length) ? calculateAnwer(A, B) : calculateAnwer(B, A);
            System.out.println("#" + (i + 1) + " " + answer);
        }
    }
    private static int calculateAnwer(int[] arr1, int[] arr2) {
        int max_sum = Integer.MIN_VALUE;
        //arr1 이 더 짧다는 가정임
        for (int j = 0; j <= arr2.length - arr1.length; j++) {
            int cur_sum = 0;
            for (int i = 0; i < arr1.length; i++) {
                cur_sum += arr1[i] * arr2[i + j];
            }
            max_sum = Math.max(cur_sum , max_sum);
        }
        return max_sum;
    }
}
