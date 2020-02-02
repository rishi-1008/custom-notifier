package com.example.ravi.CustomNotifier;

import android.content.Intent;
import android.media.AudioManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<AppsList> itemList;
    TextView tv;
    RecyclerView Rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab_add);
        tv = findViewById(R.id.textview);
        Rc = findViewById(R.id.listview);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, listAppsActivity.class));

            }
        });
        itemList = new ArrayList<>();
        try {
            for (String key : appsListAdapter.selectedApps.keySet()) {
                itemList.add(appsListAdapter.selectedApps.get(key));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
       if (itemList.isEmpty()) {
            tv.setVisibility(View.VISIBLE);
            Rc.setVisibility(View.GONE);
        } else {
            Rc.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
        }
        ListAdapter adapter = new ListAdapter(itemList,(AudioManager)getSystemService(AUDIO_SERVICE));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        Rc.setLayoutManager(mLayoutManager);
        Rc.setItemAnimator(new DefaultItemAnimator());
        Rc.setAdapter(adapter);
    }
}
