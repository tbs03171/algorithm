import java.util.*;

class ch4_3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 위치 입력 받아서 변환
    String location = sc.nextLine();
    int X = location.charAt(0) - 'a';
    int Y = location.charAt(1) - '0' - 1;

    // 위치 이동 배열
    int[] moveX = { +2, +2, -2, -2, +1, +1, -1, -1 };
    int[] moveY = { +1, -1, +1, -1, +2, -2, +2, -2 };

    // 8가지 경우의 수 실행해 보기
    int cnt = 0;
    for (int i = 0; i < 8; i++) {
      int x = X + moveX[i];
      int y = Y + moveY[i];
      if ((-1 < x && x < 8) && (-1 < y && y < 8))
        cnt++;
    }

    System.out.println(cnt);
  }
}