import java.util.*;

class Main {

  public static int n, m;
  public static boolean[] visited = new boolean[1001];
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  public static void dfs(int start) {
    visited[start] = true;

    for (int i = 0; i < graph.get(start).size(); i++) {
      if (visited[graph.get(start).get(i)] == false) dfs(graph.get(start).get(i));
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 정점의 개수 N, 간선의 개수 M
    n = sc.nextInt();
    m = sc.nextInt();

    // 그래프 초기화
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 그래프 생성
    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      graph.get(u).add(v);
      graph.get(v).add(u);
    }

    // 그래프 탐색
    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (visited[i] == false) {
        dfs(i);
        count++;
      }
    }

    System.out.println(count);
  }
}
