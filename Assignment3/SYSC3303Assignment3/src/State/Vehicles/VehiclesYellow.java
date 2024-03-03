package State.Vehicles;

import PELICAN.Context;
import State.Pedestrians.PedestriansEnabled;
import State.State;
import State.VehiclesSignal;
import State.PedestriansSignal;

public class VehiclesYellow extends State {
    private Context context;
    public VehiclesYellow(Context context) {
        this.context = context;
    }

    /**
     * Signal yellow, goes into the PedestrianEnabledState
     */
    @Override
    public void action() {
        System.out.println("---System is in VehiclesYellow State---");
        System.out.println("Traffic Light: YELLOW");
        signalVehicles(VehiclesSignal.YELLOW);
        System.out.println("Going red in 3s");
        SetTimer(3);
        KillTimer();

    }

    /**
     * Use to start a timer upon entry
     *
     * @param timer : use to Initialise timer upon entry
     */
    @Override
    public void SetTimer(int timer) {
        try {
            Thread.sleep(timer * 1000);;
        } catch (InterruptedException e) {
            System.err.println(e);
        }

    }

    /**
     * Use to kill the timer upon exit
     */
    @Override
    public void KillTimer() {
        this.context.setState(new PedestriansEnabled(this.context));
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
