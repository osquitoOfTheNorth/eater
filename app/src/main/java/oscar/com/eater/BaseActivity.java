package oscar.com.eater;

import android.Manifest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public abstract class BaseActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    private final int InternetPermissionRequestCode = 111;
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    InternetPermissionRequestCode);
        }
        addFragment();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case InternetPermissionRequestCode: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addFragment();
                }
            }
        }
    }

    public void addFragmentToStack(Fragment fragment, int in, int out){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(in, out)
                   .addToBackStack("TAG")
                   .replace(R.id.activity_base,fragment)
                   .commit();
    }


    public void addFragmentToStack(Fragment fragment){
        addFragmentToStack(fragment, R.anim.fade_in_animation, 0);
    }
    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_base);
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                           .add(R.id.activity_base, fragment)
                           .commit();
        }
    }

}
