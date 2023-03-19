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
        //Position nextHead = null;
        if (actions.dequeue().equals('N')){
            currhead.moveNorth();
        }
        if (actions.dequeue().equals('S')){
            currhead.moveSouth();
        }
        if (actions.dequeue().equals('W')){
            currhead.moveWest();
        }
        if (actions.dequeue().equals('E')){
            currhead.moveEast();
        }
        if (!region.contains(currhead)) {
            state = GameState.WALL_COLLISION;
            return;
        }
        if (cater.selfCollision(currhead)) {
            state = GameState.SELF_COLLISION;
            return;
        }
        if (currhead.equals(foodPos)) {
            cater.eat(foodPos);
            if (target.isEmpty()) {
                state = GameState.DONE;
            } else {
                foodPos = target.dequeue();
                state = GameState.EAT;
            }
        } else {
            cater.move(foodPos);
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
