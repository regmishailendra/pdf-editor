package pdftoword.pdfeditor.edit.pdf.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import pdftoword.pdfeditor.edit.pdf.R;
import pdftoword.pdfeditor.edit.pdf.activity.EditActivity;


public class RecyclerViewDownloadedAdapter extends RecyclerView.Adapter<RecyclerViewDownloadedAdapter.MyViewHolder> {
    private final List<String> fileList;
    private Activity activity;


    public RecyclerViewDownloadedAdapter(List<String> fileList, Activity activity) {
        this.fileList = fileList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_files, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
          holder.tv_name.setText(fileList.get(position));


          holder.lin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
//                  Uri file = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", new File(Environment.getExternalStorageDirectory().toString() + "/Pdf to Word/" + parentName+"/" + fileList.get(position)));
//                  Intent intent = new Intent(Intent.ACTION_VIEW);
//                  intent.setDataAndType(file, "application/pdf");
//                  intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                  intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                  activity.startActivity(intent);
                  //click here
                  Uri file = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", new File(Environment.getExternalStorageDirectory().toString() + "/Pdf to Word/"+fileList.get(position)));
                  Intent intent = new Intent(Intent.ACTION_VIEW);
                  intent.setData(file);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                  intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

             try {
                 activity.startActivity(intent);
             }catch (Exception e){
                 Toast.makeText(activity, "Your phone does not have application to open this.", Toast.LENGTH_SHORT).show();
             }
              }
          });


        holder.cv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Opening editor please wait...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                    }
                },5000);
                Intent editIntent = new Intent(activity, EditActivity.class);

                Uri fileUri=FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", new File(Environment.getExternalStorageDirectory().toString() + "/Pdf to Word/"+fileList.get(position)));
                String abs= new File(Environment.getExternalStorageDirectory().toString() + "/Pdf to Word/"+fileList.get(position)).getAbsolutePath();

                editIntent.putExtra("u",abs);
        try {
            activity.startActivity(editIntent);
        }catch (Exception e){
            Toast.makeText(activity, "Your phone does not support this action.", Toast.LENGTH_SHORT).show();
        }


            }
        });




    }
    public void clear() {
        fileList.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
               private LinearLayout lin;
               private TextView tv_name;
               private CardView cv_edit;
               private CardView cv;
        public MyViewHolder(View itemView) {
            super(itemView);
            lin=itemView.findViewById(R.id.lin);
            cv=itemView.findViewById(R.id.o);
        tv_name=itemView.findViewById(R.id.tv_name);
            cv_edit=itemView.findViewById(R.id.e);
        }
    }






}

//
