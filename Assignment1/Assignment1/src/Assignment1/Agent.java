package Assignment1;
/**
 * Represents Agent that gives out 2 ingredients on Table whenever a Sandwich is eaten
 *
 * @author Teshwar Tarachand 101167556
 * @version 1.0
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class Agent implements Runnable {
    private Table table;

    /**
     * @param table Initialise shared table to keep synchronization between Assignment1.Chef and Assignment1.Agent
     */
    public Agent(Table table){

        this.table = table ;

    }

    /**
     *
     * @return uniqueIngredient a set containing 2 unique ingredients
     */
    private static HashSet<Ingredient> randomIngredients() {
        //Take ingredients and randomise them
        Ingredient[] ingredients = Ingredient.values();

        List<Ingredient> shuffledIngredients = Arrays.asList(ingredients);
        Collections.shuffle(shuffledIngredients);

        // Create a set to store unique values
        HashSet<Ingredient> uniqueIngredient = new HashSet<>();

        // Return a set of two unique values
        uniqueIngredient.add(shuffledIngredients.get(0));
        uniqueIngredient.add(shuffledIngredients.get(1));

        // Print what Assignment1.Agent is putting
        System.out.println("Agent puts "+ shuffledIngredients.get(0) +" and " + shuffledIngredients.get(1));

        return uniqueIngredient;
    }

    /**
     * Code to be run when Assignment1.Agent thread is running. It ends when table amount of sandwich eaten reached max count
     */
    @Override
    public void run() {


            //See if we reach maximum of sandwich to be eaten
            while (table.getSandiwchCount() < table.getMaxSandwichCount()) {
                synchronized (table) {
                //Checks if there is ingredients on table, if there is wait
                while (table.isIngredientOnTable()) {
                    try {
                        table.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e);
                        return;
                    }
                }

                //if not put ingredients and notify everyone
                table.putIngredientOnTable(randomIngredients());

                table.notifyAll();


            }
        }
    }


}


