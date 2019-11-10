package com.wolfsoft.kcab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.wolfsoft.kcab.rota.DataRouteParser;
import com.wolfsoft.kcab.rota.DrawRouteMaps;
import com.wolfsoft.kcab.rota.FetchUrl;

import java.util.ArrayList;

import model.InRideModel;

public class InRide extends Fragment {
    TextView distance, time;


    public static String distanceUrl, timeUrl;



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
