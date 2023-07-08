import java.util.*;

class Main {

  public static int n;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 사람의 수 N
    n = sc.nextInt();

    // Pi 입력
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    // 오름차순 정렬
    Arrays.sort(arr);

    // 누적
    for (int i = 1; i < arr.length; i++) {
      arr[i] = arr[i - 1] + arr[i];
    }

    // 합
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum = sum + arr[i];
    }
    
    // 결과 출력
    System.out.println(sum);
  }
}
