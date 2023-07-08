import java.util.*;

class Main {

  public static int n, m, v;

  public static boolean[] visited = new boolean[1001];

  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  public static void dfs(int start) {
    visited[start] = true;
    System.out.print(start + " ");

    ArrayList<Integer> neighbors = graph.get(start);
    for (Integer value : neighbors) {
      if (!visited[value]) dfs(value);
    }
  }

  public static void bfs(int start) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.offer(start);
    visited[start] = true;

    while(!q.isEmpty()) {
      int now = q.poll();
      System.out.print(now + " ");
      
      ArrayList<Integer> neighbors = graph.get(now);
      for (Integer value : neighbors) {
        if (!visited[value]) {
          visited[value] = true;
          q.offer(value);
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    v = sc.nextInt();

    // 그래프 초기화
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 간선 정보 입력
    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    // 정렬
    for (int i = 0; i <= n; i++) {
      Collections.sort(graph.get(i));
    }
    

    dfs(v);

    // 초기화
    Arrays.fill(visited, false);
    System.out.println();

    bfs(v);
  }
}
