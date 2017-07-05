package oscar.com.eater.Utils

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import oscar.com.eater.Pojo.InnerRecipeTypeWrapper
import java.lang.reflect.Type

/**
 * Created by omenji on 7/4/17.
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

  i.e list vs JSON object.
*/
abstract class SpecialDeserializationWrapperClass : JsonDeserializer<SpecialDeserializationWrapperClass>  {

        abstract fun getWrapper() : SpecialDeserializationWrapperClass
        abstract fun setWrapperJsonObject(json : JsonElement?)
        abstract fun setWrapperJsonArray(json : JsonElement?)
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): SpecialDeserializationWrapperClass {
            if(json is JsonArray){
                setWrapperJsonArray(json)
            } else {
                setWrapperJsonObject(json)
            }
            return getWrapper()
        }
}