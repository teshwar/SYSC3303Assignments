package State.Vehicles;

import PELICAN.Context;
import State.State;
import State.VehiclesSignal;
import State.PedestriansSignal;


public class VehiclesEnabled extends State {
    private Context context;
    public VehiclesEnabled(Context context) {
        this.context = context;
    }

    /**
     * Goes into VehiclesGreen State & signalPedestrians
     */
    @Override
    public void action() {
        System.out.println("---System is in VehiclesEnabled State---");
        signalPedestrians(PedestriansSignal.DONT_WALK);
        System.out.println("System is now going into VehiclesGreen State");
        this.context.setState(new VehiclesGreen(this.context));
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
