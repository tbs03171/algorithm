import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 떡 개수 N, 떡 길이 M 입력 받기
    int n = sc.nextInt();
    int m = sc.nextInt();

    // 떡 개별 길이 입력 받기
    Integer[] arr = new Integer[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

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
        if (arr[idx] < h)
          break;
        sum = sum + (arr[idx] - h);
      }
    }

    // H 출력
    System.out.println(h);
  }
}