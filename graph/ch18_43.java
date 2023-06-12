import java.util.*;

class Edge implements Comparable<Edge> {

  private int distance;
  private int nodeA;
  private int nodeB;

  public Edge(int distance, int nodeA, int nodeB) {
    this.distance = distance;
    this.nodeA = nodeA;
    this.nodeB = nodeB;
  }

  public int getDistance() {
    return this.distance;
  }

  public int getNodeA() {
    return this.nodeA;
  }

  public int getNodeB() {
    return this.nodeB;
  }

  @Override
  public int compareTo(Edge other) {
    if (this.distance < other.distance) {
      return -1;
    }
    return 1;
  }
}

public class Main {

  // 노드의 개수와 간선의 개수
  public static int n, m;
  public static int[] parent = new int[200001]; // 부모 테이블 초기화하기
  // 모든 간선을 담을 리스트와, 최종 비용을 담을 변수
  public static ArrayList<Edge> edges = new ArrayList<>();
  public static int result = 0;

  // 특정 원소가 속한 집합을 찾기
  public static int findParent(int x) {
    // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if (x == parent[x]) return x;
    return parent[x] = findParent(parent[x]);
  }

  // 두 원소가 속한 집합을 합치기
  public static void unionParent(int a, int b) {
    a = findParent(a);
    b = findParent(b);
    if (a < b) parent[b] = a;
    else parent[a] = b;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    // 부모 테이블상에서, 부모를 자기 자신으로 초기화
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
    }

    // 모든 간선에 대한 정보를 입력 받기
    for (int i = 0; i < m; i++) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      int z = sc.nextInt();
      edges.add(new Edge(z, x, y));
    }

    // 간선을 비용순으로 정렬
    Collections.sort(edges);
    int total = 0; // 전체 가로등 비용

    // 간선을 하나씩 확인하며
    for (int i = 0; i < edges.size(); i++) {
      int cost = edges.get(i).getDistance();
      int a = edges.get(i).getNodeA();
      int b = edges.get(i).getNodeB();
      total += cost;
      // 사이클이 발생하지 않는 경우에만 집합에 포함
      if (findParent(a) != findParent(b)) {
        unionParent(a, b);
        result += cost;
      }
    }

    System.out.println(total - result);
  }
}

/* 나의 코드
import java.util.*;

class Edge implements Comparable<Edge> {
  private int x;
  private int y;
  private int cost;

  public Edge(int x, int y, int cost) {
    this.x = x;
    this.y = y;
    this.cost = cost;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getCost() {
    return this.cost;
  }

  @Override
  public int compareTo(Edge other) {
    if (this.cost < other.cost) return -1;
    return 1;
  }
}

class Main {
  // 집의 수 N, 도로의 수 M
  public static int n, m;
  public static PriorityQueue<Edge> pq = new PriorityQueue<>();
  public static ArrayList<Edge> result = new ArrayList<>();
  public static int[] parent = new int[200001];

  public static int findParent(int x) {
    if (parent[x] == x) return x;
    return parent[x] = findParent(parent[x]);
  }

  public static void unionParent(int x, int y) {
    x = findParent(x);
    y = findParent(y);
    if (x < y) parent[y] = x;
    else parent[x] = y;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // N, M 입력
    n = sc.nextInt();
    m = sc.nextInt();

    // parent 초기화
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }

    // 각 도로 정보 X, Y, Z 입력
    int beforeSum = 0;
    for (int i = 0; i < m; i++) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      int z = sc.nextInt();
      beforeSum = beforeSum + z;
      pq.offer(new Edge(x, y, z));
    }

    // 최소 신장 트리 만들기
    while(result.size() < n - 1) {
      Edge edge = pq.poll();
      int x = findParent(edge.getX());
      int y = findParent(edge.getY());
      if (x == y) continue;
      result.add(edge);
      unionParent(edge.getX(), edge.getY());
    }

    // 총 합 계산
    int afterSum = 0;
    for (int i = 0; i < result.size(); i++) {
      afterSum = afterSum + result.get(i).getCost();
    }

    // 결과 출력
    System.out.println(beforeSum - afterSum);
  }
}
*/