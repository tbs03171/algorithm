import java.util.*;

class Node {
  private int x, y;

  public Node(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}

class Main {

  // 테스트 케이스의 개수 T
  public static int t;
  public static int n, m;
  public static int[][] graph;
  public static boolean[][] visited;

  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static void bfs(int x, int y) {
    Queue<Node> q = new LinkedList<Node>();
    q.offer(new Node(x, y));
    visited[x][y] = true;

    while(!q.isEmpty()) {
      Node node = q.poll();

      // 상하좌우 탐색
      for (int i = 0; i < 4; i++) {
        int nx = node.getX() + dx[i];
        int ny = node.getY() + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
          if (graph[nx][ny] == 1 && visited[nx][ny] == false) {
            visited[nx][ny] = true;
            q.offer(new Node(nx, ny));
          }
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    t = sc.nextInt();

    for (int tc = 0; tc < t; tc++) {
      m = sc.nextInt(); // 배추밭의 가로길이 M
      n = sc.nextInt(); // 배추밭의 세로길이 N
      int k = sc.nextInt(); // 배추 위치 개수

      // 그래프 생성
      graph = new int[n][m];
      visited = new boolean[n][m];
      
      // 배추 위치 입력
      for (int i = 0; i < k; i++) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        graph[y][x] = 1;
      }

      // 탐색 시작
      int result = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (graph[i][j] == 1 && visited[i][j] == false) {
            bfs(i, j);
            result++;
          }
        }
      }

      // 결과 출력
      System.out.println(result);
    }
  }
}