package oscar.com.eater.DaggerModules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import oscar.com.eater.BaseActivity;

@Component(modules = {BaseActivityModule.class})
@Singleton
public interface BaseActivityComponent{

    Context context();
    void inject(BaseActivity activity);

}
