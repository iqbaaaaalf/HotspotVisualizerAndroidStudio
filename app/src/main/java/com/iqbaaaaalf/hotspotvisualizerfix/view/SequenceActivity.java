package com.iqbaaaaalf.hotspotvisualizerfix.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iqbaaaaalf.hotspotvisualizerfix.R;
import com.iqbaaaaalf.hotspotvisualizerfix.util.SequenceAdapter;

import java.util.ArrayList;

public class SequenceActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager sequenceLayoutManager;
    private RecyclerView.Adapter sequenceAdapter;
    private RecyclerView sequenceRv;
    private ArrayList<String> seqPerLine = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);

        Intent i = getIntent();
        seqPerLine = i.getStringArrayListExtra("allSeq");
        seqPerLine = getIntent().getStringArrayListExtra("allSeq");

        //Testing

        System.out.println("Lokasi Class : " + this.getClass().getName().toLowerCase());
        System.out.println("Jumlah Seq hasil passing ke sequenceActivity : " + seqPerLine.size());
        for (String line : seqPerLine){
            System.out.println(line);
        }

        //Testing


        sequenceRv = (RecyclerView)findViewById(R.id.rvSequenceList);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        sequenceRv.setHasFixedSize(true);

        sequenceLayoutManager = new LinearLayoutManager(this);
        sequenceRv.setLayoutManager(sequenceLayoutManager);

        sequenceAdapter = new SequenceAdapter(seqPerLine);
        sequenceRv.setAdapter(sequenceAdapter);

    }


}
