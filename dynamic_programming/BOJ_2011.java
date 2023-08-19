import java.util.*;

class Main {

  public static int[] arr;
  public static long[] dp;
  public static int mod = 1000000;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    arr = new int[str.length()];
    dp = new long[str.length() + 1];

    for (int i = 0; i < str.length(); i++) {
      arr[i] = str.charAt(i) - '0';
    }

    dp[0] = 1;
    if (1 <= arr[0] && arr[0] <= 9) dp[1] += 1;
    for (int i = 2; i <= str.length(); i++) {
      int num1 = arr[i - 1];
      int num2 = arr[i - 2] * 10 + arr[i - 1];

      if (1 <= num1 && num1 <= 9) dp[i] = (dp[i] + dp[i - 1]) % mod;
      if (10 <= num2 && num2 <= 26) dp[i] = (dp[i] + dp[i - 2]) % mod;
    }

    System.out.println(dp[str.length()]);
  }
}
