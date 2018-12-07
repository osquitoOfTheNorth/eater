package oscar.com.eater.request

import okhttp3.OkHttpClient
import oscar.com.eater.ApplicationContants
import oscar.com.eater.ApplicationContants.hostUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by oscmenji on 2018-04-02.
 */
object ServiceAccessor {
     var recipeService : RecipeService
     init {
         val client = OkHttpClient().newBuilder().addInterceptor {
             val request = it.request()
             val url = it.request().url()
                       .newBuilder().addQueryParameter(ApplicationContants.appIDQueryParam,ApplicationContants.appID)
                       .addQueryParameter(ApplicationContants.appKeyQueryParam,ApplicationContants.apiKey).build()
             it.proceed(request.newBuilder().url(url).build())
         }.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(hostUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        recipeService = retrofit.create(RecipeService::class.java)
    }
}