package oscar.com.eater.Observers;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.widget.ImageView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by omenji on 3/21/17.
 */

public class ObserverImageDownloader implements Observer<Bitmap> {

    private ImageView mHolder;
    private Animation mAnimation;

    public ObserverImageDownloader(ImageView imageView, Animation animation){
        mHolder = imageView;
        mAnimation = animation;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Bitmap value) {
        mHolder.setAnimation(mAnimation);
        mHolder.setImageBitmap(value);

    }
    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}

