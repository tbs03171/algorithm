package programmers;
import java.util.*;

public class PG_42892 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int[][] result = solution.solution(new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
            for (int[] r : result) {
                for (int n : r) {
                    System.out.print(n + " ");
                }
                System.out.println();
            }
        }
}

class Solution {

    public List<Integer> preList = new ArrayList<>();
    public List<Integer> postList = new ArrayList<>();

    public int[][] solution(int[][] nodeinfo) {

        // 정렬 (y 내림차, x 오름차)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            pq.offer(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        // 트리 구성
        Node root = pq.poll();
        while (!pq.isEmpty()) {
            insert(root, pq.poll());
        }

        // 전위 순회
        preorder(root);

        // 후위 순회
        postorder(root);

        int[][] answer = new int[2][nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = preList.get(i);
            answer[1][i] = postList.get(i);
        }
        return answer;
    }

    public void preorder(Node root) {
        preList.add(root.index);
        if (root.left != null)
            preorder(root.left);
        if (root.right != null)
            preorder(root.right);
    }

    public void postorder(Node root) {
        if (root.left != null)
            postorder(root.left);
        if (root.right != null)
            postorder(root.right);
        postList.add(root.index);
    }

    public void insert(Node root, Node node) {
        if (node.x < root.x) { // 왼쪽 자식으로 추가
            if (root.left == null)
                root.left = node;
            else
                insert(root.left, node);
        }
        else { // 오른쪽 자식으로 추가
            if (root.right == null)
                root.right = node;
            else
                insert(root.right, node);
        }
    }

    public class Node implements Comparable<Node> {
        int index;
        int x;
        int y;
        Node left = null;
        Node right = null;

        public Node (int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node other) {
            if (this.y == other.y)
                return this.x - other.x;
            return other.y - this.y;
        }
    }
}