package oscar.com.eater.Views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import oscar.com.eater.Pojo.RecipeMetric
import oscar.com.eater.R

/**
 * Created by Oscar on 5/29/2017.
 */
class RecipeMetricView(context :Context, attributes: AttributeSet) : View(context, attributes) {
    internal var metrics :  ArrayList<RecipeMetric> = ArrayList()
    internal var mMetricTextPainter = Paint(Paint.ANTI_ALIAS_FLAG)
    internal var mSeperatorTextPainter = Paint(Paint.ANTI_ALIAS_FLAG)
    internal var mMetricTextUnitPainter = Paint(Paint.ANTI_ALIAS_FLAG)
    internal var textPadding : Float = 10.0f
    init{
        val array = context.theme.obtainStyledAttributes(attributes, R.styleable.RecipeMetricView, 0, 0)
        try{
            var textColor = context!!.getColor(array.getResourceId(R.styleable.RecipeMetricView_textColorMetric,R.color.black))
            var textSizeMetric = array.getDimension(R.styleable.RecipeMetricView_metricFontSize,12.0f)
            var textSizeMetricMeasurement = array.getDimension(R.styleable.RecipeMetricView_metricUnitFontSize, 10.0f)
            var separationCharacterColor = context!!.getColor(array.getResourceId(R.styleable.RecipeMetricView_metricSeperatorColor, R.color.black))
            textPadding = array.getDimension(R.styleable.RecipeMetricView_metricPadding,10.0f)
            mMetricTextUnitPainter.textSize = textSizeMetricMeasurement
            mMetricTextUnitPainter.color = textColor
            mMetricTextPainter.textSize = textSizeMetric
            mMetricTextPainter.color = textColor
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
        var xOffset = 0.0f
        for(recipeMetric in metrics) {
            var yStartUnit = height - mMetricTextPainter.textSize
            xOffset += textPadding
            canvas?.drawText("%s".format(recipeMetric.UnitNumber),xOffset,yStartUnit,mMetricTextPainter)

            xOffset += mMetricTextPainter.measureText(recipeMetric.UnitNumber) + textPadding
            canvas?.drawText("%s".format(recipeMetric.UnitOfMeasurement),xOffset,yStartUnit,mMetricTextUnitPainter)

            xOffset += mMetricTextUnitPainter.measureText(recipeMetric.UnitOfMeasurement) + textPadding
            canvas?.drawText("%s".format(recipeMetric.UnitSeperator),xOffset,yStartUnit,mSeperatorTextPainter)
        }
    }


}