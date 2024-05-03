package swexpert.codeBattle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class no1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String nStr = br.readLine();
            int init = Integer.parseInt(nStr);
            Set<Integer> set = new HashSet<>();

            while (set.size() < 10) {
                // System.out.println(nStr + " " + set);
                for (int i = 0; i < nStr.length(); i++) {
                    int n = nStr.charAt(i) - '0';
                    set.add(n);
                }
                nStr = String.valueOf(Integer.parseInt(nStr) + init);
            }

            sb.append("#" + t + " " + (Integer.parseInt(nStr) - init) + "\n");
        }
        System.out.println(sb);
    }
}
