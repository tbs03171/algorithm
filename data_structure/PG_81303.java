package programmers;
import java.util.*;

public class PG_81303 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            String result = solution.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"});
            System.out.println("result = " + result);
        }
}

class Solution {

    public String solution(int n, int k, String[] cmd) {

        StringBuilder builder = new StringBuilder();
        int rowNum = n;
        Stack<Integer> deleted = new Stack<>();

        for (String tmp : cmd) {
            if (tmp.length() > 1) {
                int x = Integer.parseInt(tmp.substring(2));
                if (tmp.charAt(0) == 'U') k -= x;
                else k += x;
            }
            else if (tmp.charAt(0) == 'C') {
                deleted.push(k);
                rowNum--;
                if (rowNum == k) k--;
            }
            else {
                rowNum++;
                if (deleted.pop() <= k) k++;
            }
        }

        for (int i = 0; i < rowNum; i++)
            builder.append("O");
        while(!deleted.empty())
            builder.insert(deleted.pop(), "X");

        return builder.toString();
    }
}

/* 연결리스트 + Stack 활용한 풀이
class Solution {

    public static class Node {
        int pre;
        int cur;
        int next;

        Node(int pre, int cur, int next) {
            this.pre = pre;
            this.cur = cur;
            this.next = next;
        }
    }

    public static String solution(int n, int k, String[] cmd) {

        // pre, next 초기화
        int[] pre = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        Stack<Node> delStack = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));
        int now = k;

        for (String c : cmd) {
            String[] op = c.split(" ");

            if (op[0].equals("U")) {
                int x = Integer.parseInt(op[1]);
                while (x-- > 0)
                    now = pre[now];
            }
            else if (op[0].equals("D")) {
                int x = Integer.parseInt(op[1]);
                while (x-- > 0)
                    now = next[now];
            }
            else if (op[0].equals("C")) {
                delStack.push(new Node(pre[now], now, next[now]));
                if (pre[now] != -1)
                    next[pre[now]] = next[now];
                if (next[now] != -1)
                    pre[next[now]] = pre[now];
                sb.setCharAt(now, 'X');

                if (next[now] != -1)
                    now = next[now];
                else
                    now = pre[now];
            }
            else {
                Node node = delStack.pop();

                if (node.pre != -1)
                    next[node.pre] = node.cur;
                if (now.next != -1)
                    pre[node.next] = node.cur;

                sb.setCharAt(node.cur, 'O');
            }
        }

        return sb.toString();
    }
} */

/* 나의 풀이
class Solution {

    public Stack<Node> stack = new Stack<>();

    public String solution(int n, int k, String[] cmd) {

        // 양방향 연결리스트 생성
        Node start = new Node(-1);
        Node before = start;
        for (int i = 0; i <= n; i++) {
            Node node = new Node(i);
            node.left = before;
            before.right = node;
            before = node;
        }

        // 현재 선택된 노드까지 이동
        Node now = start;
        for (int i = 0; i <= k; i++) {
            now = now.right;
        }

        // 명령 수행
        for (String command : cmd) {
            String[] splits = command.split(" ");

            // D
            if (splits[0].equals("D")) {
                int dist = Integer.parseInt(splits[1]);
                for (int i = 0; i < dist; i++) {
                    now = now.right;
                }
            }
            // U
            else if (splits[0].equals("U")) {
                int dist = Integer.parseInt(splits[1]);
                for (int i = 0; i < dist; i++) {
                    now = now.left;
                }
            }
            // C
            else if (splits[0].equals("C")) {
                // 삭제 처리
                now.left.right = now.right;
                now.right.left = now.left;

                // 스택에 넣기
                stack.push(now);

                // now 변경
                if (now.right.x == n)
                    now = now.left;
                else
                    now = now.right;
            }
            // Z
            else {
                // 스택에서 꺼내기
                Node node = stack.pop();

                // 포인터 변경
                node.left.right = node;
                node.right.left = node;
            }
        }

        // 문자열 만들기
        now = start.right;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (now.x != i)
                sb.append("X");
            else {
                sb.append("O");
                now = now.right;
            }
        }

        return sb.toString();
    }

    public class Node {
        int x;
        Node left;
        Node right;

        public Node(int x) {
            this.x = x;
            this.left = null;
            this.right = null;
        }
    }
} */