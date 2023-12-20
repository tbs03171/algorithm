package programmers;
import java.util.*;

public class PG_72411 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            String[] results = solution.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
            for (String result : results) {
                System.out.print(result + " ");
            }
        }
}

class Solution {

    private HashMap<String, Integer> map;
    private int max;

    public String[] solution(String[] orders, int[] course) {

        ArrayList<String> list = new ArrayList<>();

        for (int item : course) {
            map = new HashMap<>();

            max = 2;

            for (String order : orders) {
                // 주문한 메뉴가 추가하려는 코스요리 개수 이상일 경우
                if (order.length() >= item) {
                    boolean[] isVisit = new boolean[order.length()];
                    char[] texts = order.toCharArray();
                    Arrays.sort(texts);
                    combination(texts, isVisit, 0, item);
                }
            }

            // 최대값인 경우만 골라내기
            map.forEach((s, integer) -> {
                // 요소가 최대값일 경우
                if (integer == max)
                    list.add(s);
            });
        }

        return list.stream().sorted().toArray(String[]::new);
    }

    /*
     * 조합 메서드
     */
    private void combination(char[] texts, boolean[] isVisit, int start, int target) {

        // 탐색 끝
        if (target == 0) {
            // 조합 결과 생성
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < texts.length; i++) {
                if (isVisit[i]) sb.append(texts[i]); // 선택된 메뉴
            }

            // map 에 넣기
            String key = sb.toString();
            int value = map.getOrDefault(key, 0) + 1;
            map.put(key, value);
            max = Math.max(max, value); // max 갱신
            return;
        }

        // 재귀 탐색
        for (int i = start; i < texts.length; i++) {
            isVisit[i] = true;
            combination(texts, isVisit, i + 1, target - 1);
            isVisit[i] = false;
        }
    }
}

/* 나의 풀이
class Solution {

    Map<Integer, Map<String, Integer>> map = new HashMap<>(); // <메뉴 개수, <메뉴 구성, 주문 수>>

    public String[] solution(String[] orders, int[] course) {

        // map 초기화
        for (int c : course)
            map.put(c, new HashMap<String, Integer>());

        // 모든 주문에 대해 메뉴 개수별 조합 생성 -> Map에 넣기
        for (String order : orders) {
            // order을 char[]로 바꿔서 정렬
            char[] orderArr = order.toCharArray();
            Arrays.sort(orderArr);

            // 메뉴 개수별 조합 생성
            for (int r : course) {
                if (orderArr.length < r) break; // 뽑을 개수가 총 개수보다 크면 멈춤
                combination(orderArr, r, 0, 0, new char[r]);
            }
        }

        // map에서 메뉴 구성 결과 가져와서 정렬
        List<String> list = new ArrayList<>();
        for (int c : course) {
            int max = 1;
            List<String> temp = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.get(c).entrySet()) {
                if (entry.getValue() > 1 && entry.getValue() == max) // max 와 같다면 temp에 넣기
                    temp.add(entry.getKey());
                if (entry.getValue() > max) { // max 보다 크다면 temp 초기화하고 넣기
                    temp = new ArrayList<>();
                    max = entry.getValue();
                    temp.add(entry.getKey());
                }
            }
            list.addAll(temp);
        }
        Collections.sort(list);

        // 결과 배열로 변환 후 반환
        String[] answer = new String[list.size()];
        int index = 0;
        for (String str : list) {
            answer[index] = str;
            index++;
        }
        return answer;
    }

    // input : 전체 항목, r : 뽑을 개수, count : 뽑은 개수, at : 현재 탐색 위치, result : 현재까지의 조합 결과
    public void combination(char[] input, int r, int count, int at, char[] result) {

        // 다 뽑은 경우
        if (r == count) {

            // 결과 조합 생성
            StringBuilder sb = new StringBuilder();
            for (char c : result) {
                sb.append(c);
            }

            // 맵에 저장
            if (map.get(r).containsKey(sb.toString()))
                map.get(r).put(sb.toString(), map.get(r).get(sb.toString()) + 1);
            else
                map.get(r).put(sb.toString(), 1);
            return;
        }

        if (at >= input.length) return; // 범위 벗어남

        // 재귀 탐색
        result[count] = input[at];
        combination(input, r, count + 1, at + 1, result); // 현재 문자 뽑음
        combination(input, r, count, at + 1, result); // 현재 문자 뽑지 않음
    }
} */