import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;
        
        int[] arr = food_times.clone();
        Arrays.sort(arr);
        
        int cycle = 0; // 몇 싸이클 돌았는지
        for (int i = 0; i < arr.length; i++) {
            int remains = arr.length - i; // 남은 음식 수
            if (k < remains) break; // 남은 음식 보다 남은 초가 더 적으면 멈추기
            for (int j = cycle; j < arr[i]; j++) {
                if (k < remains) { // 남은 음식 보다 남은 초가 더 적으면 멈추기
                    break;
                } else {
                    k -= remains;
                    cycle++;
                }
            }
        }
        
        for (int i = 0; i < food_times.length; i++) {
            if (food_times[i] < cycle) {
                food_times[i] = 0;
            } else {
                food_times[i] = food_times[i] - cycle;
            }
        }
        
        return answer;
    }
}