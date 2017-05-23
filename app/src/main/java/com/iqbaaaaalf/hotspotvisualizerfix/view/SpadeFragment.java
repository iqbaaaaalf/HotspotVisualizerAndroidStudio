package com.iqbaaaaalf.hotspotvisualizerfix.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iqbaaaaalf.hotspotvisualizerfix.MainActivity;
import com.iqbaaaaalf.hotspotvisualizerfix.R;
import com.iqbaaaaalf.hotspotvisualizerfix.module.Spade;
import com.iqbaaaaalf.hotspotvisualizerfix.module.Visualisasi;
import com.iqbaaaaalf.hotspotvisualizerfix.util.DirectoryList;
import com.iqbaaaaalf.hotspotvisualizerfix.util.RunTime;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpadeFragment extends Fragment {

    EditText input;
    TextView outputReadableEditText;
    View view;
    RunTime time = new RunTime();
    Visualisasi visualisasi= new Visualisasi();
    Spade spade = new Spade();
    DirectoryList dir = new DirectoryList();

    String PathInput = dir.getPathInputSpade();
    String PathOutputSpade= dir.getPathOutputSpade();
    String PathOutputTemp= dir.getPathOutputTemp();
    String NamaFile = null;

    EditText editTextSupport;

    public SpadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_spade, container, false);
        // Inflate the layout for this view

        Button spadeBtn = (Button)view.findViewById(R.id.btnSpade);
        spadeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SpadeBtn();
                    Intent intent = new Intent(getActivity(), SequenceActivity.class);
                    intent.putStringArrayListExtra("allSeq",spade.getOutputReadablePerLine());
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getBaseContext(), "Gagal melakukan analisis Spade, File tidak ditemukan!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    public void SpadeBtn() throws IOException {
        time.start();
//        spinner = (Spinner)view.findViewById(R.id.spinnerSpade);
        input = (EditText)view.findViewById(R.id.inptFileSpade);
        editTextSupport = (EditText)view.findViewById(R.id.inptSupport);
        outputReadableEditText = (TextView) view.findViewById(R.id.editTextReadableContainer);
        String support = null;
        double supportValue = 0.0;
        outputReadableEditText.setText("");

        if(TextUtils.isEmpty(input.getText()) || TextUtils.isEmpty(editTextSupport.getText())){
            Toast.makeText(getActivity().getBaseContext(), "Nilai yang dimasukan tidak boleh kosong",
                    Toast.LENGTH_LONG).show();
        }else if(Double.parseDouble(editTextSupport.getText().toString().trim()) >= 100 || Double.parseDouble(editTextSupport.getText().toString().trim()) < 0){
            Toast.makeText(getActivity().getBaseContext(), "Nilai support tidak boleh lebih dari 100",
                    Toast.LENGTH_LONG).show();
        }else{
            NamaFile = input.getText().toString();
            support = editTextSupport.getText().toString().trim();
            supportValue = Double.parseDouble(support);

            String InputFile = PathInput + "Seq-" +NamaFile + ".txt";
            String OutputFile = PathOutputSpade + "Spade-" + NamaFile + ".txt";
            String OutputFileTemp = PathOutputTemp + "Spade-" + NamaFile + ".txt";

            spade.run(InputFile, OutputFile, OutputFileTemp ,supportValue);
            outputReadableEditText.setText(spade.getOutputReadable());
            visualisasi.setAllSeq(spade.getAllSeq());

            Toast.makeText(getActivity().getBaseContext(), "Eksekusi dalam "+time.end()+" millsec Berhasil! Data tersimpan di "+ OutputFile,
                    Toast.LENGTH_LONG).show();

        }
    }

}
