package assignment2;


import java.util.regex.*;

public class ActionQueue extends MyQueue<Direction> {

    public ActionQueue() {
        super();
    }

    public void clear() {
        super.clear();
    }

    public void loadFromEncodedString(String input) {
        Pattern pattern = Pattern.compile("(\\d+)\\[(\\p{Upper}*)\\]");
        Matcher matcher = pattern.matcher(input);
        int pos = 0;
        while (pos < input.length()) {
            if (matcher.find(pos) && matcher.start() == pos) {
                int count = Integer.parseInt(matcher.group(1));
                String directions = matcher.group(2);
                for (int i = 0; i < count; i++) {
                    loadFromDirectionsString(directions);
                }
                pos = matcher.end();
            } else {
                throw new IllegalArgumentException("Syntax Error: Invalid format");
            }
        }
    }

    private void loadFromDirectionsString(String input) {
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'N':
                    this.enqueue(Direction.NORTH);
                    break;
                case 'S':
                    this.enqueue(Direction.SOUTH);
                    break;
                case 'W':
                    this.enqueue(Direction.WEST);
                    break;
                case 'E':
                    this.enqueue(Direction.EAST);
                    break;
                default:
                    throw new IllegalArgumentException("Syntax Error: Unknown character");
            }
        }
    }
}
