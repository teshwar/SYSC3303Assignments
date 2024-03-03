package State.Pedestrians;

import PELICAN.Context;
import State.State;
import State.Vehicles.VehiclesEnabled;
import State.VehiclesSignal;
import State.PedestriansSignal;

import java.util.Set;

public class PedestriansFlash extends State {
    private Context context;

    public PedestriansFlash(Context context){
        this.context = context;
        pedestrianFlashCtr = 7;
    }
    /**
     * Flash pedestrians walk & don't walk
     */
    @Override
    public void action() {
        System.out.println("---System is in PedestriansFlash State---");
        SetTimer(1);
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
        pedestrianFlashCtr -= 1;

        if(pedestrianFlashCtr == 0){
            System.out.println("System is now going into VehiclesEnabled State");
            this.context.setState(new VehiclesEnabled(this.context));
        }else if ((pedestrianFlashCtr & 1) == 0 ){
            signalPedestrians(PedestriansSignal.DONT_WALK);
            System.out.println("DONT WALK LIGHT enabled for 1s");
        } else{
            signalPedestrians(PedestriansSignal.BLANK);
            System.out.println("BLANK LIGHT enabled for 1s");
        }

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
