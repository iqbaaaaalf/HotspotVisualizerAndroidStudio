package com.iqbaaaaalf.hotspotvisualizerfix.view;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iqbaaaaalf.hotspotvisualizerfix.R;
import com.iqbaaaaalf.hotspotvisualizerfix.util.TxtReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualisasiFragment extends Fragment {

    String PathInput = Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/input/";
    String PathOutput= Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/output/";
    String NamaFile = null;
    EditText input;
    View view;


    public VisualisasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_visualisasi, container, false);

        Button btnVisualisasi = (Button)view.findViewById(R.id.btnVisualisasi);
        btnVisualisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisualisasiBtn();
            }
        });


        return view;
    }

    public void VisualisasiBtn(){
        input = (EditText)view.findViewById(R.id.inptFile2);
        NamaFile = input.getText().toString();
        TxtReader tr = new TxtReader() ;
        String input = PathOutput + "Spade-" + NamaFile + ".txt";
        tr.TxtBaca(input);
    }

}
