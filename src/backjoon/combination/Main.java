package backjoon.combination;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int N = scanner.nextInt();
            int[][] points = new int[N][2];
            HashSet<Long> pointSet = new HashSet<>();

            for (int i = 0; i < N; i++) {
                points[i][0] = scanner.nextInt();
                points[i][1] = scanner.nextInt();
                pointSet.add(encode(points[i][0], points[i][1]));
            }

            int maxArea = 0;
            for (int i = 0; i < N; i++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                for (int j = i + 1; j < N; j++) {
                    int x2 = points[j][0];
                    int y2 = points[j][1];

                    int dx = x2 - x1;
                    int dy = y2 - y1;

                    // 후보 점 C, D 찾기
                    int cx = x2 - dy;
                    int cy = y2 + dx;
                    int dx2 = x1 - dy;
                    int dy2 = y1 + dx;

                    if (pointSet.contains(encode(cx, cy)) && pointSet.contains(encode(dx2, dy2))) {
                        maxArea = Math.max(maxArea, dx * dx + dy * dy);
                    }
                }
            }
            System.out.println(maxArea);
        }
        scanner.close();
    }

    private static long encode(int x, int y) {
        return ((long) x + 10000) * 20001 + (long) y + 10000;
    }
}
