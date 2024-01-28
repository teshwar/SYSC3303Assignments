package Assignment1;

/**
 * Stimulation of the SandwichMakingChefs Problem (Also known as the Cigarettes Smokers Problem 1971)
 *
 * @author Teshwar Tarachand 101167556
 * @version 1.0
 */
import java.lang.Thread;

/**
 * Stimulate SandwichMakingChefsProblem to count of 20
 */
public class SandwichMakingChefsProblem  {
    public static void main (String[] args) {
        Table table = new Table(20);
        Thread agent = new Thread(new Agent(table));
        Thread breadChef = new Thread(new Chef(Ingredient.BREAD, table));
        Thread cheeseChef = new Thread(new Chef( Ingredient.CHEESE, table));
        Thread peanutButterChef = new Thread(new Chef(Ingredient.PEANUT_BUTTER, table));

        agent.start();
        breadChef.start();
        cheeseChef.start();
        peanutButterChef.start();
    }

}
