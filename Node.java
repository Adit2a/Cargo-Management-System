public class Node {
    public int value;
    public Node left;
    public Node right;
    public int height;
    
    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}