import java.util.*;

class Main {

  // 컴퓨터의 수 v, 쌍의 수 e
  public static int v, e;

  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  public static boolean[] visited = new boolean[101];

  public static int result = -1;

  public static void dfs(int start) {
    visited[start] = true;
    result++;

    for (int i = 0; i < graph.get(start).size(); i++) {
      if (!visited[graph.get(start).get(i)]) {
        dfs(graph.get(start).get(i));
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    v = sc.nextInt();
    e = sc.nextInt();

    // 그래프 초기화
    for (int i = 0; i <= v; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 그래프 생성
    for (int i = 0; i < e; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    // 그래프 탐색
    dfs(1);

    // 결과 출력
    System.out.println(result);
  }
}
