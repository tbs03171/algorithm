import java.util.*;

class Node implements Comparable<Node> {

  private int index;
  private int distance;

  public Node(int index, int distance) {
    this.index = index;
    this.distance = distance;
  }

  public int getIndex() {
    return this.index;
  }

  public int getDistance() {
    return this.distance;
  }

  // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
  @Override
  public int compareTo(Node other) {
    if (this.distance < other.distance) {
      return -1;
    }
    return 1;
  }
}

public class ch9_3 {
  public static int INF = (int) 1e9; // 무한 (10억)
  public static int n, m, c;
  // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
  public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
  // 최단 거리 테이블
  public static int[] d;

  public static void dijkstra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    // 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
    pq.offer(new Node(start, 0));
    d[start] = 0;
    while(!pq.isEmpty()) { // 큐가 비어있지 않다면
      // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
      Node node = pq.poll();
      int dist = node.getDistance(); // 현재 노드까지의 비용
      int now = node.getIndex(); // 현재 노드
      // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
      if (d[now] < dist) continue;
      // 현재 노드와 연결된 다른 인접한 노드들을 확인
      for (int i = 0; i < graph.get(now).size(); i++) {
        int cost = d[now] + graph.get(now).get(i).getDistance();
        // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
        if (cost < d[graph.get(now).get(i).getIndex()]) {
          d[graph.get(now).get(i).getIndex()] = cost;
          pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt(); // 도시의 개수 N
    m = sc.nextInt(); // 통로의 개수 M
    c = sc.nextInt(); // 메시지를 보내고자 하는 도시 C

    // 그래프 초기화
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<Node>());
    }

    // 모든 간선 정보 입력받기
    for (int i = 0; i < m; i++) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      int z = sc.nextInt();
      // x -> y 비용 z
      graph.get(x).add(new Node(y, z));
    }

    // 최단 거리 테이블 무한으로 초기화
    d = new int[n + 1];
    Arrays.fill(d, INF);

    // 다익스트라 수행
    dijkstra(c);

    // 메시지 받는 도시 총 개수, 총 걸리는 시간 계산
    int cnt = 0;
    int time = 0;
    for (int i = 0; i <= n; i++) {
      if (d[i] != INF && i != c) {
        cnt++;
        time = Math.max(time, d[i]);
      }
    }

    // 출력
    System.out.printf("%d %d", cnt, time);
  }
}