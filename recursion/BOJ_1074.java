import java.util.*;

class Main {

  public static int n, r, c;

  public static int search(int startR, int startC, int size) {
    if (size == 1) { // 재귀 중단
      return 0;
    }

    // 몇 사분면인지
    int half = size / 2;
    if (r < startR + half && c < startC + half) { // 1사분면
      return (0 + search(startR, startC, half));
    } else if (r < startR + half && startC + half <= c) { // 2사분면
      return ((half * half) + search(startR, startC + half, half));
    } else if (startR + half <= r && c < startC + half) { // 3사분면
      return ((half * half * 2) + search(startR + half, startC, half));
    } else { // 4사분면
      return ((half * half * 3) + search(startR + half, startC + half, half));
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    r = sc.nextInt();
    c = sc.nextInt();

    int result = search(0, 0, (int)Math.pow(2, n));

    System.out.println(result);
  }
}
