package oscar.com.eater.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import oscar.com.eater.pojo.RecipeMetric
import oscar.com.eater.R
import oscar.com.eater.utils.TypefaceCache
import oscar.com.eater.utils.XAxisDisplacementWrapper

/**
 * Created by Oscar on 5/29/2017.
 */
class RecipeMetricView(context :Context, attributes: AttributeSet) : View(context, attributes) {
    internal enum class PositionNumber {
        First, Second, Third
    }
    internal var metrics :  ArrayList<RecipeMetric> = ArrayList()
    internal var mMetricTextPainter = Paint(Paint.ANTI_ALIAS_FLAG)
    internal var mSeperatorTextPainter = Paint(Paint.ANTI_ALIAS_FLAG)
    internal var mMetricTextUnitPainter = Paint(Paint.ANTI_ALIAS_FLAG)
    internal var textPadding : Float = 0.0f
    internal var wordPadding : Float = 0.0f
    internal val mFontName : String = "Nunito-Light.ttf"
    init{
        val array = context.theme.obtainStyledAttributes(attributes, R.styleable.RecipeMetricView, 0, 0)
        try{
            val textColor = context.getColor(array.getResourceId(R.styleable.RecipeMetricView_textColorMetric,R.color.black))
            val textSizeMetric = array.getDimension(R.styleable.RecipeMetricView_metricFontSize,12.0f)
            val textSizeMetricMeasurement = array.getDimension(R.styleable.RecipeMetricView_metricUnitFontSize, 10.0f)
            val separationCharacterColor = context.getColor(array.getResourceId(R.styleable.RecipeMetricView_metricSeperatorColor, R.color.black))
            val fontNameString = array.getString(R.styleable.RecipeMetricView_textFontName)
            var fontForMetric = TypefaceCache.getInstance().getTypeface(context.assets, fontNameString ?: mFontName )
            textPadding = array.getDimension(R.styleable.RecipeMetricView_metricPadding,10.0f)
            wordPadding = context.resources.getDimension(R.dimen.recipe_metrics_word_padding)
            mMetricTextUnitPainter.textSize = textSizeMetricMeasurement
            mMetricTextUnitPainter.color = textColor
            mMetricTextUnitPainter.typeface = fontForMetric

            mMetricTextPainter.textSize = textSizeMetric
            mMetricTextPainter.color = textColor
            mMetricTextPainter.typeface = fontForMetric


            mSeperatorTextPainter.color = separationCharacterColor
            mSeperatorTextPainter.textSize = textSizeMetric
        } catch (e : Exception){

        }
    }

    fun getMetrics() : ArrayList<RecipeMetric>{
        return metrics
    }

    fun setMetrics(newMetrics : ArrayList<RecipeMetric>){
        metrics = newMetrics
        onVisibleAttributeChanged()
    }

    private fun onVisibleAttributeChanged() {
        invalidate()
        requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var index = 0
        var itemsTraversed = 0
        val totalXAxisDisplacement = calculateTotalXAxisDisplacement()
        for(recipeMetric in metrics) {
            var yStartUnit = height - mMetricTextPainter.textSize
            canvas?.drawText("%s".format(recipeMetric.UnitNumber),getXAxisOffset(totalXAxisDisplacement,index,PositionNumber.First),yStartUnit,mMetricTextPainter)
            canvas?.drawText("%s".format(recipeMetric.UnitOfMeasurement),getXAxisOffset(totalXAxisDisplacement,index,PositionNumber.Second),yStartUnit,mMetricTextUnitPainter)
            if(itemsTraversed < (metrics.size - 1)) {
                canvas?.drawText("%s".format(recipeMetric.UnitSeperator), getXAxisOffset(totalXAxisDisplacement,index,PositionNumber.Third), yStartUnit, mSeperatorTextPainter)
            }
            index += 3
            itemsTraversed += 1
        }
    }
    internal fun getXAxisOffset(wrapper: XAxisDisplacementWrapper, index : Int, pos : PositionNumber ) : Float {
        val i = when(pos) {
            PositionNumber.First -> index
            PositionNumber.Second -> index + 1
            PositionNumber.Third -> index + 2
        }
        return wrapper.xAxisCoOrdinates[i]

    }
    internal fun calculateTotalXAxisDisplacement() : XAxisDisplacementWrapper {
        var xOffset = 0.0f
        var index = 0
        var wrapper = XAxisDisplacementWrapper(width)

        for(recipeMetric in metrics){
            xOffset += textPadding
            wrapper.xAxisCoOrdinates.add(xOffset)
            xOffset += mMetricTextPainter.measureText(recipeMetric.UnitNumber) +  wordPadding
            wrapper.xAxisCoOrdinates.add(xOffset)
            xOffset += mMetricTextUnitPainter.measureText(recipeMetric.UnitOfMeasurement) + textPadding
            wrapper.xAxisCoOrdinates.add(xOffset)
            index += 1
        }
        wrapper.totalXAxisDisplacement = xOffset
        wrapper.updateXCoOrdinates()
        return wrapper
    }


}