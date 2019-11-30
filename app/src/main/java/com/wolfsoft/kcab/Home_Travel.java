package com.wolfsoft.kcab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wolfsoft.kcab.rota.DrawMarker;
import com.wolfsoft.kcab.rota.DrawRouteMaps;

import java.util.ArrayList;

import adapter.InfoWindowBusStopAdapter;
import adapter.RidehistoryAdapter;
import model.PontosModel;

import static adapter.RidehistoryAdapter.rota;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.*;
import static com.wolfsoft.kcab.rota.DrawRouteMaps.getContext;

public class Home_Travel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {


    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    private GoogleMap mMap;

    double latitudeUser;
    double longitudeUser;
    public LatLng latLngUser;


    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    DatabaseReference refPonto;
    DatabaseReference mRef;

    Marker marcadorPonto;
    public ArrayList<PontosModel> pontosModelArrayList;
    public RidehistoryAdapter ridehistoryAdapter = new RidehistoryAdapter();



    Marker markerOnibus;
    LatLng latLngOnibus;

    public void setRefPonto(String referencia) {
        refPonto = mRef.child(referencia);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_travel);

        pontosModelArrayList = new ArrayList<>();

        Button button = findViewById(R.id.btnRota);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Travel.this, Ride_History_iCab.class);
                Home_Travel.this.startActivity(intent);
            }
        });


        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
                // execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        GPSTracker gps = new GPSTracker(Home_Travel.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitudeUser = gps.getLatitude();
            longitudeUser = gps.getLongitude();

        }else{
            gps.showSettingsAlert();
        }


    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.layout, fragment, tag);
        ft.commitAllowingStateLoss();
    }




    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        latLngUser = new LatLng(latitudeUser,longitudeUser);
        selecionaRota();

        // cria marcador do usuario
        final MarkerOptions marker = new MarkerOptions().position(latLngUser).title("Posição Atual");
        marker.icon(fromResource(R.drawable.pin_black));
        // add marker
        googleMap.addMarker(marker);


        CameraPosition cameraPosition = new CameraPosition.Builder().target(
               latLngUser).zoom(16).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        MarkerOptions markerOptions= new MarkerOptions().title("").position(new LatLng(0,0)).icon(defaultMarker());
        markerOnibus = mMap.addMarker(markerOptions);
        refPonto = mRef.child("motorista");
        refPonto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                markerOnibus.remove();
                MarkerOptions markerOptions= new MarkerOptions().title("").position(new LatLng(0,0)).icon(defaultMarker());
                markerOnibus = mMap.addMarker(markerOptions);

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    final String nomeOnibus = objSnapshot.child("nomeMotorista").getValue().toString();
                    final Double latitudeOnibus = (Double) objSnapshot.child("latitude").getValue();
                    final Double longitudeOnibus = (Double) objSnapshot.child("longitude").getValue();
                    latLngOnibus = new LatLng(latitudeOnibus,longitudeOnibus);
                    markerOptions.position(latLngOnibus).title(nomeOnibus);
                    markerOnibus = mMap.addMarker(markerOptions);
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });



        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setToolbar();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        invalidateOptionsMenu();


        final InfoWindowBusStopAdapter markerInfoWindowAdapter = new InfoWindowBusStopAdapter(getApplicationContext());
        mMap.setInfoWindowAdapter(markerInfoWindowAdapter);
        mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String waipoints = comparaLatLng(marker.getPosition());
                com.wolfsoft.kcab.calculaRota.DrawRouteMaps.getInstance(getContext()).draw(marker.getPosition(), latLngOnibus, mMap,waipoints);

