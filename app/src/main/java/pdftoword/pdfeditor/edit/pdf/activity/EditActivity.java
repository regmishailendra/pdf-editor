package pdftoword.pdfeditor.edit.pdf.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import pdftoword.pdfeditor.edit.pdf.BaseActivity;
import pdftoword.pdfeditor.edit.pdf.R;

public class EditActivity extends BaseActivity {

    private EditText et;
    Button save;
    private StringBuilder text = new StringBuilder();
    private File file;
    private String fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        save=findViewById(R.id.save);
        et=findViewById(R.id.et);
         fileUri = getIntent().getStringExtra("u");
         file = new File(fileUri);
        BufferedReader reader = null;
        et.setScroller(new Scroller(this));
        et.setVerticalScrollBarEnabled(true);
        et.setMovementMethod(new ScrollingMovementMethod());
        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
                Log.v("charactercheck",text.toString());


            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }

            et.setText((CharSequence) text);



            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addContentsToFile();
                }
            });




        }


    }

    private void addContentsToFile() {
        final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setMessage("Converting to text please wait...");
        dialog.setCancelable(false);
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {


try{

if(file.exists()){
    file.delete();
}

     File file = new File(fileUri);
    FileWriter writer = new FileWriter(file,true);
    writer.write(et.getText().toString());
    writer.flush();
    writer.close();


                   runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast.makeText(EditActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });


                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }).start();
    }


    @Override
    public void onBackPressed() {
            finish();
    }
}
