package backjoon.Mathmetics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
다각형의 N개 꼭짓점 좌표를 (x1, y1), (x2, y2), ..., (xN, yN)으로 나열합니다. 
여기서 (x1, y1)은 시작점이자 끝점으로,
즉 첫 번째와 마지막 좌표가 같아야 합니다.
각 꼭짓점에 대해, 현재 꼭짓점의 x좌표와 다음 꼭짓점의 y좌표를 곱하고, 
현재 꼭짓점의 y좌표와 다음 꼭짓점의 x좌표를 곱합니다. 이 과정을 모든 꼭짓점에 대해 반복하고, 두 결과의 차이를 모두 더합니다.
이렇게 얻어진 합을 2로 나누고, 절대값을 취합니다. 이 값이 다각형의 넓이입니다.
 */
public class BOJ_2166 {
    static class Location {
        long x;
        long y;

        public Location(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static List<Location> locations = new ArrayList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Location start;

    public static void main(String[] args) throws NumberFormatException, IOException {
        init();
        // solve();
    }

    private static void init() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        double sum = 0;
        Location start = null;
        Location prev = null;
        Location last = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            if (i == 0) {
                prev = new Location(x, y);
                start = new Location(x, y);
                continue;
            }
            if (i == N - 1) {
                last = new Location(x, y);
            }
            sum += prev.x * y - prev.y * x;
            prev = new Location(x, y);
        }
        sum += start.x * last.y - start.y * last.x;
        double answer = Math.abs(sum / 2);
        System.out.printf("%.1f", answer);
    }
}

// 다익스트라로