package com.wolfsoft.kcab.calculaRota;

import com.google.android.gms.maps.model.LatLng;


public class FetchUrl {
    public static String getUrl(LatLng origin, LatLng dest, String waipoints) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String api = "AIzaSyA8QpDOUhWD7dSq5m6A1ox9gsW22Mxz-r0";
        String str_waipoint = waipoints;
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&key=" + api + "&waypoints=" + str_waipoint;
        String output = "json";
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
    }
}
