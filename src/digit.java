public class digit {
    public static void main(String[] args)
    {
        // create codePoints
        int cp1 = 57;
        int cp2 = 57;

        // Check whether the codePoints
        // are digit or not.
        System.out.println(
                "The codePoint cp1 is a digit -> "
                        + Character.isDigit(cp1));
        System.out.println(
                "The codePoint cp2 is a digit -> "
                        + Character.isDigit(cp2));
    }
}
