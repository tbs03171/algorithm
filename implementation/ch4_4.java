import java.util.*;

class ch4_4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 세로 크기 N, 가로 크기 M 입력 받기
    int N;
    int M;
    N = sc.nextInt();
    M = sc.nextInt();

    // 현재 위치와 방향 저장
    int y = sc.nextInt();
    int x = sc.nextInt();
    int dir = sc.nextInt();

    // 방향에 따른 이동 배열 (북, 동, 남, 서)
    int[] moveY = {-1, 0, +1, 0};
    int[] moveX = {0, +1, 0, -1};


    // 맵 구성하기
    Byte[][] map = new Byte[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        map[i][j] = sc.nextByte();
      }
    }

    // 다음 위치 계산
    int nextY;
    int nextX;
    int cnt = 1;
    map[y][x] = -1; // 방문 표시
    while (true) {
      for (int i = 0; i < 4; i++) {
        dir = (dir + 3) % 4; // 현재 기준 왼쪽 방향으로 회전
        // 왼쪽 방향에 아직 가보지 않은 칸이 존재한다면, 전진 후 방문 표시
        nextY = y + moveY[dir];
        nextX = x + moveX[dir];
        if (map[nextY][nextX] == 0) {
          y = nextY;
          x = nextX;
          map[y][x] = -1;
          cnt++;
          i = 0; // 새로운 위치에서 다시 네 방향 탐색하도록 초기화
        }
      }
      // 네 방향 모두 이동할 수 없다면, 뒤로 가기
      y = y + moveY[(dir+2) % 4];
      x = x + moveX[(dir+2) % 4];
      
      // 뒤쪽 방향이 바다라면 멈추기
      if (map[x][y] == 1) break;
    }

    // 출력하기
    System.out.println(cnt);
  }
}