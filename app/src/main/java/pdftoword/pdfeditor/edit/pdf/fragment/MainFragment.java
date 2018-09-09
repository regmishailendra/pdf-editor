package pdftoword.pdfeditor.edit.pdf.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import dk.nodes.filepicker.FilePickerActivity;
import dk.nodes.filepicker.FilePickerConstants;
import dk.nodes.filepicker.uriHelper.FilePickerUriHelper;
import pdftoword.pdfeditor.edit.pdf.BaseFragment;
import pdftoword.pdfeditor.edit.pdf.R;
import pdftoword.pdfeditor.edit.pdf.activity.MainActivity;
import pdftoword.pdfeditor.edit.pdf.adapter.RecyclerViewDownloadedAdapter;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static dk.nodes.filepicker.FilePickerConstants.RESULT_CODE_FAILURE;

/**
 * Created by shailendra on 7/25/18.
 */

public class MainFragment extends BaseFragment {

    Button pickFile;
    RecyclerView rv;
    private RecyclerViewDownloadedAdapter adapter;
    private List<String> fileList = new ArrayList<>();

    Button house;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        pickFile = v.findViewById(R.id.pick);
        rv = v.findViewById(R.id.rv);

        pickFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FilePickerActivity.class);
                intent.putExtra(FilePickerConstants.FILE, true);
                intent.putExtra(FilePickerConstants.TYPE, FilePickerConstants.MIME_PDF);

                startActivityForResult(intent, 22);
            }
        });


        populateRecyclerView();


        return v;


    }

    private void populateRecyclerView() {
        rv.setAdapter(null);
        listFiles();
        adapter = new RecyclerViewDownloadedAdapter(fileList, getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //build create call enqueue


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Converting to text please wait...");
        dialog.setCancelable(false);
        dialog.show();

        if (requestCode == 22) {
            if (resultCode == RESULT_OK) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        File root = new File(Environment.getExternalStorageDirectory(), "Pdf to Word");
                        if (!root.exists()) {
                            root.mkdirs();
                        }
                        File gpxfile = new File(root, getFileNameFromUri(FilePickerUriHelper.getUriString(data)) + "_" + System.currentTimeMillis() + ".txt");
                        try {
                            FileWriter writer = new FileWriter(gpxfile);
                            String parsedText = "";
                            PdfReader reader = new PdfReader(FilePickerUriHelper.getUriString(data));
                            int n = reader.getNumberOfPages();
                            for (int i = 0; i < n; i++) {
                                parsedText = PdfTextExtractor.getTextFromPage(reader, i + 1).trim() + "\n"; //Extracting the content from the different pages

                                writer.append(parsedText);
                                writer.flush();
                            }

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();

                                    populateRecyclerView();
                                }
                            });


                            writer.close();
                            reader.close();


                        } catch (Exception e) {
                            System.out.println(e);
                        }

                    }
                }).start();


            } else if (resultCode == RESULT_CANCELED) {
                dialog.dismiss();
            } else if (resultCode == RESULT_CODE_FAILURE) {
                dialog.dismiss();
            }
        } else {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Some Error", Toast.LENGTH_SHORT).show();

        }
    }

    private String getFileNameFromUri(String uriString) {
        String fileName = uriString.substring(uriString.lastIndexOf("/") + 1);
        return fileName.replace(".pdf", "");
    }

    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
        }
    }

    private void listFiles() {
        fileList.clear();
        String path = Environment.getExternalStorageDirectory().toString() + "/Pdf to Word";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
//        Log.d("Files", "Size: "+ files.length);

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                fileList.add(files[i].getName());
            }
        }
    }


    public static boolean isFirstTime = true;
    public static boolean isAdShown = false;


    public static int adCount=0;
    @Override
    public void onResume() {
        super.onResume();
        Log.v("fragmentcheck", "on resume");


        if (!isFirstTime) {

            if (adCount % 2 == 0) {
                ((MainActivity) getActivity()).showInterAd();

            }


        }



        else {

            if (!getActivity().getIntent().getBooleanExtra("a", true)) {
                ((MainActivity) getActivity()).showInterAd();
            }

        }

        isFirstTime = false;
        adCount++;
    }

    @Override
    public void onPause() {
        super.onPause();
//        isAdShown=false
    }
}













