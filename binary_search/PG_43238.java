package programmers;

public class PG_43238 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            long result = solution.solution(6, new int[]{7, 10});
            System.out.println("result = " + result);
        }
}

class Solution {

    public long solution(int n, int[] times) {

        long lo = 1L;
        long hi = (long)times[0] * (long)n;
        long answer = hi;
        while (lo <= hi) {
            long mid = (lo + hi) / 2L;

            // 가능한지 체크
            long result = 0L;
            for (int time : times) {
                result += (mid / time); // 검사 가능한 사람 수 더하기
            }

            // 범위 조정
            if (result >= n) { // 조건 만족 -> 더 짧은 시간으로
                answer = Math.min(answer, mid);
                hi = mid - 1;
            }
            else { // 조건 불만족 -> 더 긴 시간으로
                lo = mid + 1;
            }
        }

        return answer;
    }
}