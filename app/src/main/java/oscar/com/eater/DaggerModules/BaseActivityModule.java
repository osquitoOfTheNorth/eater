package oscar.com.eater.DaggerModules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;


/**
 * Created by omenji on 3/21/17.
 */

@Module
public class BaseActivityModule {

    private final Context mContext;
    public BaseActivityModule(Context context){
        mContext = context;
    }

    @Provides
    public Context context(){
        return mContext;
    }

}


