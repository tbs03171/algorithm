package programmers;
import java.util.*;

public class PG_132266 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int[] result = solution.solution(5, new int[][]{{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}, new int[]{1, 3, 5}, 5);
            for (int i : result) {
                System.out.print(i + " ");
            }
        }
}

/* 다익스트라 */
class Solution {

    public List<List<Integer>> graph;
    int[] dist;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        int[] answer = {};
        answer = new int[sources.length];

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 정보 저장
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        // 다익스트라
        dijkstra(n, destination);

        // sources => 도착지점
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] < Integer.MAX_VALUE) answer[i] = dist[sources[i]];
            else answer[i] = -1;
        }

        return answer;
    }

    public void dijkstra(int n, int destination) {

        dist = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;
        q.add(destination);

        while(!q.isEmpty()) {
            int s = q.poll();

            for (int i = 0; i < graph.get(s).size(); i++) {
                int next = graph.get(s).get(i);
                if (dist[s] + 1 < dist[next]) {
                    dist[next] = dist[s] + 1;
                    q.add(next);
                }
            }
        }
    }
}

/* 나의 풀이
class Solution {

    public List<List<Integer>> graph = new ArrayList<>();
    public int MAX_VALUE = 100001;

    public class Node implements Comparable<Node> {
        int x;
        int distance;

        public Node(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

//         n : 총 지역 수
//         roads : 두 지역 왕복할 수 있는 길 정보
//         sources : 각 부대원이 위치한 지역
//         destination : 강철부대의 지역

//         return 각 부대원이 강철부대로 복귀할 수 있는 최단시간 (불가능한 경우 -1)

//         **
//         n : 3 ~ 10만
//         roads : 2 ~ 50만
//         sources : ~500


//         각 부대원 다익스트라..
//         1. 그래프 생성
//         2. 목적지부터 모든 곳까지의 최단거리 다익스트라
//         3. 각 부대원 위치별 최단거리 저장

        // 초기화
        int[] answer = new int[sources.length];
        int[] distance = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 그래프 생성
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        // destination 에서 모든 곳까지 최단거리 다익스트라
        dijkstra(distance, destination, n);

        // 모든 부대원 정보 저장
        for (int i = 0; i < answer.length; i++) {
            if (distance[sources[i]] == MAX_VALUE) answer[i] = -1;
            else answer[i] = distance[sources[i]];
        }

        return answer;
    }

    public void dijkstra(int[] distance, int start, int n) {
        Arrays.fill(distance, MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.distance > distance[node.x]) continue;

            for (int i = 0; i < graph.get(node.x).size(); i++) {
                int neighbor = graph.get(node.x).get(i);
                if (distance[neighbor] > node.distance + 1) {
                    distance[neighbor] = node.distance + 1;
                    pq.offer(new Node(neighbor, node.distance + 1));
                }
            }
        }
    }
} */