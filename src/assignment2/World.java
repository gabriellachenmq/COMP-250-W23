package assignment2;

import java.lang.annotation.Target;

public class World {
    private Caterpillar cater;
    private Position foodPos;
    private Region region;
    private ActionQueue actions;
    private TargetQueue target;
    private GameState state;

    public World(TargetQueue tq, ActionQueue aq) {
        this.target = tq;
        this.actions = aq;
        this.region = new Region(0, 0, 15, 15);
        this.cater = new Caterpillar();
        this.foodPos = target.dequeue();
        this.state = GameState.MOVE;
    }

    public void step() {
        if (actions.isEmpty()){
            state = GameState.NO_MORE_ACTION;
            return;
        }
        Direction heading = actions.dequeue();
        if (state != GameState.MOVE && state != GameState.EAT) {
            return;
        }

        Position currhead = cater.getHead();
        Position nextHead = new Position(currhead);
        //Position nextHead = null;
        if (heading.equals(Direction.NORTH)){
            nextHead.moveNorth();
        }
        if (heading.equals(Direction.SOUTH)){
            nextHead.moveSouth();
        }
        if (heading.equals(Direction.WEST)){
            nextHead.moveWest();
        }
        if (heading.equals(Direction.EAST)){
            nextHead.moveEast();
        }
        if (!region.contains(nextHead)) {
            state = GameState.WALL_COLLISION;
            return;
        }
        if (cater.selfCollision(nextHead)) {
            state = GameState.SELF_COLLISION;
        }
        if (nextHead.equals(foodPos)) {
            cater.eat(foodPos);
            if (target.isEmpty()) {
                state = GameState.DONE;
            } else {
                foodPos = target.dequeue();
                state = GameState.EAT;
            }
        } else {
            cater.move(nextHead);
            state = GameState.MOVE;
        }

    }
    public GameState getState() {
        return state;
    }

    public Caterpillar getCaterpillar() {
        return cater;
    }

    public Position getFood() {
        return foodPos;
    }

    public boolean isRunning() {
        return state == GameState.MOVE || state == GameState.EAT;
    }
}
