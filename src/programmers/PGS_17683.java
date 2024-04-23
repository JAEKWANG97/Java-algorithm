import java.util.ArrayList;
import java.util.List;

class PGS_17683 {
    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = { "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" };
        Solution s = new Solution();
        System.out.println(s.solution(m, musicinfos));
    }
}

class Solution {
    class Music implements Comparable<Music> {
        String title;
        int playTime;
        String fullMelody;

        Music(String title, int playTime, String fullMelody) {
            this.title = title;
            this.playTime = playTime;
            this.fullMelody = fullMelody;
        }

        @Override
        public int compareTo(Music other) {
            if (this.playTime == other.playTime) {
                return 0; // Earlier in the list is earlier added
            }
            return other.playTime - this.playTime; // Descending order
        }
    }

    public String solution(String m, String[] musicinfos) {
        List<Music> candidateMusics = new ArrayList<>();
        m = convertMelody(m);

        for (String info : musicinfos) {
            String[] details = info.split(",");
            String title = details[2];
            String melody = convertMelody(details[3]);
            int playTime = calculatePlayTime(details[0], details[1]);
            String fullMelody = generateFullMelody(melody, playTime);

            if (fullMelody.contains(m)) {
                candidateMusics.add(new Music(title, playTime, fullMelody));
            }
        }

        if (candidateMusics.isEmpty()) {
            return "(None)";
        }

        // Sorting to find the longest playtime music
        candidateMusics.sort(null);
        return candidateMusics.get(0).title;
    }

    private String convertMelody(String melody) {
        return melody.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a")
                .replaceAll("B#", "b");
    }

    private int calculatePlayTime(String start, String end) {
        String[] startParts = start.split(":");
        String[] endParts = end.split(":");
        int startMinutes = Integer.parseInt(startParts[0]) * 60 + Integer.parseInt(startParts[1]);
        int endMinutes = Integer.parseInt(endParts[0]) * 60 + Integer.parseInt(endParts[1]);
        return endMinutes - startMinutes;
    }

    private String generateFullMelody(String melody, int playTime) {
        StringBuilder fullMelody = new StringBuilder();
        for (int i = 0; i < playTime; i++) {
            fullMelody.append(melody.charAt(i % melody.length()));
        }
        return fullMelody.toString();
    }
}
