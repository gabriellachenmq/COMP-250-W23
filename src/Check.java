import java.util.Stack;
public class Check {
    public static String findMatchingBracketIndex(String s) {
        String d = "";
        Stack<Character> stack = new Stack<Character>();
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(c);
            } else if (c == ']') {
                stack.pop();
                if (stack.isEmpty()) {
                    k = i;
                    break;
                }
            }
        }
        d = Integer.toString(k);
        return d;
    }
    public static void main(String[] args){
        String s = "4[2[S]2[N]]";
        System.out.println(findMatchingBracketIndex(s)); // Output: 7

    }

}
