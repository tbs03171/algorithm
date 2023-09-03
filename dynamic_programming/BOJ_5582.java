import java.util.*;

class Main {

  public static int[][] dp;
  public static int max = 0;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String strA = sc.nextLine();
    String strB = sc.nextLine();
    dp = new int[strA.length() + 1][strB.length() + 1];

    // 다이나믹 프로그래밍
    for (int i = 1; i <= strA.length(); i++) {
      for (int j = 1; j <= strB.length(); j++) {
        if (strA.charAt(i - 1) == strB.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
      }
    }

    // 제일 큰 값 찾아서 출력
    for (int i = 0; i <= strA.length(); i++) {
      for (int j = 0; j <= strB.length(); j++) {
        max = Math.max(max, dp[i][j]);
      }
    }
    System.out.println(max);
  }
}
