import java.util.*;

class Main {
  public static int M, N, x, y;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    while (T-- > 0) {
      M = sc.nextInt();
      N = sc.nextInt();
      x = sc.nextInt() - 1; // 나머지 0 나오는 것 방지하기 위해 -1
      y = sc.nextInt() - 1;

      int year = x;
      boolean check = false;
      while (year <= M * N) {
        if (year % N == y) {
          System.out.println(year + 1);
          check = true;
          break;
        }
        year = year + M;
      }
      if (check == false) {
        System.out.println(-1);
      }
    }
  }
}
