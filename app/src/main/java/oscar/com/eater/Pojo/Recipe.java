package oscar.com.eater.Pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by omenji on 3/16/17.
 */

public class Recipe implements Serializable{

    @SerializedName("id")
    public int id;
    @SerializedName("vegetarian")
    public boolean isVegetarian;
    @SerializedName("vegan")
    public boolean isVegan;
    @SerializedName("cheap")
    public boolean isCheap;
    @SerializedName("glutenFree")
    public boolean isGlutenFree;
    @SerializedName("sourceUrl")
    public String sourceUrl;
    @SerializedName("healthScore")
    public double healthScore;
    @SerializedName("title")
    public String recipeName;
    @SerializedName("image")
    public String imageSrcUrl;
    @SerializedName("readyInMinutes")
    public int totalPreperationTimeInMinutes;
    @SerializedName("extendedIngredients")
    public List<Ingredient> ingredients;
    @SerializedName("sourceName")
    public String sourceName;


    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }



    public double getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(double pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    @SerializedName("pricePerServing")
    public double pricePerServing;

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isCheap() {
        return isCheap;
    }

    public void setCheap(boolean cheap) {
        isCheap = cheap;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(double healthScore) {
        this.healthScore = healthScore;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getImageSrcUrl() {
        return imageSrcUrl;
    }

    public void setImageSrcUrl(String imageSrcUrl) {
        this.imageSrcUrl = imageSrcUrl;
    }

    public int getTotalPreperationTimeInMinutes() {
        return totalPreperationTimeInMinutes;
    }

    public void setTotalPreperationTimeInMinutes(int totalPreperationTimeInMinutes) {
        this.totalPreperationTimeInMinutes = totalPreperationTimeInMinutes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
