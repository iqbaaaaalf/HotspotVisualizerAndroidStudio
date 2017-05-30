package com.iqbaaaaalf.hotspotvisualizerfix.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iqbaaaaalf.hotspotvisualizerfix.R;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.OneSeqType;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.Point;
import com.iqbaaaaalf.hotspotvisualizerfix.module.Visualisasi;
import com.iqbaaaaalf.hotspotvisualizerfix.view.MapsActivity;

import java.util.ArrayList;

/**
 * Created by iqbaaaaalf on 5/23/2017.
 */

public class SequenceAdapter extends RecyclerView.Adapter<SequenceAdapter.ViewHolder>{

    private ArrayList<String> allSeqPerLine =  new ArrayList<String>();
    private Context context;
    private ArrayList<OneSeqType> allSeq = new ArrayList<OneSeqType>();
    private Visualisasi visualisasi = new Visualisasi();
    private String CheckFile;

    public void setCheckFile(String checkFile) {
        CheckFile = checkFile;
    }

    public SequenceAdapter(Context context, ArrayList<String> allSeqPerLine, ArrayList<OneSeqType> allSeq ){
        this.allSeqPerLine = allSeqPerLine;
        this.context = context;
        this.allSeq = allSeq;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sequenceView;
        public CardView cardView;


        public ViewHolder(final View v) {
            super(v);
            sequenceView = (TextView) v.findViewById(R.id.tvSequenceList);
            cardView = (CardView) v.findViewById(R.id.card_view);

        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public SequenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_sequence, parent, false);
        ViewHolder vh = new ViewHolder(view);

        //memasukan list dari OneSeqType
        // kedalam class visualisasi

        visualisasi.setAllSeq(allSeq);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        visualisasi.setSelectedCardPosition(position);

        holder.sequenceView.setText(allSeqPerLine.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visualisasi.setSelectedCardPosition(position);
                visualisasi.run(CheckFile);

                ArrayList<Point> ll;
                ll = visualisasi.getCommon();

                System.out.println("----- TEST hasil keluaran titik pada freq sequence -------");
                for (Point po : ll){
                    System.out.print("Long : " + po.getLongitude() + " , ");
                    System.out.println("Lat : " + po.getLatitude());
                }

                Intent mapIntent = new Intent(context, MapsActivity.class);
                mapIntent.putExtra("geoJson", visualisasi.getGeoJson().toString());
                context.startActivity(mapIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allSeq.size();
    }
    }
