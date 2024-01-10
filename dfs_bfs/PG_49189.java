package programmers;
import java.util.*;

public class PG_49189 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
            System.out.println("result = " + result);
        }
}

class Solution {

    public List<List<Integer>> makeGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        return graph;
    }

    public int solution(int n, int[][] edges) {
        final int INF = 987654321;
        final int start = 0;

        List<List<Integer>> graph = makeGraph(n, edges);
        int[] distance = new int[n];

        Arrays.fill(distance, INF);

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        distance[start] = 0;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph.get(current)) {
                if (distance[next] != INF) {
                    continue;
                }

                distance[next] = distance[current] + 1;
                q.offer(next);
            }
        }

        int max = Arrays.stream(distance).max().getAsInt();
        return (int)Arrays.stream(distance).filter(v -> v == max).count();
    }
}

/* 나의 풀이
class Solution {

    public List<List<Integer>> graph = new ArrayList<>();
    public int[] dist;
    public int max = 0;

    public int solution(int n, int[][] edge) {

        // 최단거리 배열 초기화
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 그래프 정보 입력
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // BFS 탐색
        bfs(1);

        // 개수 카운팅 후 반환
        int answer = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == max) answer++;
        }
        return answer;
    }

    public void bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()) {
            Node node = q.poll();

            // 연결된 노드들 방문
            for (int i = 0; i < graph.get(node.index).size(); i++) {
                int index = graph.get(node.index).get(i);
                int distance = node.distance + 1;

                // 이미 방문한 노드라면 지나가기
                if (dist[index] <= distance) continue;

                // 방문 처리
                q.offer(new Node(index, distance));
                dist[index] = distance;
                max = Math.max(max, distance);
            }
        }

    }

    public class Node {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
} */