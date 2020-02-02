package com.example.ravi.CustomNotifier;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

public class listAppsActivity extends AppCompatActivity {
PackageManager packageManager;
ArrayList<AppsList> appsList;
appsListAdapter adapter;
FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_list);
        final ListView lv = findViewById(R.id.appsList);
        fab = findViewById(R.id.fab_ok);
        appsList = getInstalledApps();
        adapter = new appsListAdapter(this,R.layout.app_list_layout,appsList);
        lv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getSelectedApps();
                Intent i = new Intent(listAppsActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
    public ArrayList<AppsList> getInstalledApps() {
        ArrayList<AppsList> installedApps = new ArrayList<>();

        packageManager = getPackageManager();
        for(ApplicationInfo app:packageManager.getInstalledApplications(0)){
            if(!isSystemApp(app))
                installedApps.add(new AppsList(app.loadLabel(getPackageManager()).toString(), Uri.parse("android.resource://" + app.packageName + "/" + app.icon)));
        }
        return installedApps;
    }
    private boolean isSystemApp(ApplicationInfo app) {
        return ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }
}
