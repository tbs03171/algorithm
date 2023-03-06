import java.util.*;

public class ch11_1 {

  public static int n;
  public static ArrayList<Integer> arrayList = new ArrayList<>();
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 모험가의 수 N 입력
    int n = sc.nextInt();

    // 각 모험가의 공포도 X 입력
    for (int i = 0; i < n; i++) {
      arrayList.add(sc.nextInt());
    }

    // 공포도 배열 정렬
    Collections.sort(arrayList);

    int result = 0; // 그룹 수
    int count = 0; // 현재 그룹에 포함된 모험가의 수
    
    for (int i = 0; i < n; i++) { // 공포도를 낮은 것부터 하나씩 확인하며
      count++;
      if (count >= arrayList.get(i)) { // 현재 그룹에 포함된 모험가의 수가 현재의 공포도 이상이라면
        result += 1; // 총 그룹의 수 증가시키기
        count = 0; // 현재 그룹에 포함된 모험가의 수 초기화
      }
      
    }

    System.out.println(result);
  }
}