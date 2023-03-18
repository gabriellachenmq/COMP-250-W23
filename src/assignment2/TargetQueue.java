package assignment2;


public class TargetQueue extends MyQueue<Position>{
    private MyStack<String> stack;

    public TargetQueue(){
        super();
        stack = new MyStack<String>();
    }

    public void clear(){
        super.clear();
        stack.clear();
    }

    public void addTargets(String input) {
        clear();
        String num = "";
        boolean periodFound = false;
        boolean hasConsecutiveP = false;
        int count = 0;
        if (input.length() != 0) {
            char prevChar = input.charAt(0);
            char targetChar = '(';
            for (int i = 1; i < input.length(); i++) {
                char currChar = input.charAt(i);
                if (currChar == targetChar) {
                    count++;
                }
                if (currChar == prevChar) {
                    hasConsecutiveP = true;
                    break;
                }
                prevChar = currChar;
            }

            if (hasConsecutiveP && prevChar == '.') {
                throw new IllegalArgumentException("Invalid syntax");
            }
        }
        //TargetQueue queue = (TargetQueue) new TargetQueue();
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) && c != '(' && c != ')' && c != ',' && c != '.') {
                throw new IllegalArgumentException("Invalid character: " + c);
            }

            if (c == '(') {
                if (!stack.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                stack.push("(");
            } else if (Character.isDigit(c)) {
                num += c;
            } else if (c == ',') {
                if (num.isEmpty() || stack.isEmpty() ) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                stack.push(num);
                stack.push(",");
                num = "";
            } else if (c == ')') {
                if (stack.isEmpty() || num.isEmpty() || stack.getSize() != 3) {
                    throw new IllegalArgumentException("Invalid input syntax");
                }
                String yCoord = num;
                String comma = stack.pop();
                String xCoord = stack.pop();
                String leftParen = stack.pop();
                if (!leftParen.equals("(") || !comma.equals(",") || xCoord.isEmpty()) {
                    throw new IllegalArgumentException("Invalid input syntax");
                }
                if (!isValidInteger(xCoord) || !isValidInteger(yCoord)) {
                    throw new IllegalArgumentException("Invalid input format");
                }
                try {
                    int x = Integer.parseInt(xCoord);
                    int y = Integer.parseInt(yCoord);
                    Position p = new Position(x, y);
                    enqueue(p);
                    num = "";
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid input syntax");
                }
            } else if (c == '.') {
                if (!num.isEmpty() || stack.getSize() != 0) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                periodFound = true;
            } else {
                throw new IllegalArgumentException("Invalid syntax");
            }
        }
        if (!periodFound){
            if (!stack.isEmpty() || !num.isEmpty() || count >= 1) {
                throw new IllegalArgumentException("Invalid input syntax");
            }
        }

    }
    private boolean isValidInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}

