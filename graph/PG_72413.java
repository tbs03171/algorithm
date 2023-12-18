package programmers;
import java.util.*;

public class PG_72413 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
            System.out.println("result = " + result);
        }
}

/* 다익스트라 */
class Edge implements Comparable<Edge> {

    int index;
    int cost;

    Edge (int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

class Solution {

    static final int MAX = 20000001; // 200 * 100000 + 1
    static ArrayList<ArrayList<Edge>> graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {

        int answer = Integer.MAX_VALUE;

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 저장
        for (int[] i : fares) {
            graph.get(i[0]).add(new Edge(i[1], i[2]));
            graph.get(i[1]).add(new Edge(i[0], i[2]));
        }

        // 다익스트라
        int[] startA = new int[n + 1]; // A에서 모든 곳 까지의 최소비용
        int[] startB = new int[n + 1]; // B에서 모든 곳 까지의 최소비용
        int[] start = new int[n + 1]; // 시작점에서 모든 곳 까지의 최소비용

        Arrays.fill(startA, MAX);
        Arrays.fill(startB, MAX);
        Arrays.fill(start, MAX);

        startA = dijkstra(a, startA);
        startB = dijkstra(b, startB);
        start = dijkstra(s, start);

        // 결과 탐색 후 반환
        for (int i = 1; i <= n; i++)
            answer = Math.min(answer, startA[i] + startB[i] + start[i]);
        return answer;
    }

    static int[] dijkstra (int start, int[] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nIndex = now.index;
            int nCost = now.cost;

            if (nCost > costs[nIndex]) continue; // 이미 처리된 노드

            ArrayList<Edge> edges = graph.get(nIndex);
            for (Edge edge : edges) {
                int cost = costs[nIndex] + edge.cost;

                if (cost < costs[edge.index]) {
                    costs[edge.index] = cost;
                    pq.offer(new Edge(edge.index, cost));
                }
            }
        }

        return costs;
    }
}

/* 플로이드 워셜
class Solution {

    static final int MAX = 20000001;
    public int solution(int n, int s, int a, int b, int[][] fares) {

        // 그래프 초기화
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 0;
        }

        // 간선 정보 저장
        for (int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];

            dp[start][end] = cost;
            dp[end][start] = cost;
        }

        // 플로이드 워셜
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        // 합승하는 경우 고려
        // 경유지점을 끼는 경우
        // s -> t + t -> a + t -> b
        int answer = dp[s][a] + dp[s][b];
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
        }
        return answer;
    }
} */

/* 나의 풀이 (플로이드 워셜)
class Solution {

    int[][] graph;
    public int solution(int n, int s, int a, int b, int[][] fares) {

        // 그래프 초기화
        graph = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }

        // 플로이드 워셜로 그래프 구성
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE)
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        // 합승을 멈출 위치에 따라 계산
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int result = graph[s][i] + graph[i][a] + graph[i][b]; // 합승 (s ~ i) + 내려서 택시(i ~ a) + (i ~ b)
            answer = Math.min(answer, result);
        }

        return answer;
    }
} */