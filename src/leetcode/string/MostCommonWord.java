package leetcode.string;


import java.util.HashMap;
import java.util.HashSet;

import java.util.Map.Entry;

public class MostCommonWord {
    public static void main(String[] args) {
//        paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};

        paragraph = paragraph.toLowerCase().replaceAll("[^a-zA-Z]", " ");
        HashMap<String, Integer> map = new HashMap<>();
        String[] str_list = paragraph.split(" ");
        HashSet<String> bannedSet = new HashSet<>();
        for (String word : banned) {
            bannedSet.add(word.toLowerCase());
        }
        for (String s : str_list) {
            if (!bannedSet.contains(s) && !s.equals(" ")) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        int max_count = 0;
        String max_String = "";
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max_count) {
                max_count = entry.getValue();
                max_String = entry.getKey();
            }
        }

        System.out.println(max_String);


    }
}
