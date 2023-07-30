import java.util.*;

public class Main {
  static int[] tree = new int[10001];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int idx = 0;
    while (sc.hasNext()) tree[idx++] = sc.nextInt();

    postOrder(0, idx - 1);
  }

  static void postOrder(int n, int end) {
    if (n > end) return;

    int mid = n + 1;
    while (mid <= end && tree[mid] < tree[n]) mid++;

    postOrder(n + 1, mid - 1);
    postOrder(mid, end);
    System.out.println(tree[n]);
  }
}

/* 이진 트리 직접 구현
import java.io.*;

public class Main {
  static class Node {
    int num;
    Node left, right;

    Node(int num) {
      this.num = num;
    }

    Node(int num, Node left, Node right) {
      this.num = num;
      this.left = left;
      this.right = right;
    }

    void insert(int n) {
      if (n < this.num) {
        if (this.left == null) this.left = new Node(n);
        else this.left.insert(n);
      } else {
        if (this.right == null) this.right = new Node(n);
        else this.right.insert(n);
      }
    }
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Node root = new Node(Integer.parseInt(br.readLine()));
    String input;
    while(true) {
      input = br.readLine();
      if (input == null || input.equals("")) break;
      root.insert(Integer.parseInt(input));
    }

    postOrder(root);
  }

  static void postOrder(Node node) {
    if (node == null) return;

    postOrder(node.left);
    postOrder(node.right);
    System.out.println(node.num);
  }
}
*/

/*
import java.util.*;

class Node {
  int num;
  Node left, right;

  public Node(int num) {
    this.num = num;
  }
}

class Main {

  public static Node head;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    head = new Node(n);

    while(sc.hasNext()) {
      n = sc.nextInt();
      insertNode(head, new Node(n));
    }

    // 후위 순회
    postorder(head);
  }

  public static void insertNode(Node parent, Node child) {
    if (parent.num < child.num) {
      if (parent.right == null) parent.right = child;
      else insertNode(parent.right, child);
    }
    else {
      if (parent.left == null) parent.left = child;
      else insertNode(parent.left, child);
    }
  }

  public static void postorder(Node node) {
    if (node == null) return;
    postorder(node.left);
    postorder(node.right);
    System.out.println(node.num);
  }
}
*/