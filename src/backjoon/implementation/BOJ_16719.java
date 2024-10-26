import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16719 {
    private static char[] str;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        visited = new boolean[str.length];

        zoac(0, str.length - 1);

        System.out.print(sb.toString());
    }

    private static void zoac(int start, int end) {
        if (start > end)
            return;

        int minIndex = start;
        for (int i = start; i <= end; i++) {
            if (str[i] < str[minIndex]) {
                minIndex = i;
            }
        }

        visited[minIndex] = true;
        for (int i = 0; i < str.length; i++) {
            if (visited[i])
                sb.append(str[i]);
        }
        sb.append("\n");

        zoac(minIndex + 1, end);
        zoac(start, minIndex - 1);
    }
}