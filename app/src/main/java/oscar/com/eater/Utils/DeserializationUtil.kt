package oscar.com.eater.Utils

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import oscar.com.eater.Pojo.InnerRecipeTypeWrapper
import java.lang.reflect.Type

/**
 * Created by omenji on 7/4/17.
 */
abstract class DeserializationUtil<T> : JsonDeserializer<Wrapper<T>>  {

        abstract fun getWrapper() : Wrapper<T>

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Wrapper<T> {
            var ar = ArrayList<T>()
            if(json is JsonArray){
                val listType = object : TypeToken<ArrayList<T>>(){}.type
                ar = Gson().fromJson(json,listType)
            } else {
                val type = object : TypeToken<T>(){}.type
                var Deserialized = Gson().fromJson(json,type)
                ar.add(Deserialized)

            }
            getWrapper().setWrapper(ar)
            return getWrapper()
        }
}