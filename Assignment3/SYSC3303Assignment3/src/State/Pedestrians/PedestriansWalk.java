package State.Pedestrians;

import PELICAN.Context;
import State.State;
import State.VehiclesSignal;
import State.PedestriansSignal;

public class PedestriansWalk extends State {
    private Context context;

    public PedestriansWalk(Context context){
        this.context = context;
    }

    /**
     * Allows pedestrian to walk then go to next state
     */
    @Override
    public void action() {
        System.out.println("---System is in PedestriansWalk State---");
        System.out.println("WALK LIGHT enabled for 10s");
        signalPedestrians(PedestriansSignal.WALK);
        SetTimer(10);
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
        System.out.println("System is now going into PedestriansFlash State");
        context.setState(new PedestriansFlash(this.context));
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
