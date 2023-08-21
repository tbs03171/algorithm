import java.util.*;

class Main {

  public static int N;
  public static int[] arr;
  public static long[][] dp;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    arr = new int[N];
    dp = new long[21][N];

    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }

    dp[arr[0]][0] = 1;
    for (int i = 0; i < N - 2; i++) {
      int num = arr[i + 1];
      for (int j = 0; j <= 20; j++) {
        int sub = j - num;
        if (0 <= sub && sub <= 20) dp[sub][i + 1] += dp[j][i];
      }
      for (int j = 0; j <= 20; j++) {
        int sum = j + num;
        if (0 <= sum && sum <= 20) dp[sum][i + 1] += dp[j][i];
      }
    }

    System.out.println(dp[arr[N - 1]][N - 2]);
  }

}
