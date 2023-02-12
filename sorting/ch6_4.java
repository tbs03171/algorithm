import java.util.*;

public class ch6_4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 자연수 갯수 N, 바꿔치기 횟수 K 입력 받기
    int n = sc.nextInt();
    int k = sc.nextInt();

    // A, B 배열 원소 N개씩 입력 받기
    Integer[] A = new Integer[n];
    Integer[] B = new Integer[n];
    for (int i = 0; i < n; i++) {
      A[i] = sc.nextInt();
    }
    for (int i = 0; i < n; i++) {
      B[i] = sc.nextInt();
    }

    // A는 오름차순, B는 내림차순 정렬 하기
    Arrays.sort(A);
    Arrays.sort(B, Collections.reverseOrder());

    // K번 바꿔치기 수행하기
    int temp;
    for (int i = 0; i < k; i++) {
      if (A[i] < B[i]) {
        temp = A[i];
        A[i] = B[i];
        B[i] = temp;
      } else break;
    }

    // A의 모든 원소 더하기
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += A[i];
    }

    // 출력
    System.out.println(sum);
  }
}