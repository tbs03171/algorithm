import java.util.*;

public class Main {
  public static int n, m;
  public static Integer[][] input;
  
  public static boolean dfs(int x, int y) {
    if (x > n-1 || y > m-1 || x < 0 || y < 0) return false;
    if (input[x][y] == 1) return false;
    // 현재 노드를 방문 처리
    input[x][y] = 1;
    // 현재 노드의 상, 하, 좌, 우 재귀적으로 탐색
    dfs(x-1, y); // 상
    dfs(x+1, y); // 하
    dfs(x, y-1); // 좌
    dfs(x, y+1); // 우
    return true;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // n, m 입력 받아 초기 설정
    n = sc.nextInt();
    m = sc.nextInt();
    input = new Integer[n][m];

    // 얼음판 내용 배열에 읽어오기
    sc.nextLine(); // 버퍼 비우기
    for (int i = 0; i < n; i++) {
    	String str = sc.nextLine();
      for (int j = 0; j < m; j++) {
        input[i][j] = str.charAt(j)-'0';
      }
    }

    // 얼음 갯수 탐색 시작
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
          if (dfs(i,j)) cnt++;
      }
    }

    // 얼음 갯수 출력
    System.out.println(cnt);
  }
}