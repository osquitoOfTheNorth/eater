package oscar.com.eater.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import oscar.com.eater.R;
import oscar.com.eater.utils.TypefaceCache;

/**
 * Created by omenji on 5/12/17.
 */

public class QueRicoTextView extends AppCompatTextView {

    public QueRicoTextView(Context context) {
        super(context);
    }

    public QueRicoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            return;
        }
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.QueRicoTextView);
        int color = styledAttrs.getColor(R.styleable.QueRicoTextView_color, getContext().getColor(R.color.textColor));
        String fontName = styledAttrs.getString(R.styleable.QueRicoTextView_fontType);
        styledAttrs.recycle();
        if (fontName == null) {
            Typeface typeface = TypefaceCache.getInstance().getTypeface(context.getAssets(), "Nunito-Light.ttf");
            this.setTypeface(typeface, typeface.getStyle());
        } else {
            Typeface typeface = TypefaceCache.getInstance().getTypeface(context.getAssets(), fontName);
            this.setTypeface(typeface, typeface.getStyle());
        }
        this.setTextColor(color);
    }


}
