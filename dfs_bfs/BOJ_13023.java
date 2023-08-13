import java.util.*;

class Main {

  public static int N, M;
  public static boolean[] visited;
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  public static boolean result = false;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    visited = new boolean[N];

    // 그래프 초기화
    for (int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }

    // 친구 관계 입력
    for (int i = 0; i < M; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    // 백트래킹 탐색
    for (int i = 0; i < N; i++) {
      if (result == false) dfs(i, 0);
    }

    // 결과 출력
    if (result == true) System.out.println(1);
    else System.out.println(0);
  }

  public static void dfs(int start, int depth) {
    
    if (depth == 4) {
      result = true;
      return;
    }

    visited[start] = true;
    for (int next : graph.get(start)) {
      if (visited[next] == false) {
        dfs(next, depth + 1);
      }
    }
    visited[start] = false;
  }
}
