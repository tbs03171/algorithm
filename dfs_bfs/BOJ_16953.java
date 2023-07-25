import java.io.*;
import java.util.*;

public class Main {

  static long A, B, ans;
  static boolean flag;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    A = sc.nextInt();
    B = sc.nextInt();
    dfs(A, 1);
    if (flag) System.out.println(ans);
    else System.out.println(-1);
    
  }

  public static void dfs(long num, int cnt) {
    if (num > B) return;
    if (num == B) {
      ans = cnt;
      flag = true;
      return;
    }

    dfs(num * 2, cnt + 1);
    dfs(num * 10 + 1, cnt + 1);
  }
  
}

/* bfs로 풀이
import java.util.*;
import java.io.*;

public class Main {

  static long a, b;
  static int cnt;

  static int bfs() {
    Queue<Long> q = new LinkedList<>();
    q.add(a);

    while(!q.isEmpty()) {
      int size = q.size();

      for (int i = 0; i < size; i++) {
        long tmp = q.poll();
        if (tmp == b) return cnt + 1;

        if (tmp * 2 <= b) q.add(tmp * 2);
        if (tmp * 10 + 1 <= b) q.add(tmp * 10 + 1);
      }
      cnt++;
    }
    return -1;
  }

  public static void main(String args[]) throws IOException {
    
    BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk = new StringTokenizer(bfr.readLine());

    a = Long.parseLong(stk.nextToken());
    b = Long.parseLong(stk.nextToken());

    System.out.println(bfs());
    
  }
}
*/

/* 거꾸로 가는 풀이
import java.util.*;
import java.io.*;

public class Main {

  public static void main (String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    long A = Long.parseLong(st.nextToken());
    long B = Long.parseLong(st.nextToken());

    int ans = 1;
    while (B != A) {
      if (B < A) {
        ans = -1;
        break;
      }

      String str = String.valueOf(B);

      // 맨 끝자리가 1이거나 B가 2로 나누어 떨어지지 않는다면, A로 만들 수 없다.
      if (str.charAt(str.length() - 1) != '1' && B % 2 != 0) {
        ans = -1;
        break;
      }

      if (B % 2 == 0) { // B가 2로 나누어 떨어진다면, 2로 나눈다.
        B >>= 1;
      } else { // 그렇지 않고, 맨 끝자리가 1이라면 1을 없앤다.
        str = str.substring(0, str.length() - 1);
        B = Long.parseLong(str);
      }

      ans++; // 횟수 증가
    }

    bw.write(ans + "\n");
    bw.flush();
    bw.close();
    br.close();
  }
}
/*

/* 나의 풀이
import java.util.*;

class Main {

  public static int A, B;
  public static int min = Integer.MAX_VALUE;
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    A = sc.nextInt();
    B = sc.nextInt();

    solve(A, 0);

    if (min == Integer.MAX_VALUE) System.out.println(-1);
    else System.out.println(min + 1);
  }

  public static void solve(int n, int cnt) {
    
    if (n == B) {
      min = Math.min(min, cnt);
      return;
    }
    if (n > B) return;

    if (n <= 500000000) {
      solve(n * 2, cnt + 1);
    }
    if (n <= 100000000) {
      solve(n * 10 + 1, cnt + 1);
    }
    
    return;
  }
}
*/