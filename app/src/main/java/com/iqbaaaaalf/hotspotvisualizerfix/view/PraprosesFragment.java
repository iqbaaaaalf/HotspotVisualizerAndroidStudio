package com.iqbaaaaalf.hotspotvisualizerfix.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iqbaaaaalf.hotspotvisualizerfix.R;
import com.iqbaaaaalf.hotspotvisualizerfix.module.Praproses;
import com.iqbaaaaalf.hotspotvisualizerfix.util.DirectoryList;
import com.iqbaaaaalf.hotspotvisualizerfix.util.RunSpade;
import com.iqbaaaaalf.hotspotvisualizerfix.util.RunTime;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * A simple {@link Fragment} subclass.
 */
public class PraprosesFragment extends Fragment {

    private static final int READ_BLOCK_SIZE = 100;
    EditText input;
    Praproses pra = new Praproses();
    RunTime time = new RunTime();
    RunSpade spade = new RunSpade();
    DirectoryList dir = new DirectoryList();

    String PathInput = dir.getPathInput();
    String PathOutputCsv= dir.getPathOutputCsv();
    String PathOutputSeq= dir.getPathOutputSeq();
    String NamaFile = null;
    View view;
    File fileChecker;

    public PraprosesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_praproses, container, false);
        Button btnTransform = (Button)view.findViewById(R.id.btnTransformData);
        btnTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransformBtn();
            }
        });

        Button btnSequential = (Button)view.findViewById(R.id.btnSequential);
        btnSequential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SequentialBtn();
            }
        });

        return view;
    }

    public void TransformBtn(){
        time.start();
        input = (EditText)view.findViewById(R.id.inptFile);
        NamaFile = input.getText().toString();
        String InputFile = PathInput + NamaFile + ".csv";
        String OutputFile =  PathOutputCsv + NamaFile + ".csv";

        fileChecker = new File(InputFile);
        if(!fileChecker.exists()){
            Toast.makeText(getActivity().getBaseContext(), "Gagal melakukan penyesuaian data, File tidak ditemukan!",
                    Toast.LENGTH_LONG).show();
        }else{
            try {
                pra.penyesuaianData(InputFile, OutputFile);
                Toast.makeText(getActivity().getBaseContext(), "Eksekusi dalam "+time.end()+" millsec Berhasil! Data tersimpan di "+OutputFile,
                        Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                Toast.makeText(getActivity().getBaseContext(), "Gagal melakukan penyesuaian data, File tidak ditemukan!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void SequentialBtn(){
        time.start();
        input = (EditText)view.findViewById(R.id.inptFile);
        NamaFile = input.getText().toString();
        String InputFile = PathOutputCsv + NamaFile + ".csv";
        String OutputFileTxt = PathOutputSeq + "Seq-" + NamaFile + ".txt";
        String OutputFileCsv = PathOutputSeq + "Seq-" + NamaFile + ".csv";

        fileChecker = new File(InputFile);
        if(!fileChecker.exists()){
            Toast.makeText(getActivity().getBaseContext(), "Gagal melakukan perubahan data menjadi sekuensial, silakan menjalankan transformasi data terlebih dahulu",
                    Toast.LENGTH_LONG).show();
        } else{
            try {
                pra.convert2Sequential(InputFile, OutputFileTxt, OutputFileCsv );
                Toast.makeText(getActivity().getBaseContext(), "Eksekusi dalam "+time.end()+" millsec Berhasil! Data tersimpan di "+ OutputFileTxt,
                        Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                Toast.makeText(getActivity().getBaseContext(), "Gagal melakukan perubahan data Sequential, File tidak ditemukan!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
