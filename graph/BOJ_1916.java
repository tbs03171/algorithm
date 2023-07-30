import java.util.*;

class Node implements Comparable<Node> {
  private int to;
  private long cost;

  public Node(int to, long cost) {
    this.to = to;
    this.cost = cost;
  }

  public int getTo() {
    return this.to;
  }

  public long getCost() {
    return this.cost;
  }

  @Override
  public int compareTo(Node other) {
    return Long.compare(this.cost, other.cost);
  }
}

class Main {

  public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
  public static int N, M;
  public static long[] result;
  public static int INF = (int)1e9;

  public static void dijkstra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    while(!pq.isEmpty()) {
      Node now = pq.poll(); // 우선순위 큐에서 하나 꺼내기
      if (result[now.getTo()] < now.getCost()) continue; // 이미 처리된 노드
      result[now.getTo()] = now.getCost();

      for (Node node : graph.get(now.getTo())) { // 꺼낸 노드와 연결된 노드들과의 거리 업뎃
        long cost = now.getCost() + node.getCost();
        if (result[node.getTo()] > cost) {
          pq.offer(new Node(node.getTo(), cost));
          result[node.getTo()] = cost;
        }
      }
    }
  }
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();

    // 그래프 초기화
    result = new long[N + 1];
    Arrays.fill(result, INF);
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<Node>());
    }

    // 그래프 정보 입력
    for (int i = 0; i < M; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      long cost = sc.nextLong();
      graph.get(from).add(new Node(to, cost));
    }

    // 출발 도시, 도착 도시 입력
    int from = sc.nextInt();
    int to = sc.nextInt();
    result[from] = 0;

    // 다익스트라
    dijkstra(from);

    // 결과 출력
    System.out.println(result[to]);
  }

}