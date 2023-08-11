import java.util.*;

class Node {

  int x, y, cnt;

  Node (int x, int y, int cnt) {
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }
}

class Main {

  public static int N, M;
  public static char[][] graph;
  public static boolean[][] visited;
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};
  public static int result = 0;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt(); // 세로
    M = sc.nextInt(); // 가로
    graph = new char[N][M];
    visited = new boolean[N][M];

    // 그래프 정보 입력
    sc.nextLine();
    for (int i = 0; i < N; i++) {
      String s = sc.nextLine();
      for (int j = 0; j < M; j++) {
        graph[i][j] = s.charAt(j);
      }
    }

    // 그래프 탐색
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (graph[i][j] == 'L') {
          visited = new boolean[N][M];
          result = Math.max(result, bfs(i, j));
        }
      }
    }

    // 결과 출력
    System.out.println(result);
    
  }


  public static int bfs(int startX, int startY) {
    visited[startX][startY] = true;
    Queue<Node> q = new LinkedList<>();
    q.offer(new Node(startX, startY, 0));
    int max = 0;

    while(!q.isEmpty()) {
      Node node = q.poll();
      max = Math.max(max, node.cnt);

      for (int i = 0; i < 4; i++) {
        int nx = node.x + dx[i];
        int ny = node.y + dy[i];
        if (0 > nx || nx >= N || ny < 0 || ny >= M) continue;
        if (visited[nx][ny] == false && graph[nx][ny] == 'L') {
          visited[nx][ny] = true;
          q.offer(new Node(nx, ny, node.cnt + 1));
        }
      }
    }

    return max;
  }
}
