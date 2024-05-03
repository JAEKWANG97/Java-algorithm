import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class BitmaskCombination {

    private static class Location {
        int x;

        public Location(int x) {
            this.x = x;
        }
    }

    private static class Command {
        int n;
        String direct;

        public Command(int n, String direct) {
            this.n = n;
            this.direct = direct;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static Map<Integer, Integer> map;
    private static List<Command> commands;
    private static Location location;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        commands = new ArrayList<>();
        location = new Location(0);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            commands.add(new Command(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        map.put(0, 1);
    }

    private static void solve() {
        for (Command command : commands) {
            move(command);
        }
    }

    private static void move(Command command) {
        if (command.direct.equals("R")) {
            for (int i = 0; i < command.n; i++) {
                map.put(location.x, map.getOrDefault(location.x, 0) + 1);
                location.x++;
            }
        } else {
            for (int i = 0; i < command.n; i++) {
                map.put(location.x, map.getOrDefault(location.x, 0) + 1);
                location.x--;
            }
        }
    }

    private static void print() {
        int count = 0;
        for (Integer value : map.values()) {
            if (value > 1) {
                count++;
            }
        }
        System.out.println(count);
    }
}