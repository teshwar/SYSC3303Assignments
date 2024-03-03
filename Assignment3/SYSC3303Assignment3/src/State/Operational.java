package State;

import PELICAN.Context;
import State.Vehicles.VehiclesEnabled;

public class Operational extends State{
private Context context;
    public Operational (Context context){
        System.out.println("System is now in Operational State");
        this.context = context;
    }

    /**
     *  Goes into VehiclesEnabled State
     */
    @Override
    public void action() {
        System.out.println("---System is in Operational State---");
        System.out.println("System is now going into VehiclesEnabled State");
        this.context.setState(new VehiclesEnabled(this.context));
    }

    /**
     * Use to start a timer upon entry
     *
     * @param timer : use to Initialise timer upon entry
     */
    @Override
    public void SetTimer(int timer) {

    }

    /**
     * Use to kill the timer upon exit
     */
    @Override
    public void KillTimer() {

    }

    /**
     * Allows context to change pedestrian is waiting in state machine
     */
    @Override
    public void pedestrianIsWaiting() {

    }

    /**
     * @param signal : Use to set the current Vehicle state
     */
    @Override
    public void signalVehicles(VehiclesSignal signal) {

    }

    /**
     * @param signal : Use to set the current Pedestrian state
     */
    @Override
    public void signalPedestrians(PedestriansSignal signal) {

    }

}
