package com.iqbaaaaalf.hotspotvisualizerfix.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iqbaaaaalf.hotspotvisualizerfix.R;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.OneSeqType;
import com.iqbaaaaalf.hotspotvisualizerfix.util.SequenceAdapter;

import java.util.ArrayList;

public class SequenceActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager sequenceLayoutManager;
//    private RecyclerView.Adapter sequenceAdapter;
    private RecyclerView sequenceRv;
    private ArrayList<String> seqPerLine = new ArrayList<String>();
    private ArrayList<String> emptyList = new ArrayList<String>();
    private SequenceAdapter sequenceAdapter;
    private Context context = this;
    private ArrayList<OneSeqType> allSeq = new ArrayList<OneSeqType>();
    private String fileInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);


        Intent i = getIntent();
        seqPerLine = i.getStringArrayListExtra("allSeqPerLine");
        allSeq = i.getParcelableArrayListExtra("allSeq");
        fileInput = i.getStringExtra("checkFile");

        sequenceRv = (RecyclerView)findViewById(R.id.rvSequenceList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        sequenceRv.setHasFixedSize(true);

        sequenceLayoutManager = new LinearLayoutManager(this);
        sequenceRv.setLayoutManager(sequenceLayoutManager);

        sequenceAdapter = new SequenceAdapter(context, seqPerLine, allSeq);

        //passing variable alamat file csv
        //untuk kepentingan lookup visualisasi
        sequenceAdapter.setCheckFile(fileInput);

        sequenceRv.setAdapter(sequenceAdapter);


    }

}
