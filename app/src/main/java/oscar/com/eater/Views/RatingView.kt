package oscar.com.eater.Views

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.RadioButton
import oscar.com.eater.R

/**
 * Created by omenji on 5/26/17.
 */
class RatingView(context: Context, attrs: AttributeSet) : View(context,attrs) {

    internal var mContext: Context? = null
    internal var mMaxRating: Int = 0
    internal var mTotalRating: Int = 0
    internal var mColor: Int = 0
    internal var mPainterFill: Paint? = null
    internal var mPainterOutline: Paint? = null
    internal var context : Context? = null
    internal var mRadiusOuter : Float? = null
    internal var mRadiousInner : Float? = null

    init {
        val array = context.theme.obtainStyledAttributes(attrs, R.styleable.PrepTime, 0, 0)
        try {
            mMaxRating = array.getInt(R.styleable.RatingView_maxRating, 5)
            mTotalRating = array.getInt(R.styleable.RatingView_totalRating, 0)
            mColor = array.getColor(R.styleable.RatingView_fillColor,0)
            mContext = context
            init()
        } finally {
            array.recycle()
        }
    }


    fun getMaxRating(): Int {
        return mColor
    }

    fun setMaxRating(maxRating: Int) {
        mColor = maxRating
        onVisibleAttributeChanged()
    }


    fun getTotalRating(): Int {
        return mTotalRating
    }

    fun setTotalRating(rating: Int) {
        mTotalRating = rating
        onVisibleAttributeChanged()
    }


    private fun onVisibleAttributeChanged() {
        invalidate()
        requestLayout()
    }


    private fun init() {
        mPainterFill = Paint(Paint.ANTI_ALIAS_FLAG)
        mPainterFill!!.color = mColor
        mPainterFill!!.style = Paint.Style.FILL
        mPainterOutline = Paint(Paint.ANTI_ALIAS_FLAG)
        mPainterOutline!!.color = mColor
        mPainterFill!!.style = Paint.Style.STROKE

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mRadiusOuter = (width / mMaxRating) - (10.0f * mMaxRating)
        mRadiousInner = mRadiusOuter!! - 5.0f
        for(i in 0..(mMaxRating - 1)){
            var centerX = getRadiusCenter(i,mRadiusOuter!!)
            canvas.drawCircle(centerX,height/2.0f, mRadiusOuter!!, mPainterOutline)
            if(i < mTotalRating) {
                canvas.drawCircle(centerX, height / 2.0f, mRadiousInner!!, mPainterFill)
            }
        }

    }

    private fun getRadiusCenter(index : Int, radius : Float) : Float{
        return (((2*index) + 1) * radius) + index * 5
    }
}