//                markerInfoWindowAdapter.getInfoContents(marker);
                marker.showInfoWindow();
                LatLng posicaoMarcador = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
                CameraPosition cameraPositionMarcador = new CameraPosition.Builder().target(
                        posicaoMarcador).zoom(16).build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPositionMarcador));


                return true;
            }
        }
        );
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                mMap.clear();
                String waipoints = comparaLatLng(marker.getPosition());
                DrawRouteMaps.getInstance(getContext()).draw(marker.getPosition(),latLngOnibus,mMap,waipoints);
                addFragment(new InRide(),false,"one");
                marker.getPosition();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(Gravity.LEFT); //OPEN Nav Drawer!
        }else {
            finish();
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("");

        toolbar.findViewById(R.id.navigation_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(navigationView)) {
                    drawer.closeDrawer(navigationView);
                } else {
                    drawer.openDrawer(navigationView);
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }




    public void selecionaRota(){
        FirebaseApp.initializeApp(Home_Travel.this);
        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginfire-23a07.firebaseio.com/");
        String ref = "";
        LatLng origin = new LatLng(0,0);
        LatLng destination = new LatLng(0,0);
        String waipoints = "";
            if (rota == 1) {
                ref = "Blumenau-Ilhota";
                origin = new LatLng(-26.906101, -49.077743);
                destination = new LatLng(-26.910984,-48.86465);
                waipoints = "via:-26.925929,-49.056093|via:-26.900647,-49.002856";

            }
            if (rota == 2) {
                ref = "Ilhota-Blumenau";
                origin = new LatLng(-26.910706, -48.864301);
                destination = new LatLng(-26.904585,-49.077374);
                waipoints = "via:-26.930839,-48.934213|via:-26.900647,-49.002848";
            }
            if (rota == 3) {
                ref = "Blumenau-Gaspar";
                origin = new LatLng(-26.906101, -49.077743);
                destination = new LatLng(-26.947771,-48.931056);
                waipoints = "via:-26.925929,-49.056093|via:-26.900647,-49.002456";
            }
            if (rota == 4) {
                ref = "Gaspar-Blumenau";
                origin = new LatLng(-26.955587,-48.926634);
                destination = new LatLng(-26.904585,-49.077374);
                waipoints = "via:-26.900652,-49.002985";
            }
            desenhaRota(origin,destination,waipoints);

        refPonto = mRef.child(ref);
        refPonto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    final String enderecoPonto = objSnapshot.child("endereco").getValue().toString();
                    final Double latitudePonto = (Double) objSnapshot.child("latitude").getValue();
                    final Double longitudePonto = (Double) objSnapshot.child("longitude").getValue();

                    pontosModelArrayList.add(new PontosModel(enderecoPonto, latitudePonto, longitudePonto));
                    marcadorPonto =mMap.addMarker(new MarkerOptions().position(new LatLng(latitudePonto, longitudePonto)).title(enderecoPonto).icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder)));
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }

        });
    }
    public void desenhaRota(LatLng origin, LatLng destination, String waipoints){
        DrawRouteMaps.getInstance(this).draw(origin, destination, mMap, waipoints);
        DrawMarker.getInstance(this).draw(mMap, origin, R.drawable.marker_a, "Origin Location");
        DrawMarker.getInstance(this).draw(mMap, destination, R.drawable.marker_b, "Destination Location");

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(origin)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 250, 30));
    }

    public String comparaLatLng(LatLng position){
        String waipoints = "via:";
        int i;
        if (rota == 1){
            i = 0;
            if (position.longitude <= -49.055093) {
                waipoints += "-26.925929,-49.056093|via:-26.900647,-49.002856";
                i=1;
                Log.d("tag", "completo");
            }
            if (position.longitude <= -49.002456){
                if (i!=1) {
                    waipoints += "|via:-26.900647,-49.002856";
                    Log.d("tag", "Bela");
                }
            }
        }
        if (rota == 2){
            i = 0;
            if (position.longitude <= -48.931999) {
                waipoints += "-26.930839,-48.934213|via:-26.900647,-49.002456";
                i=1;
                Log.d("tag", "completo");
            }
            if (position.longitude <= -49.002456){
                if (i!=1) {
                    waipoints += "|via:-26.900647,-49.002848";
                    Log.d("tag", "Bela");
                }
            }
        }
        if (rota == 3){
            i = 0;
            if (position.longitude <= -49.055093) {
                waipoints += "-26.925929,-49.056093|via:-26.900647,-49.002534";
                i=1;
                Log.d("tag", "completo");
            }
            if (position.longitude <= -49.002534){
                if (i!=1) {
                    waipoints += "|via:-26.900647,-49.002456";
                    Log.d("tag", "Bela");
                }
            }
        }
        if (rota == 4){
            i = 0;
            if (position.longitude <= -49.055093) {
                waipoints += "-26.900652,-49.002985";
                Log.d("tag", "completo");
            }else {
                Log.d("tag", "parcial");
            }
        }
        return waipoints;

    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("TAG","info clicado");
    }
}

