package co.orah.hackathon.SpritzFiller.app.SpritzFiller;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by videostitch on 25-Aug-17.
 */

public class CocktailRecipe {
    private ArrayList<CocktailIngredient> ingredients;

    public CocktailRecipe(CocktailIngredient[] ingredients) {
        this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
        //compute proportions
        float totalVolume = 0;
        for (CocktailIngredient ingredient: ingredients) {
            totalVolume += ingredient.getVolumes();
        }
        for (CocktailIngredient ingredient: ingredients) {
            ingredient.setProportion(ingredient.getVolumes() / totalVolume);
        }
    }
    public ArrayList<CocktailIngredient> getIngredients() {
        return this.ingredients;
    }
}
