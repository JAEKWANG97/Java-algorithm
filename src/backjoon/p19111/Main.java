package backjoon.p19111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            List<Integer> numbers = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int item = Integer.parseInt(st.nextToken());
                if (item != 0) {
                    numbers.add(item);
                }
                else{
                    return;
                }
            }
            Collections.sort(numbers);
            int temp = 0;
            for (int i = 0; i < numbers.size() - 1; i++) {
                temp += numbers.get(i) * numbers.get(i);
            }
            if (temp == numbers.get(numbers.size()-1) * numbers.get(numbers.size()-1)) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
}
