import java.util.*;

public class Main {
  public static boolean[] prime;
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int M = sc.nextInt();
    int N = sc.nextInt();

    prime = new boolean[N + 1];
    get_prime();

    for (int i = M; i <= N; i++) {
      // false = 소수
      if(!prime[i]) System.out.println(i);
    }
  }

  // 에라토스테네스의 체 알고리즘
  public static void get_prime() {
    // true = 소수아님, false = 소수
    prime[0] = prime[1] = true;

    for (int i = 2; i <= Math.sqrt(prime.length); i++) {
      if (prime[i]) continue;
      for (int j = i * i; j < prime.length; j += i) {
        prime[j] = true;
      }
    }
  }
}

/* 나의 코드
import java.util.*;

class Main {
  public static int n, m;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    m = sc.nextInt();
    n = sc.nextInt();

    HashSet<Integer> set = new HashSet<>();
    if (m <= 2) {
      set.add(2);
    }
    
    if (m % 2 == 0) {
      for (int i = m + 1; i <= n; i = i + 2) {
        set.add(i);
      }
    } else {
       for (int i = m; i <= n; i = i + 2) {
         if (i == 1) continue;
         set.add(i);
       }
    }
    

    for (int i = 3; i <= (n / 2); i++) {
      for (int j = 2; i * j <= n; j++) {
        set.remove(Integer.valueOf(i * j));
      }
    }

    ArrayList<Integer> list = new ArrayList<>(set);
    Collections.sort(list);

    for (Integer value : list) {
      System.out.println(value);
    }
  }
}
*/