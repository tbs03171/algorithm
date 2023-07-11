import java.util.*;

class Main {

  public static long[] dp = new long[101];
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = sc.nextInt();

    dp[1] = 1;
    dp[2] = 1;
    dp[3] = 1;
    dp[4] = 2;
    dp[5] = 2;

    for (int tc = 0; tc < t; tc++) {
      int n = sc.nextInt();

      for (int i = 6; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 5];
      }

      System.out.println(dp[n]);
    }
  }
}
