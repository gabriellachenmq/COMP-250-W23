package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal {

    public BlobGoal(Color c) {
        super(c);
    }

    @Override
    public int score(Block board) {
        /*
         * ADD YOUR CODE HERE
         */
        return 0;
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
