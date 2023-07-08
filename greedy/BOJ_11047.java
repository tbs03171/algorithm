import java.util.*;

class Main {
  public static int n, k;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // N, K 입력
    n = sc.nextInt();
    k = sc.nextInt();

    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    int result = 0;
    for (int i = n - 1; i >= 0; i--) {
      if (k >= arr[i]) {
        int x = k / arr[i];
        result = result + x;
        k = k % arr[i];
      }
    }

    System.out.println(result);
  }
}
