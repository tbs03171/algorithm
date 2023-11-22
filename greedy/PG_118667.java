import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        // 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum = 0;
        long q1_sum = 0;
        for (int i = 0; i < queue1.length; i++) {
            sum += queue1[i];
            sum += queue2[i];
            q1_sum += queue1[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        long mid = sum/2;
        int cnt = queue1.length * 3;

        while (q1_sum != mid) {
            if (cnt == 0) {
                return -1;
            }
            if (q1_sum > mid) {
                int num = q1.poll();
                q1_sum -= num;
                q2.add(num);
                answer++;
            } else {
                int num = q2.poll();
                q1_sum += num;
                q1.add(num);
                answer++;
            }
            cnt--;
        }

        return answer;
    }
}

/* 나의 풀이
class Solution {
    public int solution(int[] queue1, int[] queue2) {      
        // 다 더하면서 큐에 삽입
        Queue<Integer> q1 = new LinkedList<>(); Queue<Integer> q2 = new LinkedList<>();
        long q1Sum = 0L; long q2Sum = 0L;

        for (int i = 0; i < queue1.length; i++) {
            q1Sum += queue1[i];
            q2Sum += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }

        // 
        int answer = 0;
        Long resultSum = (q1Sum + q2Sum) / 2;
        boolean check = false;
        for (int i = 0; i < queue1.length * 3; i++) {
            // 원소 합이 같으면 멈추기
            if (q1Sum == q2Sum) {
                check = true;
                break;
            }

            // 원소 pop, insert
            if (q1Sum < q2Sum) { // q1Sum이 더 작으면
                int val = q2.poll();
                q1.offer(val);
                q1Sum += val;
                q2Sum -= val;
            } else { // q2Sum이 더 작으면
                int val = q1.poll();
                q2.offer(val);
                q1Sum -= val;
                q2Sum += val;
            }
            answer++;
        }

        if (check == false) return -1;

        return answer;
    }
}*/