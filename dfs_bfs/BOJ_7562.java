import java.util.*;

class Node {

  private int x, y, cnt;

  public Node(int x, int y, int cnt) {
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getCnt() {
    return this.cnt;
  }
}

class Main {

  public static int T;
  public static int l;
  public static boolean[][] visited;
  public static int targetX, targetY;
  public static int[] dx = {-2, -1, +1, +2, +2, +1, -1, -2};
  public static int[] dy = {-1, -2, -2, -1, +1, +2, +2, +1};
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    T = sc.nextInt();

    while (T-- > 0) {
      l = sc.nextInt();
      visited = new boolean[l][l];

      int startX = sc.nextInt();
      int startY = sc.nextInt();
      targetX = sc.nextInt();
      targetY = sc.nextInt();

      System.out.println(bfs(startX, startY));
    }
  }


  public static int bfs(int x, int y) {
    Queue<Node> q = new LinkedList<>();
    q.offer(new Node(x, y, 0));
    visited[x][y] = true;
    int cnt = 0;

    while (!q.isEmpty()) {
      Node node = q.poll();

      if (node.getX() == targetX && node.getY() == targetY) {
        cnt = node.getCnt();
        break;
      }

      // 8개의 위치 이동
      for (int i = 0; i < 8; i++) {
        int nx = node.getX() + dx[i];
        int ny = node.getY() + dy[i];
        if (nx < 0 || nx >= l || ny < 0 || ny >= l) continue; // 경계 밖
        
        if (visited[nx][ny] == false) { // 방문하지 않았다면
          visited[nx][ny] = true;
          q.offer(new Node(nx, ny, node.getCnt() + 1));
        }
      }
    }

    return cnt;
  }
  
}
