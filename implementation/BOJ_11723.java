import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int bit_set = 0;
    while (N-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String op = st.nextToken();
      int num;

      // 연산
      switch (op) {
        case "add" :
          num = Integer.parseInt(st.nextToken());
          bit_set = bit_set | (1 << num - 1);
          break;
        case "remove" :
          num = Integer.parseInt(st.nextToken());
          bit_set = bit_set & ~(1 << num - 1);
          break;
        case "check" :
          num = Integer.parseInt(st.nextToken());
          sb.append((bit_set & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
          break;
        case "toggle" :
          num = Integer.parseInt(st.nextToken());
          bit_set = bit_set ^ (1 << num - 1);
          break;
        case "all" :
          bit_set = bit_set | (~0);
          break;
        case "empty" :
          bit_set = bit_set & 0;
          break;
      }
    }
    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}
