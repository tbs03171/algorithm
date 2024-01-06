package programmers;
import java.util.*;

public class PG_17684 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int[] result = solution.solution("KAKAO");
            for (int n : result) {
                System.out.print(n + " ");
            }

        }
}

class Solution {

    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // map에 A ~ Z 넣기
        int count = 1;
        for (int i = 65; i <= 90; i++) {
            map.put(Character.toString((char) i), count++);
        }

        int start = 0;
        while (start < msg.length()) {
            int end = start;

            // w 찾기
            while (end + 1 <= msg.length() && map.containsKey(msg.substring(start, end + 1))) {
                end++;
            }

            // w 의 인덱스 저장
            answer.add(map.get(msg.substring(start, end)));

            // w + c를 map에 저장
            if (end < msg.length()) {
                map.put(msg.substring(start, end + 1), count++);
            }

            start = end;
        }

        // 결과 배열 생성
        int[] arr = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            arr[i] = answer.get(i);
        }
        return arr;
    }
}

/* 나의 풀이
class Solution {

    public Map<String, Integer> map = new HashMap<>();
    public Queue<Character> q = new LinkedList<>();
    public List<Integer> list = new ArrayList<>();

    public int[] solution(String msg) {

        // 모든 대문자 알파벳 map에 저장
        for (int i = 0; i < 26; i++) {
            map.put((char)(i + 'A') + "", i + 1);
        }

        // msg의 모든 문자 q에 넣기
        for (int i = 0; i < msg.length(); i++) {
            q.offer(msg.charAt(i));
        }

        // 큐가 빌 때까지
        int index = 27;
        while (true) {
            // w 찾고, w + c 넣기
            StringBuilder sb = new StringBuilder();
            sb.append(q.poll());
            while (true) {
                if (q.isEmpty()) break;
                String str = sb.toString() + q.peek();
                if (map.getOrDefault(str, 0) != 0) { // w 계속 길어지는 중
                    sb.append(q.poll());
                }
                else { // w + c 저장하고 멈춤
                    map.put(str, index);
                    list.add(map.get(sb.toString()));
                    index++;
                    break;
                }
            }

            // 마지막 부분
            if (q.isEmpty()) {
                list.add(map.get(sb.toString()));
                break;
            }
        }

        // 결과 생성
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
} */