import java.util.List;
import java.util.ArrayList;

public class AVLTree {
    private Node root;
    
    public AVLTree() {
        this.root = null;
    }
    
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    private int balance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    private Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        } else if (value < root.value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = balance(root);
        
        // Left rotation
        if (balance > 1 && value < root.left.value) {
            return rightRotate(root);
        }
        
        // Right rotation
        if (balance < -1 && value > root.right.value) {
            return leftRotate(root);
        }
        
        // Left-Right rotation
        if (balance > 1 && value > root.left.value) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        
        // Right-Left rotation
        if (balance < -1 && value < root.right.value) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    
    private Node delete(Node root, int value) {
        if (root == null) {
            return root;
        }
        
        if (value < root.value) {
            root.left = delete(root.left, value);
        } else if (value > root.value) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null) {
                Node temp = root.right;
                root = null;
                return temp;
            } else if (root.right == null) {
                Node temp = root.left;
                root = null;
                return temp;
            }
            
            Node temp = minValueNode(root.right);
            root.value = temp.value;
            root.right = delete(root.right, temp.value);
        }
        
        if (root == null) {
            return root;
        }
        
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = balance(root);
        
        // Left rotation
        if (balance > 1 && balance(root.left) >= 0) {
            return rightRotate(root);
        }
        
        // Right rotation
        if (balance < -1 && balance(root.right) <= 0) {
            return leftRotate(root);
        }
        
        // Left-Right rotation
        if (balance > 1 && balance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        
        // Right-Left rotation
        if (balance < -1 && balance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    
    private Node leftRotate(Node z) {
        Node y = z.right;
        Node T2 = y.left;
        
        y.left = z;
        z.right = T2;
        
        z.height = 1 + Math.max(height(z.left), height(z.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        
        return y;
    }
    
    private Node rightRotate(Node z) {
        Node y = z.left;
        Node T3 = y.right;
        
        y.right = z;
        z.left = T3;
        
        z.height = 1 + Math.max(height(z.left), height(z.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        
        return y;
    }
    
    private Node minValueNode(Node root) {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    private Node search(Node root, int value) {
        if (root == null || root.value == value) {
            return root;
        }
        if (root.value < value) {
            return search(root.right, value);
        }
        return search(root.left, value);
    }
    
    public void insertValue(int value) {
        this.root = insert(this.root, value);
    }
    
    public void deleteValue(int value) {
        this.root = delete(this.root, value);
    }
    
    public Node searchValue(int value) {
        return search(this.root, value);
    }
    
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(this.root, result);
        return result;
    }
    
    private void inorderTraversal(Node root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.value);
            inorderTraversal(root.right, result);
        }
    }
}