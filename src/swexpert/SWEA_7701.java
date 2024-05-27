package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_7701 {
    static class Word implements Comparable<Word> {
        String word;
        int length;

        public Word(String word, int length) {
            this.word = word;
            this.length = length;
        }

        @Override
        public int compareTo(Word o) {
            int value = Integer.compare(this.length, o.length);
            if (value == 0) {
                return this.word.compareTo(o.word);
            }
            return value;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            List<Word> list = new ArrayList<>();
            Set<String> set = new HashSet<>();
            int N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
                if (!set.contains(arr[i])) {
                    list.add(new Word(arr[i], arr[i].length()));
                    set.add(arr[i]);
                }
            }
            Collections.sort(list);
            System.out.println("#" + tc);
            for (Word word : list) {
                System.out.println(word.word);
            }
        }
    }
}
