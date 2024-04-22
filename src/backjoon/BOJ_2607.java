package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_2607 {
    static int T;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(br.readLine());
        Set<Character> set = new HashSet<>();
        String a = br.readLine();
        for (int i = 0; i < a.length(); i++) {
            set.add(a.charAt(i));
        }
        int count = T - 1;
        for (int i = 0; i < T - 1; i++) {
            String b = br.readLine();
            for (int j = 0; j < b.length(); j++) {
                if (!set.contains(b.charAt(j))) {
                    count -= 1;
                }
            }
        }

        System.out.println(count);
    }

}
