public class ObjAVLTree {
    private GObject root;
    
    public ObjAVLTree() {
        this.root = null;
    }
    
    private int height(GObject node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    private int balance(GObject node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    private GObject insert(GObject root, GObject obj1) {
        if (root == null) {
            return obj1;
        } else if (obj1.getObjectId() < root.getObjectId()) {
            root.left = insert(root.left, obj1);
        } else {
            root.right = insert(root.right, obj1);
        }
        
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = balance(root);
        
        // Left rotation
        if (balance > 1 && obj1.getObjectId() < root.left.getObjectId()) {
            return rightRotate(root);
        }
        
        // Right rotation
        if (balance < -1 && obj1.getObjectId() > root.right.getObjectId()) {
            return leftRotate(root);
        }
        
        // Left-Right rotation
        if (balance > 1 && obj1.getObjectId() > root.left.getObjectId()) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        
        // Right-Left rotation
        if (balance < -1 && obj1.getObjectId() < root.right.getObjectId()) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    
    private GObject delete(GObject root, int value) {
        if (root == null) {
            return root;
        }
        
        if (value < root.getObjectId()) {
            root.left = delete(root.left, value);
        } else if (value > root.getObjectId()) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            
            GObject temp = minValueNode(root.right);
            root = temp;
            root.right = delete(root.right, temp.getObjectId());
        }
        
        if (root == null) {
            return root;
        }
        
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = balance(root);
        
        // Balance the tree
        if (balance > 1 && balance(root.left) >= 0) {
            return rightRotate(root);
        }
        
        if (balance < -1 && balance(root.right) <= 0) {
            return leftRotate(root);
        }
        
        if (balance > 1 && balance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        
        if (balance < -1 && balance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }
    
    private GObject leftRotate(GObject z) {
        GObject y = z.right;
        GObject T2 = y.left;
        
        y.left = z;
        z.right = T2;
        
        z.height = 1 + Math.max(height(z.left), height(z.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        
        return y;
    }
    
    private GObject rightRotate(GObject z) {
        GObject y = z.left;
        GObject T3 = y.right;
        
        y.right = z;
        z.left = T3;
        
        z.height = 1 + Math.max(height(z.left), height(z.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        
        return y;
    }
    
    private GObject minValueNode(GObject root) {
        GObject current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    private GObject search(GObject root, int value) {
        if (root == null || root.getObjectId() == value) {
            return root;
        }
        if (root.getObjectId() < value) {
            return search(root.right, value);
        } else {
            return search(root.left, value);
        }
    }
    
    public void insertValue(GObject obj) {
        this.root = insert(this.root, obj);
    }
    
    public void deleteValue(int value) {
        this.root = delete(this.root, value);
    }
    
    public GObject searchValue(int value) {
        return search(this.root, value);
    }
    
    public void insertObj(GObject obj) {
        insertValue(obj);
    }
    
    public GObject findObj(int objId) {
        return searchValue(objId);
    }
    
    public void deleteObj(int objId) {
        deleteValue(objId);
    }
}