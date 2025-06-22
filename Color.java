public enum Color {
    BLUE(1),
    YELLOW(2),
    RED(3),
    GREEN(4);
    
    private final int value;
    
    Color(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}