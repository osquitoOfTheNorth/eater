package oscar.com.eater.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import oscar.com.eater.Pojo.Recipe;

/**
 * Created by omenji on 3/17/17.
 */

public class RandomRecipeRequestResponse {

    @SerializedName("recipes")
    public List<Recipe> products;

    public List<Recipe> getProducts() {
        return products;
    }

    public void setProducts(List<Recipe> products) {
        this.products = products;
    }
}
