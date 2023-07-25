import java.util.*;
import java.io.*;

public class Main {

  static Node head = new Node('A', null, null);

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      char root = st.nextToken().charAt(0);
      char left = st.nextToken().charAt(0);
      char right = st.nextToken().charAt(0);

      insertNode(head, root, left, right);
    }

    preOrder(head);
    System.out.println();
    inOrder(head);
    System.out.println();
    postOrder(head);
    
  }

  static class Node {
    char value;
    Node left;
    Node right;

    Node(char value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }

  public static void insertNode(Node temp, char root, char left, char right) {

    if (temp.value == root) {
      temp.left = (left == '.' ? null : new Node(left, null, null));
      temp.right = (right == '.' ? null : new Node(right, null, null));
    }
    else {
      if (temp.left != null) insertNode(temp.left, root, left, right);
      if (temp.right != null) insertNode(temp.right, root, left, right);
    }
  }

  public static void preOrder(Node node) {
    if (node == null) return;
    System.out.print(node.value);
    preOrder(node.left);
    preOrder(node.right);
  }

  public static void inOrder(Node node) {
    if (node == null) return;
    inOrder(node.left);
    System.out.print(node.value);
    inOrder(node.right);
  }

  public static void postOrder(Node node) {
    if (node == null) return;
    postOrder(node.left);
    postOrder(node.right);
    System.out.print(node.value);
  }
}

/* 나의 풀이
import java.util.*;

class Node {
  private int n, left, right;

  public Node(int n, int left, int right) {
    this.n = n;
    this.left = left;
    this.right = right;
    
  }
}

class Main {

  public static int N;
  public static int[][] graph;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    graph = new int[N][2]; // graph[N][0] : N번 노드의 왼쪽 자식 graph[N][1] : 오른쪽 자식
    sc.nextLine();

    // 트리 입력
    for (int i = 0; i < N; i++) {
      String[] arr = sc.nextLine().split(" ");
      int now = arr[0].charAt(0) - 'A';
      char left = arr[1].charAt(0);
      char right = arr[2].charAt(0);
      
      // 왼쪽 자식 넣기
      if (left == '.') {
        graph[now][0] = -1;
      } else {
        graph[now][0] = left - 'A';
      }

      // 오른쪽 자식 넣기
      if (right == '.') {
        graph[now][1] = -1;
      } else {
        graph[now][1] = right - 'A';
      }
    }

    preorder(0);
    System.out.println();
    inorder(0);
    System.out.println();
    postorder(0);
  }

  public static void preorder(int root) {
    // 루트 방문
    System.out.print((char)('A' + root));

    // 왼쪽 방문
    if (graph[root][0] != -1) preorder(graph[root][0]);

    // 오른쪽 방문
    if (graph[root][1] != -1) preorder(graph[root][1]);
  }

  public static void inorder(int root) {
    // 왼쪽 방문
    if (graph[root][0] != -1) inorder(graph[root][0]);

    // 루트 방문
    System.out.print((char)('A' + root));

    // 오른쪽 방문
    if (graph[root][1] != -1) inorder(graph[root][1]);
  }

  public static void postorder(int root) {
    // 왼쪽 방문
    if (graph[root][0] != -1) postorder(graph[root][0]);

    // 오른쪽 방문
    if (graph[root][1] != -1) postorder(graph[root][1]);

    // 루트 방문
    System.out.print((char)('A' + root));
  }
}
*/