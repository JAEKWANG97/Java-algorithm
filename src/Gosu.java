import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Gosu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int u = list.get(0);
        int d = 0;
        for (int i = 1; i < n; i++) {
            int w = list.get(i) - 1;
            int l = list.get(i) + 1;
            if (w != -1 && list.contains(w)) {
                d = Math.max(d, d(w, u, list));
            }
            if (l != -1 && list.contains(l)) {
                d = Math.max(d, d(l, u, list));
            }
            u = w;
        }
        System.out.println(u + +d);
    }

    public static int d(int x, int y, ArrayList<Integer> list) {
        if (x == y)
            return 0;
        if (list.contains(x - 1) && list.contains(x + 1)) {
            return 1 + d(x - 1, x + 1, list);
        }
        return 9000;
    }
}