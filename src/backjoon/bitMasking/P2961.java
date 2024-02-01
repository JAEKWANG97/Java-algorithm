package backjoon.bitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2961 {
    public static void main(String[] args) throws IOException {
//        1
//3 10
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[] sourTaste = new int[n];
        int[] bitter = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            sourTaste[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        int minTaste = Integer.MAX_VALUE;
        for(int i = 0 ; i < (1<<n); i++){
            int multiSum = 1;
            int addSum = 0;
            for(int j = 0 ; j < n ; j++){
                if((i & (1<<j)) != 0){
                    multiSum *= sourTaste[j];
                    addSum += bitter[j];
                    minTaste = Math.min(minTaste, Math.abs(addSum - multiSum));

                }
            }

        }
        System.out.println(minTaste);
    }
}
