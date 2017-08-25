package co.orah.hackathon.SpritzFiller.app.SpritzFiller;

/**
 * Created by videostitch on 25-Aug-17.
 */

public class CocktailIngredient {
    private String name;
    private float volumes;
    private float proportion;

    public CocktailIngredient(String name, float volumes) {
        this.name = name;
        this.volumes = volumes;
    }
    public String getName() {
        return this.name;
    }
    public float getVolumes() {
        return this.volumes;
    }
    public void setProportion(float proportion) {
        this.proportion = proportion;
    }
    public float getProportion() {
        return this.proportion;
    }
}
