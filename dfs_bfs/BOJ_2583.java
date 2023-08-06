import java.util.*;

class Main {

  public static int M, N, K;
  public static boolean[][] visited;
  public static ArrayList<Integer> list = new ArrayList<>();
  public static int count;
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    M = sc.nextInt();
    N = sc.nextInt();
    K = sc.nextInt();
    visited = new boolean[N][M];

    // 그래프 방문 불가 처리
    while (K-- > 0) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();
      for (int i = x1; i < x2; i++) {
        for (int j = y1; j < y2; j++) {
          visited[i][j] = true;
        }
      }
    }

    // DFS 탐색
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (visited[i][j] == false) {
          count = 0;
          dfs(i, j);
          list.add(count);
        }
      }
    }

    // 정렬
    Collections.sort(list);

    // 출력
    System.out.println(list.size());
    for (int value : list) {
      System.out.print(value + " ");
    }
  }


  public static void dfs(int x, int y) {
    visited[x][y] = true;
    count++;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
      if (visited[nx][ny] == false) {
        dfs(nx, ny);
      }
    }
  }
}
