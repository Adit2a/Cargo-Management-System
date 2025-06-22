import java.util.List;

public class Bin {
    private int binId;
    private int capacity;
    private int remainingCapacity;
    private AVLTree avl;
    
    // AVL tree properties
    public Bin left;
    public Bin right;
    public int height;
    public int minRemainingCapacity;
    public int maxRemainingCapacity;
    
    public Bin(int binId, int capacity) {
        this.binId = binId;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.avl = new AVLTree();
        this.left = null;
        this.right = null;
        this.height = 1;
        this.minRemainingCapacity = remainingCapacity;
        this.maxRemainingCapacity = remainingCapacity;
    }
    
    public void addObject(GObject obj) {
        if (this.remainingCapacity >= obj.getSize()) {
            this.avl.insertValue(obj.getObjectId());
            this.remainingCapacity -= obj.getSize();
            obj.setBinId(this.binId);
        } else {
            System.out.println("no space available");
        }
    }
    
    public void removeObject(GObject obj) {
        this.avl.deleteValue(obj.getObjectId());
        obj.setBinId(null);
        this.remainingCapacity += obj.getSize();
    }
    
    public List<Integer> getObjectIds() {
        return this.avl.inorderTraversal();
    }
    
    // Getters and setters
    public int getBinId() {
        return binId;
    }
    
    public void setBinId(int binId) {
        this.binId = binId;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public int getRemainingCapacity() {
        return remainingCapacity;
    }
    
    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }
    
    public int getMinRemainingCapacity() {
        return minRemainingCapacity;
    }
    
    public void setMinRemainingCapacity(int minRemainingCapacity) {
        this.minRemainingCapacity = minRemainingCapacity;
    }
    
    public int getMaxRemainingCapacity() {
        return maxRemainingCapacity;
    }
    
    public void setMaxRemainingCapacity(int maxRemainingCapacity) {
        this.maxRemainingCapacity = maxRemainingCapacity;
    }
}