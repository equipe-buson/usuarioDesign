package com.wolfsoft.kcab.calculaRota;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class DrawRouteMaps {

    private static DrawRouteMaps instance;
    public Context context;

    public static DrawRouteMaps getInstance(Context context) {
        instance = new DrawRouteMaps();
        instance.context = context;
        return instance;
    }

    public DrawRouteMaps draw(LatLng origin, LatLng destination, GoogleMap googleMap, String waipoints){
        String url_route = FetchUrl.getUrl(origin, destination, waipoints);
        DrawRoute drawRoute = new DrawRoute(googleMap);
        drawRoute.execute(url_route);
        Log.d("TAG",url_route);
        return instance;
    }

    public static Context getContext() {
        return instance.context;
    }
}
