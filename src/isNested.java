
import assignment2.MyStack;

import java.util.NoSuchElementException;
import java.util.Stack;

public class isNested {
    public static boolean isNested(String s) {
        Stack<Character> stack = new Stack<>();

        int iFirstL = 0;
        int isFirstR = 0;
        char rb = ']';
        char lb = '[';
        int[] br = new int[2];

        br[0] = s.indexOf('[');
        br[1] = s.indexOf(']');
        String sliced = s.substring(br[0]+1, br[1]);
        for (int i = 0; i<sliced.length(); i++){
            if(sliced.charAt(i) == rb || sliced.charAt(i) == lb){
                return true;
            }
        }
        return false;

    }

    public static String[] decoded(String input){
        String normal = "";
        String direction = "";
        String nested = "";
        int fcount = 0;
        String[] strings = new String[2];
        int bcount = 0;
        boolean a = isNested(input);
        while (!a) {
            int count = Character.getNumericValue(input.charAt(input.indexOf('[')-1));
            for (int i=input.indexOf('[')+1; i< input.indexOf(']'); i++){
                nested += input.charAt(i);
            }
            String rest = input.substring(input.indexOf(']')+1, input.length());
            input = rest;
            fcount = count;
            for (int e = 0; e < fcount; e++){
                normal += nested;
            }
            if (input.indexOf(']') == -1){
                break;
            }

            nested = "";
        }
        while(a) {
            int lb = 0;
            int rb = 0;
            int k = 0;
            MyStack<Character> stack = new MyStack<>();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '[') {
                    stack.push(c);
                } else if (c == ']') {
                    stack.pop();
                    if(stack.isEmpty()) {
                        k = i;
                        break;
                    }
                }
            }


            bcount = Character.getNumericValue(input.charAt(input.indexOf('[')-1));
            String origInput = input;
            String rest1 = origInput.substring(origInput.indexOf('[')+1,k);
            input = rest1;
            direction = input;

            for (int e = 0; e < bcount; e++){
                direction = input;
            }
            if (origInput.length() - (k+1) != 0){
                String rest2 = origInput.substring(k+1, origInput.length());
                input = rest2;
                for (int j = 0; j < bcount; j++){
                    direction += normal;
                }
            }
            if (input.indexOf(']') == -1){
                break;
            }


        }
        if(bcount == 0){
            direction = normal;
        }

        strings[0] = input;
        strings[1] = direction;
        return strings;
    }

    public static void main(String[] args){
        String s1 = "4[2[S]2[N]]";
        //String s2 = "2[3[2[N]2[E]]2[W]]";
        for (int i = 0; i<decoded(s1).length; i++){
            System.out.println(decoded(s1)[i]);
        }

        //System.out.println(isNested(s2));



    }
}
