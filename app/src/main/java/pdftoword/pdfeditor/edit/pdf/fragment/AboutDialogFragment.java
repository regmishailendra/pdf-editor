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
        "                <body>  \n" +

        "Please visit this link <br/>\"https://github.com/regmishailendra/<br/>pdf-editor\" <br/>for source code."
        +
        "This software uses iText licenced under AGPL.\n" +
        "\n" +
        "If you look into the source code of iText, you'll see that most files contain a header referring to the AGPL and this applies to this program as well.\n" +
        "\n" +
        "\"This program is free software; you can redistribute it and/or modify it under the terms of the GNU Affero General Public License version 3 as published by the Free Software Foundation with the addition of the following permission added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY ITEXT GROUP NV, ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS\n" +
        "\n" +
        "This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General Public License along with this program; if not, see <br/>" +
        "<br/> http://www.gnu.org/licenses/<br/><br/> or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, 02110-1301 USA, or download the license from the following URL: http://itextpdf.com/terms-of-use/\n" +
        "\n" +
        "The interactive user interfaces in modified source and object code versions of this program must display Appropriate Legal Notices, as required under Section 5 of the GNU Affero General Public License.\n" +
        "\n" +
        "In accordance with Section 7(b) of the GNU Affero General Public License, you must retain the producer line in every PDF that is created or manipulated using iText.\n" +
        "\n" +
        "You can be released from the requirements of the license by purchasing a commercial license. Buying such a license is mandatory as soon as you develop commercial activities involving the iText software without disclosing the source code of your own applications. These activities include: offering paid services to customers as an ASP, serving PDFs on the fly in a web application, shipping iText with a closed source product.\"\n" +
        "\n" +
        "\n" +
        "Full text of the AGPL - GNU AFFERO GENERAL PUBLIC LICENSE\n" +
        "Version 3, 19 November 2007\n" +
        "Copyright (C) 2007 Free Software Foundation, Inc.\n" +
        "Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not allowed.\n" +
        "\n" +
        "Check this link for more -\n" +
        "<br/>https://itextpdf.com/AGPL<br/>"
        +

        "                 This app lets you to make your task easier for extracting any text form pdf and lets you edit those words. After we extract text from pdf, we let you to edit those words. After converting those words you can save those to file. After that we fetch those files from storage and let you edit those files again. We do not take any ownership of use/reuse of any files you use here. We take file storage permission with you so that we can save any text you edited here. Also the storage permission is required because we let you to view the files you edited. We also use third party ad networks here to show you ad and those network use the data according to their way to show you the relevant ads. We use internet permissions.\n" +
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
