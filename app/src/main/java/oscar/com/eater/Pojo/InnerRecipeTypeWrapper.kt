package oscar.com.eater.Pojo

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import oscar.com.eater.Utils.DeserializationUtil
import java.io.Serializable
import java.lang.reflect.Type

/**
 * Created by omenji on 5/25/17.
 */

/*Stupid object required because of api response structure
      recipe_types {
         recipe_type : [
          ....
         ]
      }


  BUT THEN ALSO DEPENDING ON # OF ENTRIES:
      recipe_types{
         recipe_type : {

         ...
         }
     }
*/

class InnerRecipeTypeWrapper : Serializable, DeserializationUtil() {
    var recipeTypesInnerList: ArrayList<String> = ArrayList()


    companion object RecipeTypeWrapperDeserializer : JsonDeserializer<InnerRecipeTypeWrapper> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): InnerRecipeTypeWrapper {
            var ar = ArrayList<String>()
            if(json is JsonArray){
                val listType = object : TypeToken<ArrayList<String>>() {}.type
                ar = Gson().fromJson(json,listType)
            } else {
                var Deserialized = Gson().fromJson(json,String::class.java)
                ar.add(Deserialized)

            }
            var wrapper = InnerRecipeTypeWrapper()
            wrapper.recipeTypesInnerList = ar
            return wrapper
        }
    }
}