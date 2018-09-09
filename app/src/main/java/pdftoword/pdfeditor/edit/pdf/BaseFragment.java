package pdftoword.pdfeditor.edit.pdf;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * Created by shailendra on 7/26/18.
 */

public class BaseFragment extends Fragment {

    private ProgressBar progressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         loadAd(view);
        // progressBar=view.findViewById(R.id.pb);

    }

    public void loadAd(View v){
        AdView mAdView;
        mAdView = v.findViewById(R.id.adView);
        if(mAdView!=null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }}

        public void showProgress(){
        if (progressBar!=null){
            progressBar.setVisibility(View.VISIBLE);}
        }

        public void hideProgress(){
        if (progressBar!=null){
            progressBar.setVisibility(View.GONE);}
        }



}
