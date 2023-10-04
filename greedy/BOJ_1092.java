import java.util.*;
import java.io.*;

public class Main {

  static int N, M;
  static ArrayList<Integer> crane;
  static ArrayList<Integer> box;

  public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);
    
    N = sc.nextInt();
    crane = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      crane.add(sc.nextInt());
    }

    M = sc.nextInt();
    box = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      box.add(sc.nextInt());
    }

    Collections.sort(crane, Collections.reverseOrder());
    Collections.sort(box, Collections.reverseOrder());

    if (box.get(0) > crane.get(0)) {
      System.out.println(-1);
      return;
    }

    int ans = 0;

    while (!box.isEmpty()) {
      int idx = 0; // 박스 인덱스
      for (int i = 0; i < N;) {
        if (idx == box.size()) break;
        else if (crane.get(i) >= box.get(idx)) {
          box.remove(idx);
          i++; // 다음 크레인
        } else idx++; // 다음 박스
      }
      ans++;
    }

    System.out.println(ans);
  }
}


/* 나의 풀이
import java.util.*;
import java.io.*;

class Main {

  public static int N, M;
  public static Integer[] cranes;
  public static ArrayList<Integer> boxList = new ArrayList<>();
  public static int cnt = 0;
  
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    // 크레인 입력 후 내림차순 정렬
    N = Integer.parseInt(br.readLine());
    cranes = new Integer[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      cranes[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(cranes, Collections.reverseOrder());

    // 박스 입력 후 내림차순 정렬
    M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      boxList.add(Integer.parseInt(st.nextToken()));
    }
    Collections.sort(boxList, Collections.reverseOrder());

    // 제일 무거운 박스가 무게 제한이 제일 큰 크레인보다 무거운 경우는
    // 모든 박스를 배로 옮길 수 없음. -1 출력
    if (boxList.get(0) > cranes[0]) {
      System.out.println(-1);
      return;
    }
    
    // 모든 박스를 다 운반할 때 까지
    int idx = 0;
    while (true) {
      // 모든 박스를 하나씩 보면서
      for (int i = 0; i < boxList.size();) {
        if (idx >= N) break; // 모든 크레인 다 사용
        if (cranes[idx] >= boxList.get(i)) { // 옮길 수 있음
          boxList.remove(i); // 옮기고
          idx++; // 다음 크레인
        }
        else {
          i++; // 다음 박스
        }
      }
      cnt++; // 한 번 옮김. 카운트 하고 다시 0번 크레인으로
      idx = 0;
      if (boxList.isEmpty()) break;
    }

    // 결과 출력
    System.out.println(cnt);
    
  }
}
*/