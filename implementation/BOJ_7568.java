import java.util.*;

class Main {
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[][] arr = new int[N][2];

    // N명의 덩치 입력
    for (int i = 0; i < N; i++) {
      arr[i][0] = sc.nextInt();
      arr[i][1] = sc.nextInt();
    }

    // 덩치 등수 계산
    for (int i = 0; i < N; i++) {
      int result = 1;
      for (int j = 0; j < N; j++) {
        if (i == j) continue;
        if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) result++;
      }
      System.out.print(result + " "); // 결과 출력
    }
  }
}
