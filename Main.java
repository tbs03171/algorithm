import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 동전 개수 입력 받기
    int n = sc.nextInt();

    // 동전 정보 입력 받기
    int[] coins = new int[n];
    for (int i = 0; i < n; i++) {
      coins[i] = sc.nextInt();
    }

    // 동전 정보 정렬 하기
    Arrays.sort(coins);

    // 1부터 만들 수 있는지 없는지 확인
    int result = 0;
    boolean OK = false;
    while (true) {
      result++;
      for (int i = 1; i <= result; i++) {
        if () {
          
          break;
        }
      }
      if (OK == false) break;
    }

    // 결과 출력
    System.out.println(result);
  }
}