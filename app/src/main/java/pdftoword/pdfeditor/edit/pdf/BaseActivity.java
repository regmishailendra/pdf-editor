package pdftoword.pdfeditor.edit.pdf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import pdftoword.pdfeditor.edit.pdf.activity.MainActivity;
import pdftoword.pdfeditor.edit.pdf.fragment.MainFragment;


/**
 * Created by shailendra on 7/24/18.
 */
/*This program is free software; you can redistribute it and/or modify it under the terms of the GNU Affero General Public License version 3 as published by the Free Software Foundation with the addition of the following permission added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY ITEXT GROUP NV, ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General Public License along with this program; if not, see http://www.gnu.org/licenses/ or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, 02110-1301 USA, or download the license from the following URL: http://itextpdf.com/terms-of-use*/

public abstract class BaseActivity extends AppCompatActivity {
    public InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));

        loadInter();

        showBanner();

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
        }
    }

    public void loadInter() {
//        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        Log.d("intercheck", "inter ad new request to load");

    }


    public void showBanner() {
        Log.d("TAG", "banner load in pdf searcher");

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        if (mAdView != null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
    }


    public void showInter() {
        Log.d("TAG", "method called");
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
                  loadInter();

        }


        else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }


    public void showInter(boolean loadAd) {
        Log.d("TAG", "method called");
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            //MainFragment.isFirstTime=true;
            MainFragment.isAdShown=true;
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showInter();
    }

    public boolean shouldExit = false;
    public boolean shouldShowBackMessage = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (shouldShowBackMessage) {
            Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT).show();
        }
    }


}






