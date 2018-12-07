package oscar.com.eater.pojo

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import oscar.com.eater.utils.SpecialDeserializationWrapperClass
import java.io.Serializable

/**
 * Created by Oscar on 7/4/2017.
 */
class Directions : Serializable, SpecialDeserializationWrapperClass() {

    var directions = ArrayList<Direction>()

    override fun getWrapper(): Directions {
        return this
    }

    override fun setWrapperJsonObject(json : JsonElement?) {
        var fromJson = Gson().fromJson(json,Direction::class.java)
        directions.add(fromJson)
    }

    override fun setWrapperJsonArray(json : JsonElement?) {
        val listType = object : TypeToken<ArrayList<Direction>>(){}.type
        directions = Gson().fromJson(json,listType)
    }
}