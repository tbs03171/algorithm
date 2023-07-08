import java.util.*;

class Main {

  public static int[] dp0 = new int[41];
  public static int[] dp1 = new int[41];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    dp0[0] = 1;
    dp1[1] = 1;
    
    for (int tc = 0; tc < T; tc++) {
      int x = sc.nextInt();
      for (int i = 2; i <= x; i++) {
        dp0[i] = dp0[i - 1] + dp0[i - 2];
        dp1[i] = dp1[i - 1] + dp1[i - 2];
      }

      System.out.println(dp0[x] + " " + dp1[x]);
    }
  }
}
