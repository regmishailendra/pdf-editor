package pdftoword.pdfeditor.edit.pdf.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pdftoword.pdfeditor.edit.pdf.BaseFragment;
import pdftoword.pdfeditor.edit.pdf.R;
import pdftoword.pdfeditor.edit.pdf.adapter.RecyclerViewDownloadedAdapter;

/**
 * Created by shailendra on 7/26/18.
 */

public class DownloadedFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerViewDownloadedAdapter adapter;
   private List<String> fileList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_downloaded,container,false);
        recyclerView = v.findViewById(R.id.rv_pregnancy_symptoms);
        requestStoragePermission();
        fileList=new ArrayList<>();
try {
    listFiles();
}catch (Exception e){e.printStackTrace();}
        super.loadAd(v);

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RecyclerViewDownloadedAdapter(fileList, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




    }
    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
        }
    }
    private void listFiles() {
        String path = Environment.getExternalStorageDirectory().toString()+"/Pdf to Word";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
//        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            fileList.add(files[i].getName());
        }
    }


    @Override
    public void onResume() {
        super.onResume();



    }
}

