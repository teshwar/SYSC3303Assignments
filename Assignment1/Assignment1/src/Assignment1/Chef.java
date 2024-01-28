package Assignment1;
/**
 * Represents Chef class takes 2 required ingredients to make Sandwich and eat
 *
 * @author Teshwar Tarachand 101167556
 * @version 1.0
 */

public class Chef implements Runnable {

    private Ingredient type;
    private Table table;

    /**
     * @param type  initialise type of chef to be
     * @param table initialise shared table between chefs and Assignment1.Agent
     */
    public Chef(Ingredient type, Table table) {
        this.type = type;
        this.table = table;
    }

    @Override
    public void run() {
            //See if we reach maximum of sandwich to be eaten
            while (table.getSandiwchCount() < table.getMaxSandwichCount()) {

                synchronized (table) {
                //Checks if there is ingredients on table, and if chef has it
                while (table.isIngredientOnTable() && table.ingredientsOnTable().contains(this.type)) {
                    try {
                        table.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e);
                        return;
                    }
                }


                //Assignment1.Chef mae and eat the sandwich and notify everyone
                if (table.isIngredientOnTable()) {
                    table.eatsSandwich(this.type);

                table.notifyAll();

                }

            }
        }
    }
}
