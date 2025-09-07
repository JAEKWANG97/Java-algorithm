import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> map = new HashMap<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            int num =sc.nextInt();    
            map.putIfAbsent(num, 0);
            map.put(num, map.get(num) + 1);
        }
        int[] queries = new int[m];
        for (int i = 0; i < m; i++) {
            queries[i] = sc.nextInt();
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < m ; i++){
            sb.append(map.getOrDefault(queries[i], 0)).append(" ");
        }

        System.out.print(sb);
        
    }
}