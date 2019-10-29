package com.wolfsoft.kcab.rota;

import android.content.Context;
import android.util.Log;

import com.ahmadrosid.lib.drawroutemap.DrawRoute;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class DrawRouteMaps {

    private static com.wolfsoft.kcab.rota.DrawRouteMaps instance;
    public Context context;

    public static com.wolfsoft.kcab.rota.DrawRouteMaps getInstance(Context context) {
        instance = new com.wolfsoft.kcab.rota.DrawRouteMaps();
        instance.context = context;
        return instance;
    }

    public com.wolfsoft.kcab.rota.DrawRouteMaps draw(LatLng origin, LatLng destination, GoogleMap googleMap, String waipoints){
        String url_route = FetchUrl.getUrl(origin, destination, waipoints);
        com.wolfsoft.kcab.rota.DrawRoute drawRoute = new com.wolfsoft.kcab.rota.DrawRoute(googleMap);
        drawRoute.execute(url_route);
        Log.d("TAG",url_route);
        return instance;
    }

    public static Context getContext() {
        return instance.context;
    }
}
