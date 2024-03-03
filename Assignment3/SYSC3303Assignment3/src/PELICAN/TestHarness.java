package PELICAN;
import java.lang.Thread;
public class TestHarness {
        public static void main(String[] args) throws InterruptedException {
        Context context = new Context();

        //Thread.sleep(11000); //To go in Greentint state
        Thread.sleep(2000); //To go in VehiclesYellow directly

        context.pedestrianWaiting();
        //timeout occurs within code but there is a function

        //goes into infinity loop and stuck in VehiclesGreenInt state till we get a pedestrian signal



    }
}
