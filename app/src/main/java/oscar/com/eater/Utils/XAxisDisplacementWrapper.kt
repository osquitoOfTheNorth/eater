package oscar.com.eater.Utils

/**
 * Created by omenji on 6/14/17.
 */
class XAxisDisplacementWrapper(totalContainerWidth : Int) {
    var mTotalContainerWidth : Int = 0
    var xAxisCoOrdinates : ArrayList<Float> = ArrayList()
    var totalXAxisDisplacement : Float = 0.0f
    internal var startingXCoOrdinate : Float = 0.0f
        get() = (mTotalContainerWidth -  totalXAxisDisplacement) / 2.0f
    init {
        mTotalContainerWidth = totalContainerWidth
    }

    fun updateXCoOrdinates(){
        for(index in 0..(xAxisCoOrdinates.size -1)){
            xAxisCoOrdinates[index] = xAxisCoOrdinates[index] + startingXCoOrdinate
        }
    }

}