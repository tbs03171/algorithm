import java.util.*;

public class ch7_3 {
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

    int start = 0;
    int end = (int) 1e9;
    int result = 0;

    while (start <= end) {
      int mid = (start + end) / 2;
      int sum = 0;
      for (int i = 0; i < n; i++) {
        if (arr[i] <= mid)
          continue;
        sum = sum + (arr[i] - mid);
      }
      if (sum >= m) {
        result = mid;
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }

    // 결과 출력
    System.out.println(result);
  }
}