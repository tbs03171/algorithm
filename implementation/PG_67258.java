package programmers;
import java.util.*;

public class PG_67258 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int[] results = solution.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
            for (int result : results) {
                System.out.print(result + " ");
            }
        }
}

class Solution {

    public int[] solution(String[] gems) {
        // count : 제거한 개수, start : 구간 시작, interval : 구간 크기
        int start = 0, count = 0, interval = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> q = new LinkedList<>();

        // gems를 set에 넣기
        set.addAll(Arrays.asList(gems));

        // 투 포인터
        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            q.add(gems[i]);

            // 2개 이상이라면 하나 빼기
            while (map.get(q.peek()) > 1) {
                map.put(q.peek(), map.get(q.poll()) - 1);
                count++;
            }

            // 조건 만족하고, 현재 구간 크기가 interval 보다 작으면
            // intervel, start 갱신
            if (map.size() == set.size() && interval > i - count) {
                interval = i - count;
                start = count + 1;
            }
        }

        return new int[] {start, start + interval};
    }
}

/* 나의 풀이
class Solution {

    int minSize; // 조건 만족하는 구간 중 제일 작은 사이즈
    int result1; int result2; // 조건 만족하는 구간
    Map<String, Integer> map = new HashMap<>(); // 현재 구간에서 상품 종류별 개수

    public int[] solution(String[] gems) {

        // 초기화
        minSize = gems.length;
        result1 = 0; result2 = gems.length - 1;

        // 종류 수 체크
        Set<String> set = new HashSet();
        for (String g : gems) {
            set.add(g);
        }
        int min = set.size();
        int lo = min;
        int hi = gems.length;

        // 파라매트릭 서치
        while (lo <= hi) {

            // 초기화
            map = new HashMap<>();
            set = new HashSet<>();
            int mid = (lo + hi) / 2;
            for (int i = 0; i < mid; i++) {
                map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
                set.add(gems[i]);
            }
            if (set.size() == min) {
                result1 = 0;
                result2 = mid - 1;
                hi = mid - 1;
                continue;
            }

            boolean check = false;
            for (int i = 1; i + mid <= gems.length; i++) {
                // 앞부분 제거
                map.put(gems[i - 1], map.get(gems[i - 1]) - 1);
                if (map.get(gems[i - 1]) == 0)
                    set.remove(gems[i - 1]);

                // 뒷부분 추가
                map.put(gems[i + mid - 1], map.getOrDefault(gems[i + mid - 1], 0) + 1);
                if (map.get(gems[i + mid - 1]) == 1)
                    set.add(gems[i + mid - 1]);

                // 조건 만족 확인
                if (set.size() == min) {
                    result1 = i;
                    result2 = i + mid - 1;
                    hi = mid - 1;
                    check = true;
                    break;
                }
            }

            if (check == false) lo = mid + 1;
        }

        int[] answer = {result1 + 1, result2 + 1};
        return answer;
    }
} */