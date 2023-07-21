import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int M = sc.nextInt();
    sc.nextLine();
    String S = sc.nextLine();

    int idx = 0; // 지금 탐색중인 위치
    int result = 0; // 최종 결과
    while (idx < M) {
      if (S.charAt(idx) == 'I') { // 'I' 일 경우 -> P몇인지 세아리기
        int n = 0;
        int j = idx + 1; 
        for ( ; j < M - 1; j = j + 2) { // 'OI'가 몇 개인지 확인
          if (S.charAt(j) == 'O' && S.charAt(j + 1) == 'I') {
            n++;
          }
          else {
            break;
          }
        }
        idx = j;
        if (n >= N) {
          result = result + (n - N + 1);
        }
      }
      else { // 'O' 일 경우 -> 다음 글자 탐색
        idx++;
      }
    }

    // 결과 출력
    System.out.println(result);
  }
}
