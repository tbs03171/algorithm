import java.util.*;

class Main {
  public static int n;
  public static int[] count = new int[2]; // count[0] : 흰 색종이 개수, count[1] : 파란 색종이 개수
  public static int[][] board;

  public static void partition(int length, int startX, int startY) {
    // 모든 좌표 색상 동일하면 카운트 하고 리턴
    int color = board[startX][startY];
    boolean isEqual = true;
    for (int i = startX; i < startX + length; i++) {
      for (int j = startY; j < startY + length; j++) {
        if (board[i][j] != color) {
          isEqual = false;
        }
      }
    }
    if (isEqual == true) {
      count[color] = count[color] + 1;
      return;
    }

    // 아니라면 1, 2, 3, 4분면 각각 재귀 호출
    partition(length / 2, startX, startY);
    partition(length / 2, startX, startY + (length / 2));
    partition(length / 2, startX + (length / 2), startY);
    partition(length / 2, startX + (length / 2), startY + (length / 2));
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    board = new int[n][n];

    // 그래프 정보 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    // 카운팅 시작
    partition(n, 0, 0);

    // 결과 출력
    System.out.println(count[0]);
    System.out.println(count[1]);
  }
}
