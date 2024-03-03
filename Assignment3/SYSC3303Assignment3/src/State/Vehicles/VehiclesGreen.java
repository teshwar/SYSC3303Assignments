package State.Vehicles;

import PELICAN.Context;
import State.State;
import State.VehiclesSignal;
import State.PedestriansSignal;

public class VehiclesGreen extends State {
    private Context context;
    public VehiclesGreen(Context context) {
        this.context = context;
    }

    /**
     *  Goes into GreenInt or Yellow depending on whether there is a pedestrian waiting or not
     */
    @Override
    public void action() {
        System.out.println("---System is in VehiclesGreen State---");
        signalVehicles(VehiclesSignal.GREEN);
        isPedestrianWaiting = false;
        System.out.println("Traffic Light: GREEN");
        SetTimer(10);
        KillTimer();
    }

    /**
     * In this state, it waits for 5s
     * @param timer: use to Initialise timer upon entry
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
     * In this state, it will decide which state to go to depending if there is a pedestrian
     */
    @Override
    public void KillTimer() {
        System.out.println("System will now decide which state to go to VehiclesGreenInt or VehiclesYellow");

        if (isPedestrianWaiting){
            System.out.println("System is now going into VehiclesYellow State");
            context.setState(new VehiclesYellow(this.context));
        } else{
            System.out.println("System is now going into VehicleGreenInt State");
            context.setState(new VehicleGreenInt(this.context));
        }
    }


    /**
     *
     * @param signal: Use to set the current Vehicle state
     */
    @Override
    public void signalVehicles(VehiclesSignal signal) {

    }

    /**
     *
     * @param signal: Use to set the current Pedestrian state
     */
    @Override
    public void signalPedestrians(PedestriansSignal signal) {

    }

    /**
     * Allows context to change pedestrian is waiting in state machine
     */
    @Override
    public void pedestrianIsWaiting() {
        isPedestrianWaiting = true;
    }



}
