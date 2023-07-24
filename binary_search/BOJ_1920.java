import java.util.*;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }

    // 배열은 반드시 정렬되어 있어야 함
    Arrays.sort(arr);

    int M = sc.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {

      // 찾고자 하는 값이 있을 경우 1, 없을 경우 0 출력
      if (binarySearch(arr, sc.nextInt()) >= 0) {
        sb.append(1).append('\n');
      }
      else {
        sb.append(0).append('\n');
      }
    }
    System.out.println(sb);
  }

  /**
  * @param arr 정렬된 배열
  * @param key 찾으려는 값
  * @return key와 일치하는 배열의 인덱스
  */
  public static int binarySearch(int[] arr, int key) {

    int lo = 0;
    int hi = arr.length - 1;

    // lo가 hi보다 커지기 전까지 반복
    while (lo <= hi) {

      int mid = (lo + hi) / 2; // 중간 위치

      // key값이 중각 위치의 값보다 작을 경우
      if (key < arr[mid]) {
        hi = mid - 1;
      }
      // key값이 중간 위치의 값보다 클 경우
      else if (key > arr[mid]) {
        lo = mid + 1;
      }
      // key값과 중간 위치의 값이 같을 경우
      else {
        return mid;
      }
    }
    
    // 찾고자 하는 값이 존재하지 않을 경우
    return -1;
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int N, M;
  public static int[] arr;

  public static int binarySearch(int left, int right, int key) {

    while (left <= right) {
      int mid = (left + right) / 2;

      if (arr[mid] == key) {
        return 1;
      }
      else if (arr[mid] < key) {
        left = mid + 1;
      }
      else {
        right = mid - 1;
      }
    }

    return 0;
  }
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    arr = new int[N];

    // arr 입력 받고 정렬
    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }
    Arrays.sort(arr);

    // M개의 수 각각 이진 탐색 후 결과 출력
    M = sc.nextInt();
    while (M-- > 0) {
      System.out.println(binarySearch(0, N - 1, sc.nextInt()));
    }
  }
}
*/