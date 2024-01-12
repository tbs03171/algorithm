package programmers;
import java.util.*;

public class PG_152995 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[][]{{2,2},{1,4},{3,2},{3,2},{2,1}});
            System.out.println("result = " + result);
        }
}

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;

        // 완호 점수 저장
        int wanhoScore = scores[0][0] + scores[0][1];
        int[] wanho = {scores[0][0], scores[0][1]};

        // 근무 태도 점수 내림차순, 동료 평가 점수 오름차순 정렬
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : -(a[0] - b[0]));

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < scores.length; i++) {
            if (maxValue < scores[i][1]) // 동료 평가 점수 max 갱신
                maxValue = scores[i][1];
            else if (maxValue > scores[i][1]) { // 인센티브 받지 못하는 경우 -> 카운팅 하지 않고 지나감
                if (scores[i][0] == wanho[0] && scores[i][1] == wanho[1])
                    return -1;
                continue;
            }

            if (scores[i][0] + scores[i][1] > wanhoScore)
                answer++;
        }

        return answer;
    }
}