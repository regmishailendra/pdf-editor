package pdftoword.pdfeditor.edit.pdf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import pdftoword.pdfeditor.edit.pdf.BaseActivity;
import pdftoword.pdfeditor.edit.pdf.R;
import pdftoword.pdfeditor.edit.pdf.fragment.MainFragment;


public class SplashActivity extends BaseActivity {
            private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);

                        if(mInterstitialAd.isLoaded()){
                            mainIntent.putExtra("a",true);
                        }
                        else{
                            mainIntent.putExtra("a",false);
                            MainFragment.adCount--;

                        }
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                }, SPLASH_DISPLAY_LENGTH);
            }


    @Override
    protected void onPause() {
        super.onPause();
      showInter(false);

    }





}

