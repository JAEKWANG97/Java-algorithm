import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    // 그래프를 나타내는 클래스
    static class Graph {
        int vertices; // 정점의 수
        int[][] matrix; // 인접 행렬을 사용한 그래프 표현

        public Graph(int vertex) {
            this.vertices = vertex;
            matrix = new int[vertex][vertex];
        }

        // 그래프에 간선 추가
        public void addEdge(int source, int destination, int weight) {
            matrix[source][destination] = weight;
            // 무방향 그래프인 경우 아래 코드 주석을 해제합니다.
            // matrix[destination][source] = weight;
        }

        // 다익스트라 알고리즘 구현
        public void dijkstra(int startVertex) {
            boolean[] visited = new boolean[vertices]; // 정점을 방문했는지 체크
            int[] distance = new int[vertices]; //각 정점까지의 최단 거리를 저장
            Arrays.fill(distance, Integer.MAX_VALUE); // 각 거리를 inf로 채움
            distance[startVertex] = 0; // start 정점은 0으로 초기화
            PriorityQueue<Integer> pq = new PriorityQueue<>(vertices,
                    (a, b) -> distance[a] - distance[b]); // distance 로 pq 설정
            pq.add(startVertex); // pq에 정점 넣기. --> distance = 0;

            while (!pq.isEmpty()) {
                int u = pq.poll(); // 현재 정점
                // 현재 정점을 방문 처리
                visited[u] = true; // 방문처리
                // 모든 인접 정점을 순회
                for (int v = 0; v < vertices; v++) { // vertices 는 정점의 수
                    if (matrix[u][v] > 0 && !visited[v]) { // matrix가 0보다 크다?
                        int newDist = distance[u] + matrix[u][v]; //
                        if (newDist < distance[v]) {
                            distance[v] = newDist;
                            pq.add(v);
                        }
                    }
                }
            }

            // 결과 출력
            printDijkstra(startVertex, distance);
        }

        // 다익스트라 알고리즘의 결과를 출력하는 함수
        public void printDijkstra(int startVertex, int[] distance) {
            System.out.println("다익스트라 알고리즘 결과 (시작 정점: " + startVertex + ")");
            for (int i = 0; i < vertices; i++) {
                System.out.println("정점 " + startVertex + "에서 정점 " + i + "까지의 최단 거리: " + distance[i]);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph g = new Graph(vertices);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 5, 6);
        g.dijkstra(0);
    }
}
