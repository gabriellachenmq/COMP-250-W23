package assignment3;

import java.awt.Color;

public class PerimeterGoal extends Goal{

    public PerimeterGoal(Color c) {
        super(c);
    }

    @Override
    public int score(Block board) {
        int score = 0;
        Color[][] blockArr = board.flatten();
        int rowCount = blockArr.length;
        int colCount = blockArr[0].length;
        for(int col = 0; col < colCount; col++){
            Color top = blockArr[0][col];
            Color bottom = blockArr[rowCount - 1][col];
            if(top.equals(this.targetGoal)){
                score++;
            }
            if(bottom.equals(this.targetGoal)){
                score++;
            }
        }
        for (int row = 0; row < rowCount; row++) {
            Color left = blockArr[row][0];
            Color right = blockArr[row][colCount - 1];
            if(left.equals(this.targetGoal)){
                score++;
            }
            if(right.equals(this.targetGoal)){
                score++;
            }
        }

        return score;
    }

    @Override
    public String description() {
        return "Place the highest number of " + GameColors.colorToString(targetGoal)
                + " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
    }

}
