
import assignment2.MyStack;

import java.util.NoSuchElementException;
import java.util.Stack;

public class isNested {
    public static boolean isNested(String s) {

        char rb = ']';
        char lb = '[';
        int[] br = new int[2];
        String origInput = s;

        br[0] = s.indexOf('[');
        br[1] = s.indexOf(']');
        String sliced = s.substring(br[0]+1, br[1]);
        s = origInput;
        for (int i = 0; i<sliced.length(); i++){
            if(sliced.charAt(i) == rb || sliced.charAt(i) == lb){
                return true;
            }
        }
        return false;

    }

    public static String decoded(String input){
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

        if(a){
            Stack<Character> stack = new Stack<Character>();
            int k = 0;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
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
            String os = input;
            bcount = Character.getNumericValue(input.charAt(input.indexOf('[')-1));
            String rest1 = input.substring(input.indexOf('[')+1,k);
            String rest2 = null;
            if(os.length() - (k+1) > 0){
                rest2 = os.substring(k+1, os.length());
                if(!isNested(rest1)){
                    if(isNested(rest2)){
                        decoded(rest2);
                    }
                    else {
                        normal = decoded(rest1) + decoded(rest2);
                    }
                }
                if(isNested(rest1)){
                    decoded(rest1);
                    if(!isNested(rest1)){
                        normal = decoded(rest1);
                    }
                    else{
                        decoded(rest1);
                    }
                }
            }
            else {
                if (!isNested(rest1)) {
                    normal = decoded(rest1);
                    for (int e = 0; e<bcount; e++){
                        direction += normal;
                    }
                }
                if (isNested(rest1)) {
                    decoded(rest1);
                    if (!isNested(rest1)) {
                        normal = decoded(rest1);
                        for (int e = 0; e<bcount; e++){
                            direction += normal;
                        }
                    } else {
                        decoded(rest1);
                    }
                }
            }

        }

        if(bcount == 0){
            direction = normal;
        }


        return direction;
    }

    public static void main(String[] args){
        String s1 = "3[N]2[S]1[W]";
        String s2 = "2[3[2[N]2[E]]2[W]]";

        System.out.println(decoded(s1));


        //System.out.println(isNested(s2));



    }
}
