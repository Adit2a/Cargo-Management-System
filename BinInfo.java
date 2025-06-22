import java.util.List;

public class BinInfo {
    private int remainingCapacity;
    private List<Integer> objectIds;
    
    public BinInfo(int remainingCapacity, List<Integer> objectIds) {
        this.remainingCapacity = remainingCapacity;
        this.objectIds = objectIds;
    }
    
    public int getRemainingCapacity() {
        return remainingCapacity;
    }
    
    public List<Integer> getObjectIds() {
        return objectIds;
    }
    
    @Override
    public String toString() {
        return "BinInfo{" +
               "remainingCapacity=" + remainingCapacity +
               ", objectIds=" + objectIds +
               '}';
    }
}