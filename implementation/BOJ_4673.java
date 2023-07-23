import java.util.*;

class Main {

  public static boolean[] dp = new boolean[10001];
  
  public static void main(String[] args) {
    for (int i = 1; i <= 10000; i++) {
      int n = d(i);
      if (n <= 10000) {
        dp[n] = true;
      }
    }

    // 셀프 넘버 출력
    for (int i = 1; i <= 10000; i++) {
      if (dp[i] == false) System.out.println(i);
    }
  }

  public static int d(int n) {
    int result = n;
    while (n > 0) {
      result = result + (n % 10);
      n = n / 10;
    }

    return result;
  }
}
