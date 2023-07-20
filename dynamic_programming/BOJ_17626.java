import java.util.*;

class Main {

  public static int[] dp = new int[50001];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    Arrays.fill(dp, 4);

    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j * j <= i; j++) {
        dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
      }
    }

    System.out.println(dp[n]);
  }
}
