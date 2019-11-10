package com.wolfsoft.kcab;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.RidehistoryAdapter;
import model.PontosModel;
import model.RidehistoryModel;

public class  Ride_History_iCab extends AppCompatActivity {

    private RidehistoryAdapter ridehistoryAdapter;
    private RecyclerView recyclerview;
    public ArrayList<RidehistoryModel> ridehistoryModelArrayList;



    Integer i1[]={R.drawable.pin_black,R.drawable.pin_black,R.drawable.pin_black,R.drawable.pin_black,R.drawable.pin_black};
    Integer i2[]={R.drawable.rect_dotted,R.drawable.rect_dotted,R.drawable.rect_dotted,R.drawable.rect_dotted,R.drawable.rect_dotted};
    Integer i3[]={R.drawable.navigatiob_blue,R.drawable.navigatiob_blue,R.drawable.navigatiob_blue,R.drawable.navigatiob_blue,R.drawable.navigatiob_blue};
//    String txtmall[]={"Ponto Rua São Paulo - FURB","Ponto SESI","Ponto IFSC","Ponto Sagrada Família","Ponto Ponte dos Arcos"};
//    String txthome[]={"Escola","Casa","Trabalho","Escola","Casa"};
//    String txtdate[]={"01 Maio 2018","10 Junho 2019","25 Julho 2019","21 Agosto 2018","30 Janeiro 2018"};
//    String txtprice[]={"R$1,50","R$1,50","R$4,30","$2.94","$2.94"};



    DatabaseReference refPonto;
    DatabaseReference mRef;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;




    private void incializarFireBase() {

        FirebaseApp.initializeApp(Ride_History_iCab.this);
        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginfire-23a07.firebaseio.com/");
        refPonto =mRef.child("Linhas");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride__history_i_cab);



        ridehistoryModelArrayList = new ArrayList<>();

        incializarFireBase();

        recyclerview = findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Ride_History_iCab.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

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


        refPonto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    final String loc = objSnapshot.child("nome").getValue().toString();
                    final String valor = objSnapshot.child("valor").getValue().toString();
                    RidehistoryModel listModel = new RidehistoryModel(i1[1],i2[1],i3[1],loc,valor);
                    ridehistoryModelArrayList.add(listModel);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        };
        ridehistoryAdapter = new RidehistoryAdapter(Ride_History_iCab.this, ridehistoryModelArrayList, listener);
        recyclerview.setAdapter(ridehistoryAdapter);

    }





}