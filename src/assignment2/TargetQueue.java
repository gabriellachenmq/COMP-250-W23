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
        MyQueue<Position> queue = new MyQueue<Position>();
        for (char c : input.toCharArray()) {
            if (c == '(') {
                if (!stack.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                stack.push("(");
            } else if (Character.isDigit(c)) {
                num += c;
            } else if (c == ',') {
                if (num.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                stack.push(num);
                stack.push(",");
                num = "";
            } else if (c == ')') {
                if (stack.getSize() < 3 || !stack.pop().equals("(") || !Character.isDigit(num.charAt(0))) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                int y = Integer.parseInt(num);
                int x = Integer.parseInt(stack.pop());
                stack.pop(); // remove the ","
                queue.enqueue(new Position(x, y));
                num = "";
            } else if (c == '.') {
                if (!num.isEmpty() || stack.getSize() != 0) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
            } else {
                throw new IllegalArgumentException("Invalid syntax");
            }
        }
        if (!num.isEmpty() || stack.getSize() != 0) {
            throw new IllegalArgumentException("Invalid syntax");
        }
    }

}
