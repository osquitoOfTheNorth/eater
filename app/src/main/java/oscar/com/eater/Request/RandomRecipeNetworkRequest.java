package oscar.com.eater.Request;

import okhttp3.HttpUrl;
import okhttp3.Request;
import oscar.com.eater.ApplicationContants;

/**
 * Created by omenji on 3/16/17.
 */

public class RandomRecipeNetworkRequest {

    private HttpUrl.Builder mHttpBuilder;
    private Request.Builder mRecipeNetworkRequest;
    public RandomRecipeNetworkRequest(){

        mHttpBuilder = new HttpUrl.Builder();
        mHttpBuilder.scheme(ApplicationContants.getHostScheme());
        mHttpBuilder.host(ApplicationContants.getHostUrl());
        mHttpBuilder.addPathSegment("recipes");
        mHttpBuilder.addPathSegment("random");
        mRecipeNetworkRequest = new Request.Builder();
        mRecipeNetworkRequest.addHeader(ApplicationContants.getApiKeyMashapeHeaderName(),ApplicationContants.getApiKeyMashape());

    }

    public Request getRandomRecipes(int numberOfResults){
        String numberOfResultsKey = "number";
        mHttpBuilder.addEncodedQueryParameter(numberOfResultsKey, String.valueOf(numberOfResults));
        return mRecipeNetworkRequest.url(mHttpBuilder.build()).build();
    }
}
