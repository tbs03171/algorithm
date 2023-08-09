import java.io.*;

class Value {

  int n;
  String process;

  Value(int n, String process) {
    this.n = n;
    this.process = process;
  }
}

public class Main {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());

    Value[] dp = new Value[N + 1];
    dp[1] = new Value(0, "1");

    for (int i = 2; i <= N; i++) {
      int cnt = Integer.MAX_VALUE;
      int before = 0;
      if (i % 3 == 0) {
        cnt = dp[i / 3].n;
        before = i / 3;
      }

      if (i % 2 == 0) {
        if (cnt > dp[i / 2].n) {
          cnt = dp[i / 2].n;
          before = i / 2;
        }
      }

      if (cnt > dp[i - 1].n) {
        before = i - 1;
      }

      dp[i] = new Value(dp[before].n + 1, i + " " + dp[before].process);      
    }
    bw.write(dp[N].n + "\n");
    bw.write(dp[N].process + "\n");
    bw.flush();
    bw.close();
    br.close();
  }
}

/* 나의 풀이 (DFS)
import java.util.*;

class Main {

  public static int N;
  public static boolean[] visited;
  public static int[] arr;
  public static int[] result;
  public static int min = Integer.MAX_VALUE;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    visited = new boolean[N + 1];
    arr = new int[N + 1];
    result = new int[N + 1];

    visited[N] = true;
    dfs(N, 0);

    System.out.println(min);
    for (int i = 0; i <= min; i++) {
      System.out.print(result[i] + " ");
    }
    
  }

  public static void dfs(int n, int cnt) {

    if (cnt > min) return;

    arr[cnt] = n;
    visited[n] = true;

    if (n == 1) { // min 업뎃 하고 배열 저장
      min = cnt;
      for (int i = 0; i <= cnt; i++) {
        result[i] = arr[i];
      }
      return;
    }
    
    if (n % 3 == 0) {
      dfs(n / 3, cnt + 1);
    }
    if (n % 2 == 0) {
      dfs(n / 2, cnt + 1);
    }
    dfs(n - 1, cnt + 1);

    visited[n] = false;
  }
}
*/