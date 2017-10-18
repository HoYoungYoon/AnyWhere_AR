package io.github.anywhere.mixare.mixare_ar;



import android.app.Activity;
import android.os.Bundle;

import io.github.anywhere.R; ;


public class MixareActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /* Splash(로딩화면) 띄우기 */
     //   startActivity(new Intent(MixareActivity.this, Splash.class));
        
        finish();
    }
}