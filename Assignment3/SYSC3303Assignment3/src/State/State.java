package State;

/**
 * Represents the state class which all the other state will implements from
 *
 * @author Teshwar Tarachand
 * @version 1.0
 */


public abstract class State {

    /**
     *  Parameter present in all state
     */
    static public int pedestrianFlashCtr;
    static public boolean isPedestrianWaiting;

    /**
     * States perform it's required action
     */
    public abstract void action ();
    /**
     * Use to start a timer upon entry
     * @param timer: use to Initialise timer upon entry
     */
    public abstract void SetTimer(int timer);

    /**
     *
     * Use to kill the timer upon exit
     */
    public abstract void KillTimer();

    /**
     *
     * @param signal: Use to set the current Pedestrian state
     */
    public abstract void signalPedestrians(PedestriansSignal signal);

    /**
     *
     * @param signal: Use to set the current Vehicle state
     */
    public abstract void signalVehicles(VehiclesSignal signal);

    /**
     * Allows context to change pedestrian is waiting in state machine
     */
    public abstract void pedestrianIsWaiting();
}
