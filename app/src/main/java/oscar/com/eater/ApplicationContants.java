package oscar.com.eater;

/**
 * Created by omenji on 3/16/17.
 */

public class ApplicationContants {

    private static final String ApiKeyMashape = "vw9araJR53mshxYNeoOKZqfRRWeOp16Or0SjsnDQCxqlXxeSVJ";
    private static final String ApiKeyMashapeHeaderName = "X-Mashape-Key";
    private static final String HostEndPoint = "spoonacular-recipe-food-nutrition-v1.p.mashape.com";
    private static final String HostScheme = "https";

    public static String getRecipeSerializableKey() {
        return RecipeSerializableKey;
    }

    private static final String RecipeSerializableKey = "recipe_serialized";

    public static String getHostScheme(){
        return HostScheme;
    }
    public static String getHostUrl() {
        return HostEndPoint;
    }
    public static String getApiKeyMashape() {
        return ApiKeyMashape;
    }
    public static String getApiKeyMashapeHeaderName() {
        return ApiKeyMashapeHeaderName;
    }
}
