public class BinAVLTree {
    private Bin root;
    
    public BinAVLTree() {
        this.root = null;
    }
    
    private int height(Bin node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    private int balance(Bin node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    private Bin insert(Bin root, Bin bin1) {
        if (root == null) {
            return bin1;
        } else if (bin1.getBinId() < root.getBinId()) {
            root.left = insert(root.left, bin1);
        } else {
            root.right = insert(root.right, bin1);
        }
        
        root.height = 1 + Math.max(height(root.left), height(root.right));
        
        // Update min and max remaining capacity
        int leftMin = (root.left != null) ? root.left.getMinRemainingCapacity() : Integer.MAX_VALUE;
        int rightMin = (root.right != null) ? root.right.getMinRemainingCapacity() : Integer.MAX_VALUE;
        root.setMinRemainingCapacity(Math.min(root.getRemainingCapacity(), Math.min(leftMin, rightMin)));
        
        int leftMax = (root.left != null) ? root.left.getMaxRemainingCapacity() : Integer.MIN_VALUE;
        int rightMax = (root.right != null) ? root.right.getMaxRemainingCapacity() : Integer.MIN_VALUE;
        root.setMaxRemainingCapacity(Math.max(root.getRemainingCapacity(), Math.max(leftMax, rightMax)));
        
        int balance = balance(root);
        
        // Left rotation
        if (balance > 1 && bin1.getBinId() < root.left.getBinId()) {
            return rightRotate(root);
        }
        
        // Right rotation
        if (balance < -1 && bin1.getBinId() > root.right.getBinId()) {
            return leftRotate(root);
        }
        
        // Left-Right rotation
        if (balance > 1 && bin1.getBinId() > root.left.getBinId()) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        
        // Right-Left rotation
        if (balance < -1 && bin1.getBinId() < root.right.getBinId()) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    
    private Bin leftRotate(Bin z) {
        Bin y = z.right;
        Bin T2 = y.left;
        
        y.left = z;
        z.right = T2;
        
        z.height = 1 + Math.max(height(z.left), height(z.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        
        return y;
    }
    
    private Bin rightRotate(Bin z) {
        Bin y = z.left;
        Bin T3 = y.right;
        
        y.right = z;
        z.left = T3;
        
        z.height = 1 + Math.max(height(z.left), height(z.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        
        return y;
    }
    
    private Bin minValueNode(Bin root) {
        Bin current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    private Bin search(Bin root, int value) {
        if (root == null || root.getBinId() == value) {
            return root;
        }
        if (root.getBinId() < value) {
            return search(root.right, value);
        } else {
            return search(root.left, value);
        }
    }
    
    public void insertValue(Bin bin) {
        this.root = insert(this.root, bin);
    }
    
    public Bin searchValue(int value) {
        return search(this.root, value);
    }
    
    public void insertBin(Bin bin1) {
        insertValue(bin1);
    }
    
    public Bin findBin(int binId) {
        return searchValue(binId);
    }
    public Bin getRoot() {
    return this.root;
    }
}