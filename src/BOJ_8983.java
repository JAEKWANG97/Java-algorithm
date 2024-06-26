import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {
    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, L;
    static int[][] arr;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] shootingPosition;
    static Location[] animalPosition;

    public static void main(String[] args) throws IOException {
        solve();
    }

    private static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int count = 0;
        // NOTE - 사대의 수 M (1 ≤ M ≤ 100,000)
        // NOTE - 동물의 수 N ()

        shootingPosition = new int[M];
        animalPosition = new Location[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            shootingPosition[i] = x;
        }

        Arrays.sort(shootingPosition);

        // NOTE - 동물들의 좌표 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Location animal = new Location(x, y);

            int left = 0;
            int right = M - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int dist = calDist(mid, animal);
                if (dist <= L) {
                    count += 1;
                    break;

                }
                if (shootingPosition[mid] < animal.x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        System.out.println(count);
    }

    private static int calDist(int shootPosIndex, Location animal) {
        return Math.abs(shootingPosition[shootPosIndex] - animal.x) + animal.y;
    }

}
// 사대의 수 M (1 ≤ M ≤ 100,000), 동물의 수 N (1 ≤ N ≤ 100,000), 사정거리 L (1 ≤ L ≤
// 1,000,000,000)

// LINK - https://www.acmicpc.net/problem/8983
//
