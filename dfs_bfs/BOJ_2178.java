import java.util.*;

class Node {
  public int x, y;

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

  public static int n, m;

  public static int[][] graph = new int[101][101];
  public static boolean[][] visited = new boolean[101][101];

  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static void bfs(int x, int y) {
    Queue<Node> q = new LinkedList<Node>();
    q.offer(new Node(x, y));
    visited[x][y] = true;

    while (!q.isEmpty()) {
      Node node = q.poll();

      // 상하좌우 탐색
      for (int i = 0; i < 4; i++) {
        int nx = node.getX() + dx[i];
        int ny = node.getY() + dy[i];
        if (0 <= nx && nx <= n - 1 && 0 <= ny && ny <= m - 1) { // 경계 내에 있으면
          if (graph[nx][ny] == 1 && visited[nx][ny] == false) { // 아직 방문하지 않았다면
            graph[nx][ny] = graph[node.getX()][node.getY()] + 1; // 거리 저장
            visited[nx][ny] = true; // 방문 처리
            q.offer(new Node(nx, ny)); // 큐에 넣기
          }
        } 
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    // 그래프 입력
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      String s = sc.nextLine();
      for (int j = 0; j < m; j++) {
        graph[i][j] = s.charAt(j) - '0';
      }
    }

    // BFS 탐색
    bfs(0, 0);

    //     for (int i = 0; i < n; i++) {
    //   for (int j = 0; j < m; j++) {
    //     System.out.print(graph[i][j] + " ");
    //   }
    //   System.out.println();
    // }

    // 결과 출력
    System.out.println(graph[n - 1][m - 1]);
}
}
