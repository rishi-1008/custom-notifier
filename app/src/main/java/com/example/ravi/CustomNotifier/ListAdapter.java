package com.example.ravi.CustomNotifier;

import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private ArrayList<AppsList> itemsList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final AppsList item = itemsList.get(position);
        holder.appName.setText(item.getAppName());
        holder.appIcon.setImageURI(item.getAppIcon());
        holder.appVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                appsListAdapter.selectedApps.get(item.getAppName()).setAppVolume(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
    @Override
    public int getItemCount() {
        return itemsList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView appName;
        private ImageView appIcon;
        private SeekBar appVolume;
        public MyViewHolder(View view){
            super(view);
            appName = view.findViewById(R.id.txt);
            appIcon = view.findViewById(R.id.img);
            appVolume = view.findViewById(R.id.seekBar2);
        }
    }
    public ListAdapter(ArrayList<AppsList> itemsList,AudioManager manager){
        this.itemsList = itemsList;
    }
}
