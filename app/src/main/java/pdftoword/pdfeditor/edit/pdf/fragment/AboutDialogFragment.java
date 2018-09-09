package pdftoword.pdfeditor.edit.pdf.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import pdftoword.pdfeditor.edit.pdf.R;


/**
 * Created by shailendra on 7/23/18.
 */

public class AboutDialogFragment extends DialogFragment {

private String HTML_TEXT="" +
        "";

  private Button btn;

private String CREDITS_TEXT="Icon made by<br/> " +
        "<a href=\"https://www.alfredocreates.com/\">Alfredo Hernandez</a> from <a href=\"https://www.flaticon.com\">www.flaticon.com</a><br/>";

private String mainText=HTML_TEXT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_layout,container,false);
        WebView webview = v.findViewById(R.id.web_view);
        Bundle bundle= getArguments();



if(bundle!=null){

       String type=bundle.getString("t");
        if(type.matches("c")){
            mainText=CREDITS_TEXT;
        }
        else if(type.matches("a")){
            mainText=HTML_TEXT;
        }
}

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.loadDataWithBaseURL("", mainText, "text/html", "UTF-8", "");

//        btn=v.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });

        return v;
    }
}
