import java.util.*;

public class ch11_4 {

  public static int n;
  public static ArrayList<Integer> arrayList = new ArrayList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 동전 개수 입력 받기
    n = sc.nextInt();

    // 동전 정보 입력 받기
    for (int i = 0; i < n; i++) {
      arrayList.add(sc.nextInt());
    }

    // 동전 정보 정렬 하기
    Collections.sort(arrayList);

    int target = 1;
    for (int i = 0; i < n; i++) {
      // 만들 수 없는 금액을 찾았을 때 반복 종료
      if (target < arrayList.get(i))
        break;
      target += arrayList.get(i);
    }

    // 결과 출력
    System.out.println(target);
  }
}