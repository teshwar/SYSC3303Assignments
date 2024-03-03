package State.Vehicles;

import PELICAN.Context;
import State.State;
import State.VehiclesSignal;
import State.PedestriansSignal;

public class VehicleGreenInt extends State {
    private Context context;
    public VehicleGreenInt(Context context) {
        this.context = context;
    }

    /**
     * Stays in green state until there is a pedestrian waiting
     */
    @Override
    public void action() {
        System.out.println("---System is in VehiclesGreenInt State---");
        System.out.println("Traffic Light: GREEN");
        System.out.println("No pedestrian waiting");

        //Pedestrian signal
        if(isPedestrianWaiting){
            System.out.println("Traffic Light: GREEN");
            System.out.println("Going yellow in 10s");

            //I did not use SetTimer as it was not shown in the state diagram
            try {
                Thread.sleep(10 * 1000);;
            } catch (InterruptedException e) {
                System.err.println(e);
            }

            System.out.println("System is now going into VehiclesYellow State");
            context.setState(new VehiclesYellow(context));
        }
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
        isPedestrianWaiting = true;
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
