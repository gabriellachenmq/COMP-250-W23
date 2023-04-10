package assignment3;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Block {
    private int xCoord;
    private int yCoord;
    private int size; // height/width of the square
    private int level; // the root (outer most block) is at level 0
    private int maxDepth;
    private Color color;

    private Block[] children; // {UR, UL, LL, LR}

    public static Random gen = new Random();


    /*
     * These two constructors are here for testing purposes.
     */
    public Block() {}

    public Block(int x, int y, int size, int lvl, int  maxD, Color c, Block[] subBlocks) {
        this.xCoord=x;
        this.yCoord=y;
        this.size=size;
        this.level=lvl;
        this.maxDepth = maxD;
        this.color=c;
        this.children = subBlocks;
    }



    /*
     * Creates a random block given its level and a max depth.
     *
     * xCoord, yCoord, size, and highlighted should not be initialized
     * (i.e. they will all be initialized by default)
     */
    public Block(int lvl, int maxDepth) {
        if(lvl > maxDepth){
            throw new IllegalArgumentException("level cannot be larger than the maxDepth.");
        }
        this.level = lvl;
        this.maxDepth = maxDepth;
        this.color = null;
        Block[] iniBlock = new Block[0];
        this.children = iniBlock;
        if(this.level<this.maxDepth){
            Double randomNumber = gen.nextDouble(1);
            if (randomNumber < Math.exp(-0.25 * this.level)){
                this.children = subdivideBlock(lvl+1, maxDepth);;
            }
            else{
                this.color = GameColors.BLOCK_COLORS[gen.nextInt(4)];
            }
        }
        else{
            this.color = GameColors.BLOCK_COLORS[gen.nextInt(4)];
        }
    }

    private Block[] subdivideBlock(int lvl, int maxDepth){
        Block[] children = new Block[4];
        for (int i=0; i<4; i++){
            Block child = new Block(lvl,maxDepth);
            children[i] = child;
        }
        return children;
    }



    /*
     * Updates size and position for the block and all of its sub-blocks, while
     * ensuring consistency between the attributes and the relationship of the
     * blocks.
     *
     *  The size is the height and width of the block. (xCoord, yCoord) are the
     *  coordinates of the top left corner of the block.
     */
    public void updateSizeAndPosition (int size, int xCoord, int yCoord) {
        if(size <= 0 || (size % 2 != 0 && size != 1)){
            throw new IllegalArgumentException("Invalid size.");
        }
        this.size = size;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        int childSize = size/2;
        if(this.children.length != 0) {
            this.children[0].updateSizeAndPosition(childSize, xCoord + childSize, yCoord);
            this.children[1].updateSizeAndPosition(childSize, xCoord, yCoord);
            this.children[2].updateSizeAndPosition(childSize, xCoord, yCoord + childSize);
            this.children[3].updateSizeAndPosition(childSize, xCoord + childSize, yCoord + childSize);
        }
    }


    /*
     * Returns a List of blocks to be drawn to get a graphical representation of this block.
     *
     * This includes, for each undivided Block:
     * - one BlockToDraw in the color of the block
     * - another one in the FRAME_COLOR and stroke thickness 3
     *
     * Note that a stroke thickness equal to 0 indicates that the block should be filled with its color.
     *
     * The order in which the blocks to draw appear in the list does NOT matter.
     */
    public ArrayList<BlockToDraw> getBlocksToDraw() {

        ArrayList<BlockToDraw> blockList = new ArrayList<BlockToDraw>();
        BlockToDraw block1 = new BlockToDraw(this.color,this.xCoord,this.yCoord,this.size,0);
        BlockToDraw block2 = new BlockToDraw(GameColors.FRAME_COLOR, this.xCoord,this.yCoord,this.size,3);
        blockList.add(block1);
        blockList.add(block2);
        if (this.children.length != 0){
            blockList.remove(blockList.size()-1);
            blockList.remove(blockList.size()-1);
            blockList.addAll(this.children[0].getBlocksToDraw());
            blockList.addAll(this.children[1].getBlocksToDraw());
            blockList.addAll(this.children[2].getBlocksToDraw());
            blockList.addAll(this.children[3].getBlocksToDraw());
        }
        return blockList;
    }

    /*
     * This method is provided and you should NOT modify it.
     */
    public BlockToDraw getHighlightedFrame() {
        return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
    }



    /*
     * Return the Block within this Block that includes the given location
     * and is at the given level. If the level specified is lower than
     * the lowest block at the specified location, then return the block
     * at the location with the closest level value.
     *
     * The location is specified by its (x, y) coordinates. The lvl indicates
     * the level of the desired Block. Note that if a Block includes the location
     * (x, y), and that Block is subdivided, then one of its sub-Blocks will
     * contain the location (x, y) too. This is why we need lvl to identify
     * which Block should be returned.
     *
     * Input validation:
     * - this.level <= lvl <= maxDepth (if not throw exception)
     * - if (x,y) is not within this Block, return null.
     */
    public Block getSelectedBlock(int x, int y, int lvl) {
        Block selectedBlock = new Block();
        if(lvl > this.maxDepth || lvl < this.level){
            throw new IllegalArgumentException("Invalid input.");
        }
        else if(!this.inRange(x,y)){
            return null;
        }
        if(this.level<lvl && this.children.length != 0 && this.inRange(x,y)) {
            for (Block child : this.children) {
                if (child.inRange(x, y)) {
                    if (child.level == lvl) {
                        selectedBlock = child;
                    }
                    else if (child.level < lvl){
                        selectedBlock = child.getSelectedBlock(x,y,lvl);
                    }
                }
            }
        }
        else{
            return this;
        }
        return selectedBlock;
    }
    private boolean inRange(int x, int y){
        return ((x < this.xCoord+this.size &&  x >= this.xCoord) && (y < this.yCoord+this.size &&  y >= this.yCoord));
    }




    /*
     * Swaps the child Blocks of this Block.
     * If input is 1, swap vertically. If 0, swap horizontally.
     * If this Block has no children, do nothing. The swap
     * should be propagate, effectively implementing a reflection
     * over the x-axis or over the y-axis.
     *
     */
    public void reflect(int direction) {
        if(direction != 0 && direction != 1){
            throw new IllegalArgumentException("invalid direction.");
        }
        if(direction == 0){
            if(this.children.length != 0){
                swapChildren(this.children,0);
                this.updateSizeAndPosition(this.size,this.xCoord,this.yCoord);
                for(int i=0; i<4; i++){
                    this.children[i].reflect(0);
                }
            }
        }
        if(direction == 1){
            if(this.children.length != 0) {
                swapChildren(this.children,1);
                this.updateSizeAndPosition(this.size,this.xCoord,this.yCoord);
                for(int i=0; i<4; i++){
                    this.children[i].reflect(1);
                }
            }
        }
    }

    private Block[] swapChildren(Block[] list, int direction){
        if(direction == 0) {
            int size = list.length;
            for (int i = 0; i < size / 2; i++) {
                Block temp = list[i];
                list[i] = list[size - i - 1];
                list[size - i - 1] = temp;
            }
        }
        if(direction == 1){
            // Swap elements at index 0 and 1
            Block temp = list[0];
            list[0] = list[1];
            list[1] = temp;

            // Swap elements at index 2 and 3
            temp = list[2];
            list[2] = list[3];
            list[3] = temp;
        }
        return list;

    }



    /*
     * Rotate this Block and all its descendants.
     * If the input is 1, rotate clockwise. If 0, rotate
     * counterclockwise. If this Block has no children, do nothing.
     */
    public void rotate(int direction) {
        /*
         * ADD YOUR CODE HERE
         */
    }



    /*
     * Smash this Block.
     *
     * If this Block can be smashed,
     * randomly generate four new children Blocks for it.
     * (If it already had children Blocks, discard them.)
     * Ensure that the invariants of the Blocks remain satisfied.
     *
     * A Block can be smashed iff it is not the top-level Block
     * and it is not already at the level of the maximum depth.
     *
     * Return True if this Block was smashed and False otherwise.
     *
     */
    public boolean smash() {
        /*
         * ADD YOUR CODE HERE
         */
        return false;
    }


    /*
     * Return a two-dimensional array representing this Block as rows and columns of unit cells.
     *
     * Return and array arr where, arr[i] represents the unit cells in row i,
     * arr[i][j] is the color of unit cell in row i and column j.
     *
     * arr[0][0] is the color of the unit cell in the upper left corner of this Block.
     */
    public Color[][] flatten() {
        /*
         * ADD YOUR CODE HERE
         */
        return null;
    }



    // These two get methods have been provided. Do NOT modify them.
    public int getMaxDepth() {
        return this.maxDepth;
    }

    public int getLevel() {
        return this.level;
    }


    /*
     * The next 5 methods are needed to get a text representation of a block.
     * You can use them for debugging. You can modify these methods if you wish.
     */
    public String toString() {
        return String.format("pos=(%d,%d), size=%d, level=%d"
                , this.xCoord, this.yCoord, this.size, this.level);
    }

    public void printBlock() {

        this.printBlockIndented(0);
    }

    private void printBlockIndented(int indentation) {
        String indent = "";
        for (int i=0; i<indentation; i++) {
            indent += "\t";
        }

        if (this.children.length == 0) {
            // it's a leaf. Print the color!
            String colorInfo = GameColors.colorToString(this.color) + ", ";
            System.out.println(indent + colorInfo + this);
        } else {
            System.out.println(indent + this);
            for (Block b : this.children)
                b.printBlockIndented(indentation + 1);
        }
    }

    private static void coloredPrint(String message, Color color) {
        System.out.print(GameColors.colorToANSIColor(color));
        System.out.print(message);
        System.out.print(GameColors.colorToANSIColor(Color.WHITE));
    }

    public void printColoredBlock(){
        Color[][] colorArray = this.flatten();
        for (Color[] colors : colorArray) {
            for (Color value : colors) {
                String colorName = GameColors.colorToString(value).toUpperCase();
                if(colorName.length() == 0){
                    colorName = "\u2588";
                }else{
                    colorName = colorName.substring(0, 1);
                }
                coloredPrint(colorName, value);
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Block.gen = new Random(4);
        Block b = new Block(0, 3);
        b.updateSizeAndPosition(16, 0, 0);
        b.printBlock();


    }

}
