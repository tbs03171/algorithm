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

  // 상자의 가로 칸의 수 M, 세로 칸의 수 N
  public static int n, m;

  public static int[][] graph = new int[1001][1001];
  public static boolean[][] visited = new boolean[1001][1001];

  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static Queue<Node> q = new LinkedList<Node>();
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // M, N 입력
    m = sc.nextInt();
    n = sc.nextInt();

    // 그래프 모양 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        graph[i][j] = sc.nextInt();
      }
    }
    
    // 익은 토마토들 큐에 넣기
    boolean already = true; // 이미 다 익었는가?    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (graph[i][j] == 0) already = false;
        if (graph[i][j] == 1 && visited[i][j] == false) {
          q.offer(new Node(i, j));
          visited[i][j] = true;
        }
      }
    }

    // bfs 탐색
    while (!q.isEmpty()) {
      Node node = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = node.getX() + dx[i];
        int ny = node.getY() + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < m) { // 경계 내부에 있다면
          if (visited[nx][ny] == false && graph[nx][ny] == 0) {
            graph[nx][ny] = graph[node.getX()][node.getY()] + 1;
            visited[nx][ny] = true;
            q.offer(new Node(nx, ny));
          }
        }
      }
    }

    
    // 토마토가 얼마나 익었는지 결과 확인
    boolean all = true; // 모두 다 익었는가?
    int max = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (graph[i][j] == 0) {
          all = false;
          break;
        }
        max = Math.max(max, graph[i][j]);
      }
    }

    // 이미 다 익음 : 0 출력
    // 모두 익지 못함 : -1 출력
    // 정상 : 그래프에서 제일 큰 수 출력
    if (already == true) {
      System.out.println(0);
    } else if (all == false) {
      System.out.println(-1);
    } else {
      System.out.println(max - 1);
    }
  }
}
