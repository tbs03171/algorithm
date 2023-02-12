import java.util.*;

public class ch7_2 {

  public static boolean binarySearch(int[] arr, int target, int start, int end) {
    while (start <= end) {
      int mid = (start + end) / 2;
      // 찾은 경우 true 반환
      if (arr[mid] == target)
        return true;
      // target이 더 작은 경우
      else if (arr[mid] > target)
        end = mid - 1;
      // target이 더 큰 경우
      else
        start = mid + 1;
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 매장에 있는 부품 종류 N 입력 받기
    int n = sc.nextInt();

    // N개의 부품 번호 입력 받기
    int[] items = new int[n];
    for (int i = 0; i < n; i++) {
      items[i] = sc.nextInt();
    }

    // N개의 부품 정렬하기
    Arrays.sort(items);

    // 손님이 문의한 부품 종류 M 입력 받기
    int m = sc.nextInt();

    // M개의 부품 번호 입력 받아서 탐색하기
    for (int i = 0; i < m; i++) {
      int target = sc.nextInt();
      if (binarySearch(items, target, 0, n - 1))
        System.out.print("yes ");
      else
        System.out.print("no ");
    }

  }
}