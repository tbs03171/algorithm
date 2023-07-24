import java.util.*;

class Main {

  public static int N;
  public static boolean[] visited;
  public static int[] parent;
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    visited = new boolean[N + 1];
    parent = new int[N + 1];

    // 그래프 생성
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 그래프 정보 입력
    for (int i = 0; i < N - 1; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    // dfs 탐색
    dfs(1);

    // 결과 출력
    for (int i = 2; i <= N; i++) {
      System.out.println(parent[i]);
    }
  }

  public static void dfs(int start) {
    visited[start] = true;

    for (int val : graph.get(start)) {
      if (visited[val] == false) {
        parent[val] = start;
        dfs(val);
      }
    }
  }
}

/* BFS 풀이
import java.util.*;

class Main {

  public static int N;
  public static boolean[] visited;
  public static int[] parent;
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    visited = new boolean[N + 1];
    parent = new int[N + 1];

    // 그래프 생성
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 그래프 정보 입력
    for (int i = 0; i < N - 1; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    // bfs 탐색
    bfs(1);

    // 결과 출력
    for (int i = 2; i <= N; i++) {
      System.out.println(parent[i]);
    }
  }

  public static void bfs(int start) {

    Queue<Integer> q = new LinkedList<Integer>();
    q.offer(start);

    while(!q.isEmpty()) {
      int now = q.poll();

      for (int val : graph.get(now)) {
        if (visited[val] == false) {
          q.offer(val);
          parent[val] = now;
          visited[val] = true;
        }
      }
    }
  }
}
*/