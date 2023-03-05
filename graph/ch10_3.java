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

  // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
  @Override
  public int compareTo(Edge other) {
    if (this.distance < other.distance)
      return -1;
    return 1;
  }
}

public class ch10_3 {

  // 노드 개수(V)와 간선 개수(E)
  public static int v, e;
  public static int[] parent = new int[100001];
  // 모든 간선을 담을 리스트와, 최종 비용을 담을 변수
  public static ArrayList<Edge> edges = new ArrayList<>();
  public static int result = 0;

  // 특정 원소가 속한 집합을 찾기
  public static int findParent(int x) {
    // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if (parent[x] == x)
      return x;
    return parent[x] = findParent(parent[x]);
  }

  // 두 원소가 속한 집합을 합치기
  public static void unionParent(int a, int b) {
    a = findParent(a);
    b = findParent(b);
    if (a < b)
      parent[b] = a;
    else
      parent[a] = b;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 집의 개수 N 입력
    v = sc.nextInt();
    // 길 개수 M 입력
    e = sc.nextInt();

    // 부모 배열 자기 자신으로 초기화
    for (int i = 1; i <= v; i++) {
      parent[i] = i;
    }

    // 간선 정보 입력
    for (int i = 0; i < e; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      int cost = sc.nextInt();
      edges.add(new Edge(cost, a, b));
    }

    // 간선을 비용순으로 정렬
    Collections.sort(edges);

    // 간선을 하나씩 확인하며
    int cnt = 0;
    for (int i = 0; i < edges.size(); i++) {
      int cost = edges.get(i).getDistance();
      int a = edges.get(i).getNodeA();
      int b = edges.get(i).getNodeB();
      if (findParent(a) != findParent(b)) {
        unionParent(a, b);
        result += cost;
        cnt++;
        if (cnt == v - 2)
          break; // 마지막 하나는 연결 X (마을 2개로 분리 위해)
      }
    }

    System.out.println(result);
  }
}