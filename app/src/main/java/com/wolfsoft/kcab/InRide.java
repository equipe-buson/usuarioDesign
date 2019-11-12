package com.wolfsoft.kcab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.wolfsoft.kcab.calculaRota.DrawRoute;
import com.wolfsoft.kcab.calculaRota.FetchUrl;

public class InRide extends Fragment {
    TextView distance, time;


    public static String distanceUrl;
    public static String timeUrl;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        InRide.url = url;
    }

    public static String url;
    DrawRoute drawRoute;

    public InRide(){
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_in__ride_icab, container, false);
        viewAdapter(view);
        return view;
    }
    public void viewAdapter(View view){
        distance = view.findViewById(R.id.duration);
        time = view.findViewById(R.id.time);
        distance.setText(getDistanceUrl());
        time.setText(getTimeUrl());
        Log.d("tag", "onCreate");
    }

    public static String getDistanceUrl() {
        return distanceUrl;
    }

    public static void setDistanceUrl(String distanceUrl) {
        InRide.distanceUrl = distanceUrl;
    }

    public static String getTimeUrl() {
        return timeUrl;
    }

    public static void setTimeUrl(String timeUrl) {
        InRide.timeUrl = timeUrl;
    }





}
