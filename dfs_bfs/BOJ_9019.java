import java.util.*;

class Main {

  public static String result;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    
    while (T-- > 0) {
      int A = sc.nextInt();
      int B = sc.nextInt();

      Queue<Integer> q = new LinkedList<>();
      boolean[] visited = new boolean[10000]; // BFS 탐색의 방문 여부 체크
      String[] command = new String[10000]; // 정답 명령어를 담는 배열
      
      q.offer(A);
      visited[A] = true;
      Arrays.fill(command, "");

      while (!q.isEmpty() && !visited[B]) {
        int now = q.poll();

        int D = (2 * now) % 10000;
        int S = now == 0 ? 9999 : now - 1;
        int L = (now % 1000) * 10 + now / 1000; // 1234 -> 2341 : 2340 + 1
        int R = (now % 10) * 1000 + now / 10; // 1234 -> 4123 : 4000 + 123

        if (!visited[D]) {
          q.offer(D);
          visited[D] = true;
          command[D] = command[now] + "D";
        }
        if (!visited[S]) {
          q.offer(S);
          visited[S] = true;
          command[S] = command[now] + "S";
        }
        if (!visited[L]) {
          q.offer(L);
          visited[L] = true;
          command[L] = command[now] + "L";
        }
        if (!visited[R]) {
          q.offer(R);
          visited[R] = true;
          command[R] = command[now] + "R";
        }
        
      }

      System.out.println(command[B]);
    }
  }
}
