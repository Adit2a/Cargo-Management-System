import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void printSeparator() {
        System.out.println("\n" + "-".repeat(80) + "\n");
    }
    
    public static void main(String[] args) {
        // Initialize GCMS
        GCMS gcms = new GCMS();
        
        // Adding an initial set of bins with varying capacities
        int[][] initialBinData = {
            {1001, 50}, {1002, 30}, {1003, 40}, {1004, 25}, {1005, 35},
            {1006, 60}, {1007, 45}, {1008, 55}, {1009, 20}, {1010, 70}
        };
        
        System.out.println("Adding Initial Bins:");
        for (int[] binData : initialBinData) {
            int binId = binData[0];
            int capacity = binData[1];
            gcms.addBin(binId, capacity);
            System.out.println("Added Bin ID: " + binId + ", Capacity: " + capacity);
        }
        
        printSeparator();
        
        // Adding an initial set of objects with varying sizes and colors
        Object[][] initialObjectData = {
            {2001, 20, Color.RED}, {2002, 15, Color.YELLOW}, {2003, 10, Color.BLUE},
            {2004, 25, Color.GREEN}, {2005, 30, Color.RED}, {2006, 5, Color.YELLOW},
            {2007, 8, Color.BLUE}, {2008, 22, Color.GREEN}, {2009, 35, Color.BLUE},
            {2010, 40, Color.RED}, {2011, 12, Color.YELLOW}, {2012, 18, Color.GREEN},
            {2013, 7, Color.BLUE}, {2014, 28, Color.RED}, {2015, 16, Color.YELLOW}
        };
        
        System.out.println("Adding Initial Objects:");
        for (Object[] objData : initialObjectData) {
            int objId = (Integer) objData[0];
            int size = (Integer) objData[1];
            Color color = (Color) objData[2];
            try {
                gcms.addObject(objId, size, color);
                System.out.println("Added Object ID: " + objId + ", Size: " + size + ", Color: " + color.name());
            } catch (NoBinFoundException e) {
                System.out.println("Failed to add Object ID: " + objId + ", Size: " + size + 
                                 ", Color: " + color.name() + " - No suitable bin found");
            }
        }
        
        printSeparator();
        
        // Displaying bin information after initial additions
        System.out.println("Bin Information After Adding Initial Objects:");
        for (int[] binData : initialBinData) {
            int binId = binData[0];
            try {
                BinInfo info = gcms.binInfo(binId);
                if (info != null) {
                    System.out.println("Bin ID: " + binId + ", Remaining Capacity: " + 
                                     info.getRemainingCapacity() + ", Objects: " + info.getObjectIds());
                }
            } catch (Exception e) {
                System.out.println("Error retrieving info for Bin ID: " + binId + " - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        // Displaying object information after initial additions
        System.out.println("Object Information After Adding Initial Objects:");
        for (Object[] objData : initialObjectData) {
            int objId = (Integer) objData[0];
            try {
                Integer assignedBin = gcms.objectInfo(objId);
                System.out.println("Object ID: " + objId + " is assigned to Bin ID: " + assignedBin);
            } catch (Exception e) {
                System.out.println("Error retrieving info for Object ID: " + objId + " - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        // Adding additional bins after some objects have been placed
        int[][] additionalBinData = {{1011, 65}, {1012, 45}, {1013, 55}};
        
        System.out.println("Adding Additional Bins:");
        for (int[] binData : additionalBinData) {
            int binId = binData[0];
            int capacity = binData[1];
            gcms.addBin(binId, capacity);
            System.out.println("Added Bin ID: " + binId + ", Capacity: " + capacity);
        }
        
        printSeparator();
        
        // Adding additional objects after new bins have been added
        Object[][] additionalObjectData = {
            {2016, 25, Color.GREEN}, {2017, 14, Color.YELLOW}, {2018, 9, Color.BLUE},
            {2019, 50, Color.RED}, {2020, 33, Color.YELLOW}, {2021, 12, Color.GREEN},
            {2022, 7, Color.BLUE}, {2023, 19, Color.RED}, {2024, 28, Color.YELLOW},
            {2025, 11, Color.BLUE}
        };
        
        System.out.println("Adding Additional Objects:");
        for (Object[] objData : additionalObjectData) {
            int objId = (Integer) objData[0];
            int size = (Integer) objData[1];
            Color color = (Color) objData[2];
            try {
                gcms.addObject(objId, size, color);
                System.out.println("Added Object ID: " + objId + ", Size: " + size + ", Color: " + color.name());
            } catch (NoBinFoundException e) {
                System.out.println("Failed to add Object ID: " + objId + ", Size: " + size + 
                                 ", Color: " + color.name() + " - No suitable bin found");
            }
        }
        
        printSeparator();
        
        // Displaying bin information after adding additional objects
        System.out.println("Bin Information After Adding Additional Objects:");
        List<int[]> allBins = new ArrayList<>();
        allBins.addAll(Arrays.asList(initialBinData));
        allBins.addAll(Arrays.asList(additionalBinData));
        
        for (int[] binData : allBins) {
            int binId = binData[0];
            try {
                BinInfo info = gcms.binInfo(binId);
                if (info != null) {
                    System.out.println("Bin ID: " + binId + ", Remaining Capacity: " + 
                                     info.getRemainingCapacity() + ", Objects: " + info.getObjectIds());
                }
            } catch (Exception e) {
                System.out.println("Error retrieving info for Bin ID: " + binId + " - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        // Displaying object information after adding additional objects
        System.out.println("Object Information After Adding Additional Objects:");
        List<Object[]> allObjects = new ArrayList<>();
        allObjects.addAll(Arrays.asList(initialObjectData));
        allObjects.addAll(Arrays.asList(additionalObjectData));
        
        for (Object[] objData : allObjects) {
            int objId = (Integer) objData[0];
            try {
                Integer assignedBin = gcms.objectInfo(objId);
                System.out.println("Object ID: " + objId + " is assigned to Bin ID: " + assignedBin);
            } catch (Exception e) {
                System.out.println("Object ID: " + objId + " has been deleted or does not exist - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        // Deleting some objects
        int[] objectsToDelete = {2003, 2005, 2010, 2015, 2018, 2019};
        System.out.println("Deleting Objects:");
        for (int objId : objectsToDelete) {
            try {
                gcms.deleteObject(objId);
                System.out.println("Deleted Object ID: " + objId);
            } catch (Exception e) {
                System.out.println("Failed to delete Object ID: " + objId + " - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        // Displaying bin information after deletions
        System.out.println("Bin Information After Deleting Objects:");
        for (int[] binData : allBins) {
            int binId = binData[0];
            try {
                BinInfo info = gcms.binInfo(binId);
                if (info != null) {
                    System.out.println("Bin ID: " + binId + ", Remaining Capacity: " + 
                                     info.getRemainingCapacity() + ", Objects: " + info.getObjectIds());
                }
            } catch (Exception e) {
                System.out.println("Error retrieving info for Bin ID: " + binId + " - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        // Displaying object information after deletions
        System.out.println("Object Information After Deleting Objects:");
        List<Integer> currentItems = new ArrayList<>();
        for (Object[] objData : allObjects) {
            currentItems.add((Integer) objData[0]);
        }
        
        // Remove deleted objects
        for (int deletedId : objectsToDelete) {
            currentItems.remove(Integer.valueOf(deletedId));
        }
        
        for (int objId : currentItems) {
            try {
                Integer assignedBin = gcms.objectInfo(objId);
                System.out.println("Object ID: " + objId + " is assigned to Bin ID: " + assignedBin);
            } catch (Exception e) {
                System.out.println("Object ID: " + objId + " has been deleted or does not exist - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        // Attempting to add an object that cannot fit into any bin
        System.out.println("Adding an Object That Cannot Fit into Any Bin:");
        try {
            gcms.addObject(2026, 100, Color.BLUE);
            System.out.println("Added Object ID: 2026, Size: 100, Color: BLUE");
        } catch (NoBinFoundException e) {
            System.out.println("Failed to add Object ID: 2026, Size: 100, Color: BLUE - No suitable bin found");
        }
        
        printSeparator();
        
        // Final bin information
        System.out.println("Final Bin Information:");
        for (int[] binData : allBins) {
            int binId = binData[0];
            try {
                BinInfo info = gcms.binInfo(binId);
                if (info != null) {
                    System.out.println("Bin ID: " + binId + ", Remaining Capacity: " + 
                                     info.getRemainingCapacity() + ", Objects: " + info.getObjectIds());
                }
            } catch (Exception e) {
                System.out.println("Error retrieving info for Bin ID: " + binId + " - " + e.getMessage());
            }
        }
        
        printSeparator();
        
        System.out.println("All enhanced tests completed.");
    }
}