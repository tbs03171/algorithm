import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    String[] arr = new String[N];

    sc.nextLine(); // 개행 버림

    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextLine();
    }

    Arrays.sort(arr, new Comparator<String>() {
      @Override
      public int compare(String s1, String s2) {
        if (s1.length() == s2.length()) return s1.compareTo(s2);
        else return s1.length() - s2.length();
      }
    });

    System.out.println(arr[0]);

    for (int i = 1; i < N; i++) {
      // 중복되지 않는 단어만 출력
      if (!arr[i].equals(arr[i - 1])) {
        System.out.println(arr[i]);
      }
    }
  }
}

/* 나의 코드
import java.util.*;

class Main {

  public static Set<String> set = new HashSet<String>();
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    while (N-- > 0) {
      set.add(sc.next());
    }

    ArrayList<String> list = new ArrayList<>(set);
    Collections.sort(list, new Comparator<String>() {
      @Override
      public int compare(String s1, String s2) {
        if (s1.length() == s2.length()) return s1.compareTo(s2);
        else return s1.length() - s2.length();
      }
    });

    for (String word : list) {
      System.out.println(word);
    }
  }
}
*/