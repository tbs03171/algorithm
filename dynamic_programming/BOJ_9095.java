import java.util.*;

class Main {

  public static int T;
  public static int[] dp = new int[12];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    T = sc.nextInt();

    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    
    for (int tc = 0; tc < T; tc++) {
      int x = sc.nextInt();
      for (int i = 4; i <= x; i++) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
      }
      System.out.println(dp[x]);
    }
  }
}
