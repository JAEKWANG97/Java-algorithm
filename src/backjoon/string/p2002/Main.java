package backjoon.string.p2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < num; i++) {
            String car = br.readLine();
            map.put(car, i);
        }

        for (int i = 0; i < num; i++) {
            String car = br.readLine();
            if(map.get(car) > i){
                for (Entry<String, Integer> entry : map.entrySet()) {
                    if(entry.getValue() < map.get(car)){
                        entry.setValue(entry.getValue() + 1);
                    }
                }
                count += 1;
                map.put(car , i);
            }
        }
        System.out.println(count);
    }
}
