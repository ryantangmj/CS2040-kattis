import java.io.*;
import java.util.HashMap;

class Nicknames {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        AVLTree tree = new AVLTree();

        int numNames = Integer.parseInt(br.readLine());
        for (int i = 0; i < numNames; i++) {
            tree.insert(br.readLine());
        }

        int numNickNames = Integer.parseInt(br.readLine());
        HashMap<String, Integer> record = new HashMap<String, Integer>();

        for (int i = 0; i < numNickNames; i++) {
            String nickname = br.readLine();
            if (record.containsKey(nickname)) {
                pw.println(record.get(nickname) + "\n");
            } else {
                int matches = tree.search(nickname);
                record.put(nickname, matches);
                pw.println(matches + "\n");
            }
        }

        pw.close();
    }
}

class Node {
    String key;
    int height;
    Node parent;
    Node left;
    Node right;

    Node(String key) {
        this.key = key;
        this.height = 0;
    }
}

class AVLTree { 
    public Node root; 

    public AVLTree() {
        root = null;
    }
  
    public int height(Node N) { 
        return N == null ? -1 : N.height;
    } 

    public int search(String v) {
        return search(root, v);
    }

    public int search(Node N, String v) {
        if (N == null) {
            return 0;      
        } else {
            String nKey = N.key;
            if (nKey.indexOf(v) == 0) {
                return 1 + search(N.left, v) + search(N.right, v);
            } else if (v.compareTo(nKey) > 0) {
                return search(N.right, v);  
            } else {
                return search(N.left, v);
            } 
        }               
    }

    Node rightRotate(Node y) { 
        Node x = y.left; 

        x.parent = y.parent;
        y.parent = x; 
        y.left = x.right; 
        if(x.right != null) {
            x.right.parent = y;
        }
        x.right = y;

        updateHeight(x);
        updateHeight(y);
        return x; 
    } 
  
    Node leftRotate(Node x) { 
        Node y = x.right; 
  
        y.parent = x.parent;
        x.parent = y;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.left = x; 

        updateHeight(x);
        updateHeight(y);
        return y; 
    } 
  
    int getBalance(Node N) { 
        return N == null ? 0 : height(N.left) - height(N.right);
    } 

    public void insert(String v) { 
        root = insert(root, v);
    }

    Node insert(Node node, String v) { 
        if (node == null) {
            return (new Node(v)); 
        }

        if (v.compareTo(node.key) < 0) { 
            node.left = insert(node.left, v); 
            node.left.parent = node;
        } else {
            node.right = insert(node.right, v);
            node.right.parent = node;   
        } 
            
        updateHeight(node);
  
        int balance = getBalance(node); 

        if (balance == 2) {
            if (getBalance(node.left) == -1) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (balance == -2) {
            if (getBalance(node.right) == 1) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        return node;
    }

    public void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), 
                              height(node.right)); 
    }
}