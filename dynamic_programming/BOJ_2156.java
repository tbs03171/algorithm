import java.util.*;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    int[] arr = new int[N + 1];
    int[] dp = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      arr[i] = sc.nextInt();
    }

    dp[1] = arr[1];
    if (N > 1) {
      dp[2] = arr[1] + arr[2];
    }
    for (int i = 3; i <= N; i++) {
      dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
    }

    System.out.println(dp[N]);
  }
}

/* top-down 풀이
import java.util.*;

public class Main {

  static Integer[] dp;
  static int[] arr;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    dp = new Integer[N + 1];
    arr = new int[N + 1];

    for (int i = 1; i < N + 1; i++) {
      arr[i] = sc.nextInt();
    }

    dp[0] = 0;
    dp[1] = arr[1];

    
    // N이 1로 주어질 수 있으므로 이럴 때를 위해 조건식을 달아둔다.
    // 또한 dp[2]는 어떤 경우에도 첫 번째와 두 번째를 합한 것이 최댓값이다.
    
    if (N > 1) {
      dp[2] = arr[1] + arr[2];
    }

    System.out.println(recur(N));
  }

  static int recur(int N) {

    if (dp[N] == null) {
      dp[N] = Math.max(Math.max(recur(N - 2), recur(N - 3) + arr[N - 1]) + arr[N], recur(N - 1));
    }

    return dp[N];
  }
}
*/

/* 나의 풀이
import java.util.*;

class Main {

  public static int N;
  public static int[] arr;
  public static int[] dp;
  public static int max = 0;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    arr = new int[N];
    dp = new int[N];

    // 포도주 양 입력
    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }

    // dp
    dp[0] = arr[0];
    if (N >= 2) {
      dp[1] = dp[0] + arr[1];
      max = dp[1];
    }
    if (N >= 3) {
      int now = Math.max(arr[0], arr[1]) + arr[2];
      dp[2] = Math.max(max, now);
    }
    for (int i = 3; i < N; i++) {
      int now = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
      max = Math.max(now, max);
      dp[i] = max;
    }

    // // 최대값 찾기
    // int max = 0;
    // for (int i = 0; i < N; i++) {
    //   max = Math.max(dp[i], max);
    // }

    // 결과 출력
    System.out.println(dp[N - 1]);
    
  }
}
*/