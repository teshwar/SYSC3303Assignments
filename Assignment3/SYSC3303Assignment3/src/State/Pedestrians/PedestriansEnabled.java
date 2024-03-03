package State.Pedestrians;

import PELICAN.Context;
import State.State;
import State.VehiclesSignal;
import State.PedestriansSignal;

public class PedestriansEnabled extends State {
    private Context context;
    public PedestriansEnabled(Context context) {
        this.context = context;
    }


    /**
     *  Goes into pedestriansWalk & prevent signalVehicles
     */
    @Override
    public void action() {
        System.out.println("---System is in PedestriansEnabled State---");
        System.out.println("Traffic Light: RED");
        signalVehicles(VehiclesSignal.RED);
        System.out.println("System is now going into PedestriansWalk State");
        this.context.setState(new PedestriansWalk(this.context));


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
