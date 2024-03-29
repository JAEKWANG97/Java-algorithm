package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReorderDataInLogFiles {
    public static void main(String[] args) {
        List<String> letterList = new ArrayList<>();
        List<String> digitList = new ArrayList<>();
        String[] logs = new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};

        for (String log : logs) {
            String identifier = log.split(" ")[0];
            if(identifier.contains("dig")){
                digitList.add(log);
            }else{
                letterList.add(log);
            }
        }

        Collections.sort(letterList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] s1x = s1.split(" ", 2);
                String[] s2x = s2.split(" ",2);

                int compared = s1x[1].compareTo(s2x[1]);
                if(compared == 0){
                    return s1x[0].compareTo(s2x[0]);
                }
                return compared;
            }
        });
        Collections.sort(digitList);

        letterList.addAll(digitList);



    }
}
