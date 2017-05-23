package com.iqbaaaaalf.hotspotvisualizerfix.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iqbaaaaalf.hotspotvisualizerfix.R;

import java.util.ArrayList;
import java.util.List;

import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.Sequence;

/**
 * Created by iqbaaaaalf on 5/23/2017.
 */

public class SequenceAdapter extends RecyclerView.Adapter<SequenceAdapter.ViewHolder>{

    private Context context;
    private ArrayList<String> allSeq =  new ArrayList<String>();

    public SequenceAdapter(ArrayList<String> allSeq){
        this.allSeq = allSeq;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView sequenceView;

        public ViewHolder(View v) {
            super(v);
            sequenceView = (TextView) v.findViewById(R.id.tvSequenceList);
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public SequenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_sequence, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.sequenceView.setText(allSeq.get(position));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allSeq.size();
    }
    }
