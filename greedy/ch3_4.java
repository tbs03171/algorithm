
import java.util.*;

class ch3_4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // N, K 입력받기
    int N = sc.nextInt();
    int K = sc.nextInt();

    // 횟수 세기
    int count = 0; // 횟수
    int remainder = 0; // 나머지
    while (N != 1) {
      remainder = N % K;
      if (remainder == 0) { // 나누어 떨어지는 경우 2번 선택
        N /= K;
        count++;
      } else { // 나누어 떨어지지 않는 경우 1번 선택
        N -= remainder;
        count += remainder;
      }
    }

    // 횟수 출력하기
    System.out.println(count);
  }
}