package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal {

    public BlobGoal(Color c) {
        super(c);
    }

    @Override
    public int score(Block board) {
        Color[][] blockArr = board.flatten();
        boolean[][] vidited = new boolean[blockArr.length][blockArr[0].length];
        int score = undiscoveredBlobSize(0,0,blockArr,vidited);
        for(int i=0; i<blockArr.length; i++){
            for(int j=0; j<blockArr.length; j++){
                int temp = undiscoveredBlobSize(i,j,blockArr,vidited);
                if (temp>score){
                    score = temp;
                }
            }
        }
        return score;
    }

    @Override
    public String description() {
        return "Create the largest connected blob of " + GameColors.colorToString(targetGoal)
                + " blocks, anywhere within the block";
    }


    public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
        if (i < 0 || i >= unitCells.length || j < 0 || j >= unitCells[0].length) {
            // cell is out of bounds
            return 0;
        }
        if (visited[i][j]) {
            // cell has already been visited
            return 0;
        }
        if(unitCells[i][j] != this.targetGoal){
            return 0;
        }
        visited[i][j] = true;
        int blobSize = 1;

        blobSize += undiscoveredBlobSize(i+1, j, unitCells, visited);
        blobSize += undiscoveredBlobSize(i-1, j, unitCells, visited);
        blobSize += undiscoveredBlobSize(i, j+1, unitCells, visited);
        blobSize += undiscoveredBlobSize(i, j-1, unitCells, visited);

        return blobSize;
    }

}
