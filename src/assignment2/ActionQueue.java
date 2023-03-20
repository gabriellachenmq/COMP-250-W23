package assignment2;


public class ActionQueue extends MyQueue<Direction> {

    public ActionQueue() {
        super();
    }

    public void clear() {
        super.clear();
    }

    public void loadFromEncodedString(String input) {
        clear();
        String directionStr = "";
        String normalStr = "";
        String nestedStr = "";
        int count = 0;
        int sc = 0;
        if (input.length() != 0) {
            char targetChar1 = '[';
            char targetChar2 = ']';
            int count1 = 0;
            int count2 = 0;
            for (int i = 0; i < input.length(); i++) {
                char currChar = input.charAt(i);
                if (currChar == targetChar1){
                    count1++;
                }
                if (currChar == targetChar2){
                    count2++;
                }
                if (currChar == '-' || currChar == ' '){
                    throw new IllegalArgumentException("Invalid character");
                }
                else if (currChar == targetChar1 && input.charAt(i+1) == targetChar1){
                    throw new IllegalArgumentException("Invalid syntax");
                }
            }
            if (count1 == 0 && count2 == 0){
                for (char c : input.toCharArray()) {
                    if (c != 'N' && c != 'W' && c != 'E' && c != 'S') {
                        throw new IllegalArgumentException("Invalid character: " + c);
                    }
                    directionStr += c;
                }
                loadFromDirectionsString(directionStr);
                directionStr = "";
            }
            if ((count1+count2) % 2 != 0 || count1 != count2){
                throw new IllegalArgumentException("Invalid syntax");
            }
            if (count1 == 1 && count2 ==1){
                int iLeft = input.indexOf('[');
                int iRight = input.indexOf(']');
                int a = 0;
                int digit = Character.getNumericValue(input.charAt(iLeft-1));
                if(!Character.isDigit(input.charAt(iLeft-1))){ //check if there is a int right in front of the []
                    throw new IllegalArgumentException("Invalid syntax");
                }
                if ((iRight - iLeft) == 1){
                    throw new IllegalArgumentException("Invalid syntax");
                }

                if (input.indexOf(digit) > 0) {
                    for (int e = 0; e < input.indexOf(input.charAt(iLeft-1)); e++) {
                        if (input.charAt(e) != 'N' && input.charAt(e) != 'E' && input.charAt(e) != 'S' && input.charAt(e) != 'W') {
                            throw new IllegalArgumentException("Invalid syntax");
                        }
                        directionStr += input.charAt(e);
                    }
                }
                for (int i = iLeft+1; i<iRight; i++){
                    if (input.charAt(i) != 'N' && input.charAt(i) != 'E' && input.charAt(i) != 'S' && input.charAt(i) != 'W') {
                        throw new IllegalArgumentException("Invalid syntax");

                    }
                    normalStr += input.charAt(i);
                }
                for (a = 0; a<digit; a++){
                    directionStr += normalStr;
                }
                loadFromDirectionsString(directionStr);
                directionStr = "";
                normalStr = "";
            }

            if (count1 % 2 == 0 && count2 % 2 == 0){

            }

        }


    }

    private void loadFromDirectionsString(String input) {
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'N':
                    enqueue(Direction.NORTH);
                    break;
                case 'S':
                    enqueue(Direction.SOUTH);
                    break;
                case 'W':
                    enqueue(Direction.WEST);
                    break;
                case 'E':
                    enqueue(Direction.EAST);
                    break;
                default:
                    throw new IllegalArgumentException("Syntax Error: Unknown character");
            }
        }
    }
}
