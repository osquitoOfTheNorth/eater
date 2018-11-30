package oscar.com.eater.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import oscar.com.eater.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int FIREBASE_SIGN_IN = 1111;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mFireBaseAuth = FirebaseAuth.getInstance();
        if(mFireBaseAuth.getCurrentUser() != null){
            startActivity(getSearchActivityIntent());
        } else {
            List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());
            startActivityForResult(AuthUI
                    .getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.drawable.auth_icon)
                    .setTheme(R.style.EaterThemeFirebase)
                    .build(), FIREBASE_SIGN_IN);
        }
    }

    private Intent getSearchActivityIntent(){
        return new Intent(getApplicationContext(), SearchActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == FIREBASE_SIGN_IN){
            if(resultCode == RESULT_OK){
                startActivity(getSearchActivityIntent());
            } else {
                Toast.makeText(this, getString(R.string.sign_in_error) , Toast.LENGTH_LONG).show();
                startActivity(getSearchActivityIntent());
            }
        }
    }
}
