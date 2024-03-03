package PELICAN;

import State.State;
import State.Operational;

public class Context implements Runnable{

    private State currentState ;
    private final Thread thread;

    public Context(){
        this.currentState = new Operational(this);
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     *
     * @param state: Set the current state of Context
     */
    public void setState(State state) {
        this.currentState = state;
    }

    /**
     * A pedestrian is currently waiting
     */
    public void pedestrianWaiting(){
        this.currentState.pedestrianIsWaiting();
    }

    /**
     * timeout function. I don't understand it's use as the timeout is from the machine
     * but since it was in the diagram, I implemented it
     */
    public void timeout(){

    }

    @Override
    public void run() {
        while (true) {
            currentState.action();
        }
    }
}
