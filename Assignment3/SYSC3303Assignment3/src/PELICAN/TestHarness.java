package PELICAN;
import java.lang.Thread;

/**
 * Simulate the overall system/context
 *
 * @author Teshwar Tarachand
 * @version 1.0
 */


public class TestHarness {
        public static void main(String[] args) throws InterruptedException {
        Context context = new Context();

        Thread.sleep(11000); //To go in Greentint state
        //Thread.sleep(2000); //To go in VehiclesYellow directly

        context.pedestrianWaiting();
        //timeout occurs within code but there is a function

        //goes into infinity loop and stuck in VehiclesGreenInt state till we get a pedestrian signal



    }
}
