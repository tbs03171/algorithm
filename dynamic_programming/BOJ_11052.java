import java.util.*;

class Main {

  public static int N;
  public static int[] arr;
  public static int[] dp;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    arr = new int[N + 1];
    dp = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      arr[i] = sc.nextInt();
    }

    for (int i = 1; i <= N; i++) {
      for (int j = i; j <= N; j++) {
        dp[j] = Math.max(dp[j], arr[i] + dp[j - i]);
      }
    }

    System.out.println(dp[N]);
  }
}
