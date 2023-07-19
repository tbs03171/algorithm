import java.util.*;

class Main {
  public static int[] count = new int[101];
  public static int[] ladderAndSnake = new int[101]; // 사다리 또는 뱀 이동 정보
  public static boolean[] visited = new boolean[101]; // 방문 유무
  public static int n, m;

  public static void bfs (int start) {
    Queue<Integer> q = new LinkedList<>();
    visited[start] = true;
    q.offer(start);
    count[start] = 0;

    while (!q.isEmpty()) {
      int now = q.poll();
      if (now == 100) {
        System.out.println(count[now]);
        return;
      }
      
      // 주사위
      for (int i = 1; i <= 6; i++) {
        int next = now + i;
        if (next <= 100) { // 경계 내부에 있으며
          if (visited[next] == false) { // 아직 방문하지 않았다면
            visited[next] = true; // 방문 처리

            if (ladderAndSnake[next] != 0) { // 사다리 또는 뱀 존재
              if (visited[ladderAndSnake[next]] == false) {
                q.offer(ladderAndSnake[next]);
                visited[ladderAndSnake[next]] = true;
                count[ladderAndSnake[next]] = count[now] + 1;
              }
            } else { // 사다리 또는 뱀 존재하지 않음
              q.offer(next);
              count[next] = count[now] + 1;
            }
          }
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    while (n-- > 0) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      ladderAndSnake[x] = y;
    }
    while (m-- > 0) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      ladderAndSnake[u] = v;
    }
  
    bfs(1);
  }
}