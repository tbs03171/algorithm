import java.util.*;

class Solution {
    public static List<List<Node>> graph;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        // 그래프 만들기
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 입구일 경우 다른 곳으로만 갈 수 있는 단방향
        // 산봉우리일 경우 특정 한 곳에서 산봉우리로 가는 단방향
        for (int[] path : paths) {
            int s = path[0];
            int e = path[1];
            int cost = path[2];

            if (isGate(s, gates) || isSummit(e, summits)) {
                graph.get(s).add(new Node(e, cost));
            }
            else if (isGate(e, gates) || isSummit(s, summits)) {
                graph.get(e).add(new Node(s, cost));
            }
            else {
                graph.get(s).add(new Node(e, cost)); // 일반 등산로 양방향
                graph.get(e).add(new Node(s, cost));
            }
        }

        answer = dijkstra(n, gates, summits);

        return answer;
    }

    public int[] dijkstra(int n, int[] gates, int[] summits) {
        int[] intensity = new int[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0; // 출입구
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 이미 처리됨
            if (intensity[node.v] < node.cost) continue;

            // intensity 갱신
            for (Node neighbor : graph.get(node.v)) {
                int cost = Math.max(intensity[node.v], neighbor.cost);
                if (intensity[neighbor.v] > cost) {
                    intensity[neighbor.v] = cost;
                    pq.offer(new Node(neighbor.v, cost));
                }
            }
        }

        // intensity가 최소가 되는 등산코스에 포함된 산봉우리 번호와 intensity의 최솟값
        int sv = 0; // 산봉우리 번호
        int smin = Integer.MAX_VALUE; // intensity 최솟값

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < smin) {
                smin = intensity[summit];
                sv = summit;
            }
        }

        int[] ans = {sv, smin};
        return ans;
    }

    public boolean isGate (int v, int[] gates) {
        for (int gate : gates) {
            if (v == gate) return true;
        }

        return false;
    }

    public boolean isSummit (int v, int[] summits) {
        for (int summit : summits) {
            if (v == summit) return true;
        }
        return false;
    }

    static private class Node implements Comparable<Node> {
        int v, cost;

        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cost < other.cost) return -1;
            return 1;
        }
    }
}

/* 나의 풀이
class Solution {

    public class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            if (this.distance < other.distance) return -1;
            return 1;
        }
    }

    public ArrayList<ArrayList<Node>> graph = new ArrayList<>(); // 그래프
    public boolean[] is_shimtu; // 쉼터인지 저장
    public boolean[] is_summit; // 산봉우리인지 저장
    public int[] intensityArr; // 각 지점까지의 intensity 저장
    public int resultSummit = 0;
    public int resultIntensity = Integer.MAX_VALUE;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }

        // intensity 배열 초기화
        intensityArr = new int[n + 1];

        // 쉼터 초기화 (출입구, 산봉우리 제외)
        is_shimtu = new boolean[n + 1];
        Arrays.fill(is_shimtu, true);
        for (int num : gates) is_shimtu[num] = false;
        for (int num : summits) is_shimtu[num] = false;

        // 산봉우리 초기화
        is_summit = new boolean[n + 1];
        for (int num : summits) is_summit[num] = true;

        // paths를 그래프 형태로 변환
        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int distance = path[2];
            graph.get(a).add(new Node(b, distance));
            graph.get(b).add(new Node(a, distance));
        }

        // 모든 출입구에 대해 dijkstra 수행
        for (int gate : gates) {
            dijkstra(gate);
        }

        // 결과 반환
        int[] answer = {resultSummit, resultIntensity};
        return answer;
    }

    // 한 정점에서 모든 정점까지의 intensity
    public void dijkstra(int gate) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(gate, 0));

        Arrays.fill(intensityArr, Integer.MAX_VALUE);
        intensityArr[gate] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // resultIntensity 이상이면 탐색 의미 X (이걸 해야 모든 테스트 케이스 통과함)
            if (node.distance > resultIntensity) return;

            // 이미 처리된 적 있다면 넘어가기
            if (intensityArr[node.index] < node.distance) continue;

            // 이 노드를 거쳐서 다른 곳으로 가는 비용 갱신
            for (Node neighbor : graph.get(node.index)) { // 연결된 곳들 중에
                // 산봉우리라면
                if (is_summit[neighbor.index]) {
                    int cost = Math.max(node.distance, neighbor.distance);
                    if (cost < intensityArr[neighbor.index]) intensityArr[neighbor.index] = cost;

                    // 최종 결과 갱신
                    if (intensityArr[neighbor.index] < resultIntensity) {
                        resultIntensity = intensityArr[neighbor.index];
                        resultSummit = neighbor.index;
                    } else if (intensityArr[neighbor.index] == resultIntensity) {
                        if (resultSummit > neighbor.index) resultSummit = neighbor.index;
                    }
                    continue;
                }

                // 쉼터이면
                if (is_shimtu[neighbor.index]) {
                    int cost = Math.max(node.distance, neighbor.distance);
                    if (cost < intensityArr[neighbor.index]) {
                        intensityArr[neighbor.index] = cost;
                        pq.offer(new Node(neighbor.index, cost));
                    }
                }
            }
        }        
    }
} */