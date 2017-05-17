package oscar.com.eater.Request;

import okhttp3.HttpUrl;
import okhttp3.Request;
import oscar.com.eater.ApplicationContants;

/**
 * Created by omenji on 3/29/17.
 */

public class AnalyzedIntructionsRequest {

    int mRecipeID;
    HttpUrl.Builder mHttpBuilder;
    Request.Builder mRequestBuilder;

    public AnalyzedIntructionsRequest(int recipeID){
        mRecipeID = recipeID;
        mHttpBuilder = new HttpUrl.Builder();
        mHttpBuilder.scheme(ApplicationContants.getHostScheme());
        mHttpBuilder.host(ApplicationContants.getHostUrl());
        mHttpBuilder.addPathSegment("recipes");
        mHttpBuilder.addPathSegment(String.valueOf(recipeID));
        mHttpBuilder.addPathSegment("analyzedInstructions");
        mRequestBuilder = new Request.Builder();
        mRequestBuilder.addHeader(ApplicationContants.getApiKeyMashapeHeaderName(),ApplicationContants.getApiKeyMashape());
    }

    public Request getAnalyzedInstructions(){
        return mRequestBuilder.url(mHttpBuilder.build()).build();
    }
}
