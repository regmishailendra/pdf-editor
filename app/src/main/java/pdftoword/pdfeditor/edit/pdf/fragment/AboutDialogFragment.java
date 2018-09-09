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

private String HTML_TEXT="<html>  \n" +
        "                <title>Welcome</title>  \n" +
        "                <body>  \n" +
        "                <font color=\\\\red\\\\><center>Privacy Policy<br/></center></font>  \n" +
        "                 This app lets you to make your task easier for extracting any text form pdf and lets you edit those words. After we extract tect from pdf, we let you to edit those words. After converting those words you can save those to file. After that we fetch those files from storage and let you edit those files again. We do not take any ownership of use/reuse of any files you use here. We take file storage permission with you so that we can save any text you edited here. Also the storage permission is required because we let you to view the files you edited. We also use third party ad networks here to show you ad and those network use the data according to their way to show you the relevant ads. We use internet permissions.\n" +
        "                  \n" +
        "                  \n" +
        "                      \n" +
        "                  \n" +
        "                </body>  \n" +
        "                  \n" +
        "                </html>";

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
