import java.util.*;

/* 스택 사용한 풀이 */
class Solution {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        // 배달, 수거 스택 초기화
        Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) dStack.add(i);
            if (pickups[i] > 0) pStack.add(i);
        }

        while (!dStack.empty() && !pStack.empty()) {
            answer += Math.max((dStack.peek() + 1) * 2, (pStack.peek() + 1) * 2);

            // 배달
            int box = 0;
            while (!dStack.empty() && box <= cap) {
                if (deliveries[dStack.peek()] + box <= cap) // 배달 가능
                    box += deliveries[dStack.peek()];
                else { // 용량 초과
                    deliveries[dStack.peek()] -= (cap - box);
                    break;
                }
                dStack.pop();
            }

            // 수거
            box = 0;
            while (!pStack.empty() && box <= cap) {
                if (pickups[pStack.peek()] + box <= cap) // 수거 가능
                    box += pickups[pStack.peek()];
                else {
                    pickups[pStack.peek()] -= (cap - box);
                    break;
                }
                pStack.pop();
            }
        }

        // dStack만 남음
        while (!dStack.empty()) {
            answer += (dStack.peek() + 1) * 2;

            int box = 0;
            while (!dStack.empty() && box <= cap) {
                if (deliveries[dStack.peek()] + box <= cap) // 배달 가능
                    box += deliveries[dStack.peek()];
                else { // 용량 초과
                    deliveries[dStack.peek()] -= (cap - box);
                    break;
                }
                dStack.pop();
            }
        }

        // pStack만 남음
        while (!pStack.empty()) {
            answer += (pStack.peek() + 1) * 2;

            int box = 0;
            while (!pStack.empty() && box <= cap) {
                if (pickups[pStack.peek()] + box <= cap) // 수거 가능
                    box += pickups[pStack.peek()];
                else {
                    pickups[pStack.peek()] -= (cap - box);
                    break;
                }
                pStack.pop();
            }
        }

        return answer;
    }
}

/* 
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long answer = 0;

        // 배달, 수거 가능 용량
        int d = 0;
        int p = 0;

        // 제일 먼 집부터
        for (int i = n - 1; i >= 0; i--) {
            d -= deliveries[i];
            p -= pickups[i];

            // 배달 용량 초과 -> 왕복 거리 추가 및 용량 + cap
            while(d < 0 || p < 0) {
                d += cap;
                p += cap;
                answer += (i + 1) * 2;
            }
        }

        return answer;
    }
}
*/

/* 나의 풀이
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 배달 해야될 인덱스, 수거 해야될 인덱스
        int idxD = -1;
        int idxP = -1;

        // 배달, 수거 시작 인덱스 초기화
        for (int i = deliveries.length - 1; i >= 0; i--) {
            if (deliveries[i] > 0) {
                idxD = i;
                break;
            }
        }
        for (int i = pickups.length - 1; i >= 0; i--) {
            if (pickups[i] > 0) {
                idxP = i;
                break;
            }
        }

        // 모든 배달, 수거 완료할 때까지 반복
        long answer = 0;
        while (true) {
            if (idxD == -1 && idxP == -1) break;

            // 수거 인덱스, 배달 인덱스 중 큰 값을 업뎃
            answer = answer + Math.max(idxD + 1, idxP + 1) * 2;

            // 배달 처리
            int capD = cap;
            while (idxD >= 0) {
                if (deliveries[idxD] <= capD) { // 모두 배달 가능
                    capD = capD - deliveries[idxD];
                    deliveries[idxD] = 0;
                    idxD--;
                } else { // 일부만 배달 가능
                    deliveries[idxD] = deliveries[idxD] - capD;
                    capD = 0;
                    break;
                }
            }
            // 수거 처리
            int capP = cap;
            while (idxP >= 0) {
                if (pickups[idxP] <= capP) { // 모두 수거 가능
                    capP = capP - pickups[idxP];
                    pickups[idxP] = 0;
                    idxP--;
                } else { // 일부만 수거 가능
                    pickups[idxP] = pickups[idxP] - capP;
                    capP = 0;
                    break;
                }
            }
        }

        return answer;
    }
} */