import java.util.*;

class Main {

  // 게임 판 크기 N
  public static int n;

  // 게임 판
  public static int[][] arr = new int[100][100];

  // 경우의 수 저장
  public static long[][] d = new long[100][100];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 게임 판 크기 N 입력
    n = sc.nextInt();

    // 게임 판 정보 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        arr[i][j] = sc.nextInt();
      }
    }
    
    if (arr[0][0] != 0) d[0][0] = 1;
    
    // 경로 탐색 시작
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == n - 1 && j == n - 1) break;
        
        // 여기로 오는 경로가 존재하면
        if (d[i][j] != 0) {
          int jump = arr[i][j];

          if (jump == 0) {
            continue;
          }
          
          // 아래쪽 점프
          if (0 <= i + jump && i + jump < n) {
            d[i + jump][j] = d[i + jump][j] + d[i][j];
          }

          // 오른쪽 점프
          if (0 <= j + jump && j + jump < n) {
            d[i][j + jump] = d[i][j + jump] + d[i][j];
          }
        }
      }
    }

    System.out.println(d[n - 1][n - 1]);
    
  }
}
