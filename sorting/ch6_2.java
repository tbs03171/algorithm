import java.util.*;

public class ch6_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // N 입력 받기
    int n = sc.nextInt();

    // 숫자 배열에 담기
    Integer[] arr = new Integer[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    // 내림차순으로 정렬
    Arrays.sort(arr, Collections.reverseOrder());

    // 출력하기
    for (int i = 0; i < n; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}