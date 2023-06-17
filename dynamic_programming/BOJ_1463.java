import java.util.*;

class Main {

  public static int[] d = new int[1000001];
  public static int n;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();

    Arrays.fill(d, 1000001);
    d[0] = 0;
    d[1] = 0;

    for (int i = 2; i <= n; i++) {
      if (i % 3 == 0) {
        d[i] = Math.min(d[i], d[i / 3] + 1);
      }
      if (i % 2 == 0) {
        d[i] = Math.min(d[i], d[i / 2] + 1);
      }
      d[i] = Math.min(d[i], d[i - 1] + 1);
    }

    System.out.println(d[n]);
  }
}
