public class NoBinFoundException extends Exception {
    public NoBinFoundException() {
        super("No Bin found to store the given object");
    }
}