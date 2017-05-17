package oscar.com.eater.Observables;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by omenji on 3/21/17.
 */

public class ObservableImageRequest implements ObservableOnSubscribe<Bitmap> {

    private final String mImageUrl;

    public ObservableImageRequest(String imageUrlSrc){
        mImageUrl = imageUrlSrc;
    }

    @Override
    public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(new Request.Builder().url(mImageUrl).build()).execute();
        InputStream inputStream = response.body().byteStream();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        e.onNext(bitmap);
        e.onComplete();
    }


}
