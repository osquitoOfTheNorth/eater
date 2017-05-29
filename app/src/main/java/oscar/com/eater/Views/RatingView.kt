package oscar.com.eater.Views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
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
    internal var mRadiusInner : Float? = null
    internal var mLineThickness : Float? = null
    internal var mCenterCircles : Boolean = false
    internal var paddingBetweenElements : Float? = null
    internal var mDiameter : Float ? = null
    init {
        val array = context.theme.obtainStyledAttributes(attrs, R.styleable.RatingView, 0, 0)
        try {
            mMaxRating = array.getInt(R.styleable.RatingView_maxRating, 5)
            mTotalRating = array.getInt(R.styleable.RatingView_totalRating,0)
            mColor = context!!.getColor(array.getResourceId(R.styleable.RatingView_fillColor, R.color.black))
            mLineThickness = context!!.resources.getDimension(R.dimen.rating_view_outer_line_thickness)
            paddingBetweenElements =  context!!.resources.getDimension(R.dimen.rating_view_spacing_between_elements)
            mCenterCircles = array.getBoolean(R.styleable.RatingView_shouldCenter, false)
            mDiameter = array.getDimension(R.styleable.RatingView_radius, context!!.resources.getDimension(R.dimen.default_rating_radius))
            mContext = context
            init()
        } finally {
            array.recycle()
        }
    }


    fun getMaxRating(): Int {
        return mMaxRating
    }

    fun setMaxRating(maxRating: Int) {
        mMaxRating = maxRating
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
        mPainterOutline!!.strokeWidth = mLineThickness!!
        mPainterOutline!!.style = Paint.Style.STROKE


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mRadiusOuter = (mDiameter!! - mLineThickness!!) / 2.0f
        mRadiusInner = mRadiusOuter!! - mContext!!.resources.getDimension(R.dimen.rating_view_spacing_outer_inner)
        for(i in 0..(mMaxRating - 1)){
            var centerX = getRadiusCenter(i,mRadiusOuter!!)
            if(mCenterCircles){
                val padding = (width - (((mRadiusOuter!! * 2) * mMaxRating) + (paddingBetweenElements!! * (mMaxRating - 1)))) / 2
                centerX += (padding + mLineThickness!!)
            }
            canvas.drawCircle(centerX, height / 2.0f, mRadiusOuter!!, mPainterOutline)
            if(i < mTotalRating) {
                canvas.drawCircle(centerX, height / 2.0f, mRadiusInner!!, mPainterFill)
            }
        }

    }

    private fun getRadiusCenter(index : Int, radius : Float) : Float{
        return (((2*index) + 1) * radius) + index * paddingBetweenElements!!
    }
}