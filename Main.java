import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

<<<<<<< HEAD
    // 떡 개수 N, 떡 길이 M 입력 받기
    int n = sc.nextInt();
    int m = sc.nextInt();

    // 떡 개별 길이 입력 받기
    Integer[] arr = new Integer[n];
=======
    // 자연수 갯수 N, 바꿔치기 횟수 K 입력 받기
    int n = sc.nextInt();
    int k = sc.nextInt();

    // A, B 배열 원소 N개씩 입력 받기
    Integer[] A = new Integer[n];
    Integer[] B = new Integer[n];
>>>>>>> 2ee6b23897b58eff29e2826740e0d7205fb3408c
    for (int i = 0; i < n; i++) {
      A[i] = sc.nextInt();
    }
    for (int i = 0; i < n; i++) {
      B[i] = sc.nextInt();
    }

<<<<<<< HEAD
    // 떡 길이들 정렬
    Arrays.sort(arr, Collections.reverseOrder());

    // 초기 설정
    int idx = 0; // H보다 작은 원소들 중 제일 큰 원소의 인덱스
    int sum = 0; // 잘린 떡 길이 합
    int h = arr[0]; // 절단기 높이 H

    // sum >= m 일 때까지
    while (sum < m) {
      h--;
      sum = sum + idx;
      for (; idx < n; idx++) {
        if (arr[idx] < h) break;
        sum = sum + (arr[idx] - h);
      }
    }

    // H 출력
    System.out.println(h);
=======
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
>>>>>>> 2ee6b23897b58eff29e2826740e0d7205fb3408c
  }
}