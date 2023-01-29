import java.util.*;

class ch3_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in); // 스캐너 불러오기

    // N, M, K 입력받기
    int N = sc.nextInt();
    int M = sc.nextInt();
    int K = sc.nextInt();

    // 자연수들 입력받기
    int[] numArr;
    numArr = new int[N];
    for (int i = 0; i < N; i++) {
      numArr[i] = sc.nextInt();
    }

    // 제일 큰 수 찾기
    Arrays.sort(numArr);
    int first = numArr[N - 1];
    int second = numArr[N - 2];

    // 더하기
    int x = M / (K + 1); // 몇 묶음 ?
    int y = M % (K + 1); // 나머지는 ?
    int result = (first * K + second * 1) * x + second * y;

    // 출력하기
    System.out.println(result);
  }
}
