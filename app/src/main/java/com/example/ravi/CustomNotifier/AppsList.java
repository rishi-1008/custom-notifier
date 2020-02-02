package com.example.ravi.CustomNotifier;

import android.net.Uri;
import android.os.Parcel;

class AppsList {

    private String appName;
    private Uri appIcon;
    private boolean isItemClicked;
    private int appVolume;

    public AppsList(String appName,Uri appIcon){
        this.appName = appName;
        this.appIcon = appIcon;
        this.isItemClicked=false;
    }
    public AppsList(Parcel in){
        appName = in.readString();
        appIcon =  in.readParcelable(getClass().getClassLoader());
    }
    public String getAppName() {
        return appName;
    }
    public Uri getAppIcon() {
        return appIcon;
    }
    public boolean getItemClicked(){
        return isItemClicked;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public void setItemState(boolean value) {
        isItemClicked = value;
    }
    public int getAppVolume() {
        return appVolume;
    }
    public void setAppVolume(int appVolume) {
        this.appVolume = appVolume;
    }
}
