import java.util.*;

class Main {

  public static int N, K;
  public static int[] dp;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();
    dp = new int[N + 1];

    Arrays.fill(dp, 1);
    for (int i = 2; i <= K; i++) {
      for (int j = 1; j <= N; j++) {
        dp[j] = (dp[j - 1] + dp[j]) % 1000000000;
      }
    }

    System.out.println(dp[N]);
  }
}
