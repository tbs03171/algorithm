package programmers;

public class PG_68646 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[]{-16,27,65,-2,58,-92,-71,-68,-61,-33});
            System.out.println(result);
        }
}

class Solution {

    public int solution(int[] a) {

        int answer = 2; // 맨 왼쪽, 맨 오른쪽은 항상 가능

        int[] left = new int[a.length];
        int[] right = new int[a.length];

        // i 위치에서의 왼쪽 최솟값 저장
        int min = a[0];
        for (int i = 1; i < a.length - 1; i++) {
            left[i] = min;
            min = Math.min(min, a[i]);
        }

        // i 위치에서의 오른쪽 최솟값 저장
        min = a[a.length - 1];
        for (int i = a.length - 2; i > 0; i--) {
            right[i] = min;
            min = Math.min(min, a[i]);
        }

        // 양 옆의 최솟값이 둘 다 a[i] 보다 작으면 불가능
        for (int i = 1; i < a.length - 1; i++) {
            if (left[i] < a[i] && right[i] < a[i]) continue;
            answer++;
        }

        return answer;
    }
}

/* 우선순위 큐 사용 (나의 풀이)
class Solution {
    public int solution(int[] a) {

        // a의 길이가 3 이하면 모두 가능
        if (a.length <= 3) return a.length;

        // 방문한 수 모두 표시
        Map<Integer, Boolean> map = new HashMap<>();

        // 결과 초기화
        int answer = 1;
        map.put(a[0], true);
        int leftMin = a[0];

        // 우선순위 큐에 모두 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 2; i < a.length; i++) {
            pq.offer(a[i]);
        }

        // 차례대로 탐색
        for (int i = 1; i < a.length; i++) {
            map.put(a[i], true);

            // 우선순위 큐 정리
            while (!pq.isEmpty() && map.containsKey(pq.peek())) {
                pq.poll();
            }

            // leftMin 보다 작은 경우
            if (a[i] < leftMin) {
                leftMin = a[i];
                answer++;
            }
            // leftMin 보다 큰 경우
            else {
                // rightMin 보다 작은 경우
                if (pq.isEmpty() || a[i] < pq.peek())
                    answer++;
            }
        }

        return answer;
    }
} */