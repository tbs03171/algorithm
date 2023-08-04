import java.io.*;
import java.util.*;

public class Main {

  public static int L, C;
  public static char[] list;
  public static char[] code;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());

    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    list = new char[C];
    code = new char[L];

    st = new StringTokenizer(br.readLine());

    for (int x = 0; x < C; x++) {
      list[x] = st.nextToken().charAt(0);
    }

    // 정렬
    Arrays.sort(list);

    makeCode(0, 0);
  }

  public static void makeCode(int x, int idx) {

    if (idx == L) {
      // 최소 한 개의 모음, 최소 두 개의 자음인지 확인
      if (isValid()) {
        System.out.println(code);
      }
      return;
    }

    // 아직 길이 L의 코드를 만들지 않았고 글자도 남았다
    for (int i = x; i < C; i++) {
      code[idx] = list[i];
      makeCode(i + 1, idx + 1);
    }
  }

  // 모음 최소 1개, 자음 최소 2개인지 확인
  public static boolean isValid() {
    int mo = 0;
    int ja = 0;

    for (char x : code) {
      if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') mo++;
      else ja++;
    }

    if (mo >= 1 && ja >= 2) return true;
    return false;
  }
}

/* 나의 풀이
import java.util.*;

public class Main {

    public static int L;
    public static int C;
    public static char[] arr;
    public static char[] result;
    public static boolean[] is_vowel = new boolean[26];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();
        arr = new char[C];
        result = new char[L];

        is_vowel['a' - 'a'] = true;
        is_vowel['e' - 'a'] = true;
        is_vowel['i' - 'a'] = true;
        is_vowel['o' - 'a'] = true;
        is_vowel['u' - 'a'] = true;


        for (int i = 0; i < C; i++) {
            arr[i] = sc.next().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0, 0);
    }

    public static void dfs(int at, int moCnt, int jaCnt) {
        if (moCnt + jaCnt == L && moCnt >= 1 && jaCnt >= 2) {
            for (int i = 0; i < L; i++) {
                System.out.print(result[i]);
            }
            System.out.println();
            return;
        }

        if (moCnt + jaCnt >= L) return;

        for (int i = at; i < C; i++) {
            char c = arr[i];
            result[moCnt + jaCnt] = c;
            if (is_vowel[c - 'a'] == true) dfs(i + 1, moCnt + 1, jaCnt);
            else dfs(i + 1, moCnt, jaCnt + 1);
        }
    }
}
 */