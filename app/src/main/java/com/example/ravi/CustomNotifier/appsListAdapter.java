package com.example.ravi.CustomNotifier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class appsListAdapter extends ArrayAdapter<AppsList> {
    private Context context;
    int resource;
    private ArrayList<AppsList> appsList;
    public static HashMap<String,AppsList> selectedApps = new HashMap<>();

    public appsListAdapter(@NonNull Context context, int resource, ArrayList<AppsList> appsList) {
        super(context, resource,appsList);
        this.appsList = appsList;
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final AppsList app = appsList.get(position);
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.app_list_layout, null);
        }
            TextView tv = view.findViewById(R.id.appName);
            ImageView iv = view.findViewById(R.id.appIcon);
            CheckBox cb = view.findViewById(R.id.checkbox);
            tv.setText(app.getAppName());
            iv.setImageURI(app.getAppIcon());
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(app.getItemClicked())
                        app.setItemState(false);
                    else
                        app.setItemState(true);
                }
            });
            cb.setChecked(app.getItemClicked());
            if(selectedApps.containsKey(app.getAppName())){
                app.setItemState(true);
                cb.setChecked(true);
            }
            return view;
        }
    public HashMap<String,AppsList> getSelectedApps() {
        selectedApps.clear();
        for(AppsList app:appsList){
            if(app.getItemClicked() && !selectedApps.containsKey(app.getAppName()))
                selectedApps.put(app.getAppName(),app);
        }
        return selectedApps;
    }
}
