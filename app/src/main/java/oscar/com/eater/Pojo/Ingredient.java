package oscar.com.eater.Pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by omenji on 3/16/17.
 */

public class Ingredient implements Serializable {

    @SerializedName("aisle")
    public String aisleClassifcation;
    @SerializedName("image")
    public String ingredientImageUrl;
    @SerializedName("name")
    public String ingredientName;
    @SerializedName("amount")
    public Double unitAmountRequired;
    @SerializedName("unitShort")
    public String unitOfMeasurementLong;
    @SerializedName("unitLong")
    public String unitOfMeasurementShort;

    public String getAisleClassifcation() {
        return aisleClassifcation;
    }

    public void setAisleClassifcation(String aisleClassifcation) {
        this.aisleClassifcation = aisleClassifcation;
    }

    public String getIngredientImageUrl() {
        return ingredientImageUrl;
    }

    public void setIngredientImageUrl(String ingredientImageUrl) {
        this.ingredientImageUrl = ingredientImageUrl;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Double getUnitAmountRequired() {
        return unitAmountRequired;
    }

    public void setUnitAmountRequired(Double unitAmountRequired) {
        this.unitAmountRequired = unitAmountRequired;
    }

    public String getUnitOfMeasurementLong() {
        return unitOfMeasurementLong;
    }

    public void setUnitOfMeasurementLong(String unitOfMeasurementLong) {
        this.unitOfMeasurementLong = unitOfMeasurementLong;
    }

    public String getUnitOfMeasurementShort() {
        return unitOfMeasurementShort;
    }

    public void setUnitOfMeasurementShort(String unitOfMeasurementShort) {
        this.unitOfMeasurementShort = unitOfMeasurementShort;
    }
}
