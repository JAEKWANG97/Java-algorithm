import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static int[] compress(String msg) {

        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            dictionary.put("" + (char) ('A' + i), i + 1);
        }

        List<Integer> result = new ArrayList<>();
        String w = "";
        int dictSize = 27;

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            String wc = w + c;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        // Output the last sequence
        if (!w.isEmpty()) {
            result.add(dictionary.get(w));
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
