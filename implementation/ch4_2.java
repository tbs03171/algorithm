import java.util.*;

class ch4_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int cnt = 0;
    for (int i = 0; i <= n; i++) {
      if (i == 3 || i == 13) {
        cnt += 3600;
      } else {
        cnt += 1575;
      }
    }

    System.out.println(cnt);
  }
}