package Assignment1;
/**
 * Represents Table class which is shared among Agent and Chefs
 *
 * @author Teshwar Tarachand 101167556
 * @version 1.0
 */

import java.util.HashSet;
public class Table {
    private int CurrentSandwichCount;
    private int MaxSandwichCount;
    private HashSet<Ingredient> ingredientOnTable ;

    public Table (int MaxSandwichCount){
        this.MaxSandwichCount = MaxSandwichCount ;
        this.CurrentSandwichCount = 0;
        this.ingredientOnTable = new HashSet<>();
    }

    /**
     *
     * @param ingredientOnTable : take ingredient set and set it on table
     */
    public void putIngredientOnTable(HashSet<Ingredient> ingredientOnTable) {
        if (this.ingredientOnTable.isEmpty()){
            this.ingredientOnTable = ingredientOnTable;
        }
        else {
            System.err.println("Error: Assignment1.Agent tried to put ingredient when tables had ingredients");
        }

    }

    /**
     *  Chef has eaten a sandwich
     */
    public void eatsSandwich(Ingredient Type){
        this.ingredientOnTable.clear();
        System.out.println("A chef with " + Type +" made a sandwich" );
        this.CurrentSandwichCount++ ;
        System.out.println("Current Count: " + this.CurrentSandwichCount + " | Maximum Sandwich: " + this.MaxSandwichCount);

    }

    /**
     *
     * @return ingredientOnTable returns the current ingredients on the table
     */
    public HashSet<Ingredient> ingredientsOnTable(){
        return ingredientOnTable ;
    }
    /**
     *
     * @return boolean to check if there is currently any ingredients in the table or not
     */
    public boolean isIngredientOnTable(){
        return !ingredientOnTable.isEmpty();
    }

    /**
     *
     * @return int that is the current sandwich count
     */
    public int getSandiwchCount(){
        return CurrentSandwichCount ;
    }

    /**
     *
     * @return int that is maximum sandwich to be made by the user
     */
    public int getMaxSandwichCount(){
        return MaxSandwichCount ;
    }


}
