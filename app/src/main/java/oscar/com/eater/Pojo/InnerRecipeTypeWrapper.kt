package oscar.com.eater.Pojo

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import oscar.com.eater.Utils.SpecialDeserializationWrapperClass
import java.io.Serializable

/**
 * Created by omenji on 5/25/17.
 */

class InnerRecipeTypeWrapper : Serializable, SpecialDeserializationWrapperClass() {
    var recipeTypesInnerList: ArrayList<String> = ArrayList()

    override fun setWrapperJsonObject(json : JsonElement?) {
        var fromJson = Gson().fromJson(json,String::class.java)
        recipeTypesInnerList.add(fromJson)
    }

    override fun setWrapperJsonArray(json : JsonElement?) {
        val listType = object : TypeToken<ArrayList<String>>(){}.type
        recipeTypesInnerList = Gson().fromJson(json,listType)
    }

    override fun getWrapper(): InnerRecipeTypeWrapper {
        return this
    }
}