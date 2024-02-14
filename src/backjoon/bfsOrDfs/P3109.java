package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3109 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static Character[][] arr;
    static int count = 0;

    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new Character[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            flag = false;
            dfs(i, 0);
            arr[i][0] = 'o';
            
        }

        System.out.println(count);

    }

    static void dfs(int x, int y) {

        if (y == M - 1) {
            count += 1;
            flag = true;
            return;
        }

        if (!flag && isIn(x - 1, y + 1) && arr[x - 1][y + 1] == '.') {
            arr[x - 1][y + 1] = 'o';
            dfs(x - 1, y + 1);
        }
        if (!flag && isIn(x, y + 1) && arr[x][y + 1] == '.') {
            arr[x][y + 1] = 'o';
            dfs(x, y + 1);
        }
        if (!flag && isIn(x + 1, y + 1) && arr[x + 1][y + 1] == '.') {
            arr[x + 1][y + 1] = 'o';
            dfs(x + 1, y + 1);
        }


    }

    static boolean isIn(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }
}
