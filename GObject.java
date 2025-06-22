public class GObject {
    private int objectId;
    private int size;
    private Color color;
    private Integer binId;
    
    // AVL tree properties
    public GObject left;
    public GObject right;
    public int height;
    
    public GObject(int objectId, int size, Color color) {
        this.objectId = objectId;
        this.size = size;
        this.color = color;
        this.binId = null;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
    
    // Getters and setters
    public int getObjectId() {
        return objectId;
    }
    
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Integer getBinId() {
        return binId;
    }
    
    public void setBinId(Integer binId) {
        this.binId = binId;
    }
}