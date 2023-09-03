import java.util.*;

class Point {
  int l, r, c, count;

  Point (int l, int r, int c, int count) {
    this.l = l;
    this.r = r;
    this.c = c;
    this.count = count;
  }
}

class Main {

  public static int L, R, C;
  public static char[][][] graph;
  public static boolean[][][] visited;
  public static int startL, startR, startC;
  public static int endL, endR, endC;
  public static int[] dL = {0, 0, 0, 0, +1, -1}; // 동, 서, 남, 북, 상, 하
  public static int[] dR = {-1, +1, 0, 0, 0, 0};
  public static int[] dC = {0, 0, -1, +1, 0, 0};
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    while (true) {
      L = sc.nextInt();
      R = sc.nextInt();
      C = sc.nextInt();
      if (L == 0 && R == 0 && C == 0) break;
      graph = new char[L][R][C];
      visited = new boolean[L][R][C];

      // 그래프 입력
      for (int i = 0; i < L; i++) {
        sc.nextLine();
        for (int j = 0; j < R; j++) {
          String s = sc.nextLine();
          for (int k = 0; k < C; k++) {
            char c = s.charAt(k);
            if (c == 'S') {
              startL = i;
              startR = j;
              startC = k;
            }
            if (c == 'E') {
              endL = i;
              endR = j;
              endC = k;
            }
            graph[i][j][k] = c;
          }
        }
      }

      // // 그래프 출력
      // for (int i = 0; i < L; i++) {
      //   System.out.println();
      //   for (int j = 0; j < R; j++) {
      //     for (int k = 0; k < C; k++) {
      //       System.out.print(graph[i][j][k]);
      //     }
      //     System.out.println();
      //   }
      // }

      // BFS 탐색
      int result = bfs(startL, startR, startC);

      // 결과 출력
      if (result == -1) System.out.println("Trapped!");
      else System.out.println("Escaped in " + result + " minute(s).");
    }
    

    
  }

  public static int bfs(int l, int r, int c) {
    Queue<Point> q = new LinkedList<>();
    q.offer(new Point(l, r, c, 0));
    visited[l][r][c] = true;

    while (!q.isEmpty()) {
      Point p = q.poll();

      if (p.l == endL && p.r == endR && p.c == endC) return p.count;

      for (int i = 0; i < 6; i++) {
        int nL = p.l + dL[i];
        int nR = p.r + dR[i];
        int nC = p.c + dC[i];
        if (nL < 0 || nL >= L || nR < 0 || nR >= R || nC < 0 || nC >= C) continue;
        if (!visited[nL][nR][nC] && graph[nL][nR][nC] != '#') {
          visited[nL][nR][nC] = true;
          q.offer(new Point(nL, nR, nC, p.count + 1));
        }
      }
    }

    return -1;
  }
}
