package backjoon.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P1913 {

    static int[] deltaX = {1, 0, -1, 0};
    static int[] deltaY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        int targetX = 0;
        int targetY = 0;
        int cur = n * n;

        int[][] arr = new int[n][n];
        int x = 0;
        int y = 0;
        int i = 0;
        while (cur != 0) {
            arr[x][y] = cur;
            if (cur == target) {
                targetX = x;
                targetY = y;
            }
            cur -= 1;
            int nextX = x + deltaX[i];
            int nextY = y + deltaY[i];
            if (nextX >= n || nextY >= n || nextX < 0 || nextY < 0 || arr[nextX][nextY] != 0) {
                i = (i + 1) % 4;
                x = x + deltaX[i];
                y = y + deltaY[i];
            } else {
                x = nextX;
                y = nextY;
            }
        }

        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr.length; k++) {
               sb.append(arr[j][k]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

        System.out.println((targetX + 1) + " " + (targetY + 1));

    }
}
