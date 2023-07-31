import java.util.*;

class Node {
    int index, distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
}

public class Main {

    public static int N, K;
    public static int min = Integer.MAX_VALUE;
    public static boolean[] visited;
    public static int max = 100000;

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(N, 0));

        while(!q.isEmpty()) {
            Node node = q.poll();
          
            visited[node.index] = true;
            if (node.index == K) min = Math.min(min, node.distance);

            if (node.index * 2 <= max && visited[node.index * 2] == false) q.offer(new Node(node.index * 2, node.distance));
            if (node.index + 1 <= max && visited[node.index + 1] == false) q.offer(new Node(node.index + 1, node.distance + 1));
            if (node.index - 1 >= 0 && visited[node.index - 1] == false) q.offer(new Node(node.index - 1, node.distance + 1));

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        visited = new boolean[max + 1];

        bfs();

        System.out.println(min);

    }
}
