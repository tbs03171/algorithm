import java.util.*;

public class ch10_2 {

  // 노드의 개수(N)와 연산의 개수(M)
  // 노드의 개수는 최대 100,000개
  public static int n, m;
  public static int[] parent = new int[100001]; // 부모 테이블 초기화

  // 특정 원소가 속한 집합을 찾기
  public static int findParent(int x) {
    // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if (parent[x] == x) return x;
    return parent[x] = findParent(parent[x]);
  }

  // 두 원소가 속한 집합을 합치기
  public static void unionParent(int a, int b) {
    a = findParent(a);
    b = findParent(b);
    if (a < b) parent[b] = a;
    else parent[a] = b;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 학생 번호 수 N, 연산 개수 M 입력
    int n = sc.nextInt();
    int m = sc.nextInt();

    // 부모 배열 자기 자신으로 초기화
    for (int i = 0; i <= n; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < m; i++) {
      int oper = sc.nextInt();
      int a = sc.nextInt();
      int b = sc.nextInt();
      if (oper == 0) { // 0 a b -> 팀 합치기
        unionParent(a, b);
      }
      else { // 1 a b -> 같은 팀 여부 확인
        if (findParent(a) == findParent(b)) System.out.println("YES");
        else System.out.println("NO");
      }
    }
  }
}