package programmers;

public class PG_64062 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
            System.out.println("result = " + result);
        }
}

class Solution {

    public int solution(int[] stones, int k) {
        int answer = 0;

        // 건너는 최소 인원 수
        int lowCount = 1;

        // 건너는 최대 인원 수
        int highCount = Integer.MAX_VALUE;

        // 이분 탐색에서 이용할 중간값
        int averageCount = (lowCount + highCount) / 2;

        while (lowCount <= highCount) {
            averageCount = (lowCount + highCount) / 2;

            // 건너뛴 징검다리 수
            int jumpCount = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] - averageCount <= 0) {
                    jumpCount++;
                } else {
                    jumpCount = 0;
                }

                // 조건 만족 X (k개 이상 건너뜀) -> 인원 수 줄임
                if (jumpCount >= k) {
                    highCount = averageCount - 1;
                    break;
                }
            }

            // 조건 만족 O (k개 미만 건너뜀) -> 인원 수 늘림
            if (jumpCount < k) {
                lowCount = averageCount + 1;
                answer = lowCount;
            }
        }

        return answer;
    }
}

/* 나의 풀이
class Solution {

    PriorityQueue<Node> pq = new PriorityQueue<>();
    int count = 0; // 징검다리 건넌 사람 수
    int[] arr;
    boolean check = true;

    public int solution(int[] stones, int k) {

        // arr 초기화
        arr = new int[stones.length + 2];

        // 우선순위 큐에 (인덱스, 수) 저장
        for (int i = 0; i < stones.length; i++) {
            pq.offer(new Node(i + 1, stones[i]));
        }

        while (true) {
            count = pq.peek().n; // 건넘 처리

            // pq에서 0이 된 바위들 모두 꺼내기
            while (pq.peek().n == count) {
                // 꺼내기
                Node node = pq.poll();
                int n = node.n;
                int index = node.index;

                // 연결
                arr[index] = index;
                int start = 0;
                int end = 0;
                // 왼쪽 연결
                if (arr[index - 1] != 0) {
                    start = arr[index - 1];
                    end = arr[index];
                    arr[start] = end;
                    arr[end] = start;
                }
                // 오른쪽 연결
                if (arr[index + 1] != 0) {
                    start = arr[index];
                    end = arr[index + 1];
                    arr[start] = end;
                    arr[end] = start;
                }

                // 연결 후 구간 크기 확인
                if (end - start >= k - 1) {
                    check = false;
                    break;
                }
            }

            if (check == false) break;
            if (pq.isEmpty()) break; // 큐가 비었다면 멈춤
        }
        return count;
    }

    class Node implements Comparable<Node> {
        int index;
        int n;

        public Node(int index, int n) {
            this.index = index;
            this.n = n;
        }

        @Override
        public int compareTo(Node other) {
            if (this.n == other.n) return Integer.compare(this.index, other.index);
            else return Integer.compare(this.n, other.n);
        }
    }
} */