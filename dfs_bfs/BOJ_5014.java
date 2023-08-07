import java.util.*;

class Floor {
  int num, cnt;

  public Floor(int num, int cnt) {
    this.num = num;
    this.cnt = cnt;
  }
}

class Main {

  public static int F, S, G, U, D;
  public static boolean[] visited;
  public static int result = Integer.MAX_VALUE;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    F = sc.nextInt();
    S = sc.nextInt();
    G = sc.nextInt();
    U = sc.nextInt();
    D = sc.nextInt();
    visited = new boolean[F + 1];

    bfs(S);

    if (visited[G] == false) System.out.println("use the stairs");
    else System.out.println(result);
  }

  public static void bfs(int x) {
    Queue<Floor> q = new LinkedList<>();
    q.offer(new Floor(x, 0));
    visited[x] = true;

    while (!q.isEmpty()) {
      Floor f = q.poll();

      // 도착
      if (f.num == G) {
        result = f.cnt;
        return;
      }

      // U, D 이동
      int nxU = f.num + U;
      if (1 <= nxU && nxU <= F && visited[nxU] == false) {
        visited[nxU] = true;
        q.offer(new Floor(nxU, f.cnt + 1));
      }
      int nxD = f.num - D;
      if (1 <= nxD && nxD <= F && visited[nxD] == false) {
        visited[nxD] = true;
        q.offer(new Floor(nxD, f.cnt + 1));
      }
    }
  }
}
