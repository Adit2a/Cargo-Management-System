public class GCMS {
    private BinAVLTree bins;
    private ObjAVLTree objects;
    
    public GCMS() {
        this.bins = new BinAVLTree();
        this.objects = new ObjAVLTree();
    }
    
    public void addBin(int binId, int capacity) {
        Bin newBin = new Bin(binId, capacity);
        this.bins.insertBin(newBin);
    }
    
    public void addObject(int objectId, int size, Color color) throws NoBinFoundException {
        GObject obj = new GObject(objectId, size, color);
        Bin suitableBin;
        
        if (color == Color.BLUE || color == Color.YELLOW) {
            suitableBin = compactFit(obj);
        } else {
            suitableBin = largestFit(obj);
        }
        
        if (suitableBin == null) {
            throw new NoBinFoundException();
        }
        
        suitableBin.addObject(obj);
        this.objects.insertObj(obj);
    }
    
    private Bin compactFit(GObject obj) {
        return findSuitableBin(this.bins.getRoot(), obj);
    }
    
    private Bin findSuitableBin(Bin node, GObject obj) {
        // Base case: when the node is null, return null
        if (node == null) {
            return null;
        }
        
        // Go left first to explore smaller capacities
        Bin leftSuitable = (node.left != null) ? findSuitableBin(node.left, obj) : null;
        
        // Check if the current node is suitable
        Bin suitableBin = (node.getRemainingCapacity() >= obj.getSize()) ? node : null;
        
        // Go right to explore larger capacities
        Bin rightSuitable = (node.right != null) ? findSuitableBin(node.right, obj) : null;
        
        // Compare all three (left, right, current node) to find the best suitable bin
        suitableBin = compareBins(suitableBin, leftSuitable, obj.getColor());
        suitableBin = compareBins(suitableBin, rightSuitable, obj.getColor());
        
        return suitableBin;
    }
    
    private Bin compareBins(Bin bin1, Bin bin2, Color color) {
        if (bin1 == null) {
            return bin2;
        }
        if (bin2 == null) {
            return bin1;
        }
        
        // Compare based on remaining capacity first
        if (bin1.getRemainingCapacity() < bin2.getRemainingCapacity()) {
            return bin1;
        } else if (bin1.getRemainingCapacity() > bin2.getRemainingCapacity()) {
            return bin2;
        } else {
            // If capacities are equal, compare by bin_id based on color
            if (color == Color.BLUE) {
                return (bin1.getBinId() < bin2.getBinId()) ? bin1 : bin2;
            } else if (color == Color.YELLOW) {
                return (bin1.getBinId() > bin2.getBinId()) ? bin1 : bin2;
            } else {
                return bin1;
            }
        }
    }
    
    private Bin largestFit(GObject obj) {
        return findLargestFit(this.bins.getRoot(), obj);
    }
    
    private Bin findLargestFit(Bin node, GObject obj) {
        // Base case: if node is null, return null
        if (node == null) {
            return null;
        }
        
        // Recursively search the left and right subtrees
        Bin leftSuitable = null;
        if (node.left != null) {
            leftSuitable = findLargestFit(node.left, obj);
        }
        
        Bin rightSuitable = null;
        if (node.right != null) {
            rightSuitable = findLargestFit(node.right, obj);
        }
        
        // Check the current node
        Bin suitableBin = null;
        if (node.getRemainingCapacity() >= obj.getSize()) {
            suitableBin = node;
        }
        
        // If no suitable bin is found in current node or subtrees
        if (suitableBin == null && leftSuitable == null && rightSuitable == null) {
            return null;
        }
        
        // Determine the best bin (left, right, current)
        Bin bestBin = suitableBin;
        
        if (leftSuitable != null && (bestBin == null || leftSuitable.getRemainingCapacity() > bestBin.getRemainingCapacity())) {
            bestBin = leftSuitable;
        }
        if (rightSuitable != null && (bestBin == null || rightSuitable.getRemainingCapacity() > bestBin.getRemainingCapacity())) {
            bestBin = rightSuitable;
        }
        
        // If there are multiple bins with the same remaining capacity
        if (bestBin != null && leftSuitable != null && bestBin.getRemainingCapacity() == leftSuitable.getRemainingCapacity()) {
            if (obj.getColor() == Color.RED && bestBin.getBinId() > leftSuitable.getBinId()) {
                bestBin = leftSuitable;
            } else if (obj.getColor() == Color.GREEN && bestBin.getBinId() < leftSuitable.getBinId()) {
                bestBin = leftSuitable;
            }
        }
        
        if (bestBin != null && rightSuitable != null && bestBin.getRemainingCapacity() == rightSuitable.getRemainingCapacity()) {
            if (obj.getColor() == Color.RED && bestBin.getBinId() > rightSuitable.getBinId()) {
                bestBin = rightSuitable;
            } else if (obj.getColor() == Color.GREEN && bestBin.getBinId() < rightSuitable.getBinId()) {
                bestBin = rightSuitable;
            }
        }
        
        return bestBin;
    }
    
    public void deleteObject(int objectId) {
        GObject obj = this.objects.findObj(objectId);
        if (obj != null && obj.getBinId() != null) {
            Bin binObj = this.bins.findBin(obj.getBinId());
            if (binObj != null) {
                binObj.removeObject(obj);
            }
            this.objects.deleteObj(objectId);
        }
    }
    
    public BinInfo binInfo(int binId) {
        Bin binObj = this.bins.findBin(binId);
        if (binObj != null) {
            return new BinInfo(binObj.getRemainingCapacity(), binObj.getObjectIds());
        }
        return null;
    }
    
    public Integer objectInfo(int objectId) {
        GObject obj = this.objects.findObj(objectId);
        return (obj != null) ? obj.getBinId() : null;
    }
}