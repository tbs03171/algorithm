import java.util.*;

class Main {

  public static int N, M;
  public static int[][] board;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    board = new int[N + 1][N + 1];

    // 표 정보 입력
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    // 구간합 미리 계산
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        board[i][j] = board[i][j] + board[i - 1][j] + board[i][j - 1] - board[i - 1][j - 1];
      }
    }

    // 연산 입력
    StringBuilder sb = new StringBuilder();
    while (M-- > 0) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();

      int result = board[x2][y2] - board[x1 - 1][y2] - board[x2][y1 - 1] + board[x1 - 1][y1 - 1];
      sb.append(result).append("\n");
    }

    System.out.println(sb);
  }
}
