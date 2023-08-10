import java.util.*;

class Main {

  public static int n, m;
  public static int[] parent;

  public static int find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
  }

  public static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if (x < y) parent[y] = x;
    else parent[x] = y;
  }
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    parent = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      parent[i] = i;
    }

    while (m-- > 0) {
      int op = sc.nextInt();
      int a = sc.nextInt();
      int b = sc.nextInt();

      if (op == 0) { // 합집합
        union(a, b);
      }
      else { // 같은 집합인지 확인
        if (find(a) == find(b)) System.out.println("YES");
        else System.out.println("NO");
      } 
    }
  }
}
