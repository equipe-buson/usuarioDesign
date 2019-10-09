package com.wolfsoft.kcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import model.RidehistoryModel;

public class Ride_History_iCab extends AppCompatActivity {

    private RidehistoryAdapter ridehistoryAdapter;
    private RecyclerView recyclerview;
    private ArrayList<RidehistoryModel> ridehistoryModelArrayList;

    Integer i1[]={R.drawable.pin_black,R.drawable.pin_black,R.drawable.pin_black,R.drawable.pin_black,R.drawable.pin_black};
    Integer i2[]={R.drawable.rect_dotted,R.drawable.rect_dotted,R.drawable.rect_dotted,R.drawable.rect_dotted,R.drawable.rect_dotted};
    Integer i3[]={R.drawable.navigatiob_blue,R.drawable.navigatiob_blue,R.drawable.navigatiob_blue,R.drawable.navigatiob_blue,R.drawable.navigatiob_blue};
//    String txtmall[]={"Ponto Rua São Paulo - FURB","Ponto SESI","Ponto IFSC","Ponto Sagrada Família","Ponto Ponte dos Arcos"};
//    String txthome[]={"Escola","Casa","Trabalho","Escola","Casa"};
//    String txtdate[]={"01 Maio 2018","10 Junho 2019","25 Julho 2019","21 Agosto 2018","30 Janeiro 2018"};
//    String txtprice[]={"R$1,50","R$1,50","R$4,30","$2.94","$2.94"};
        String txtmall[];
   String txthome[];
   String txtdate[];
   String txtprice[];

    FirebaseDatabase firebaseDatabase;
    DatabaseReference refPonto;
    DatabaseReference mRef;

    final int i = 0;

    private void incializarFireBase() {

        FirebaseApp.initializeApp(Ride_History_iCab.this);
        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginfire-23a07.firebaseio.com/");
        refPonto =mRef.child("Pontos");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride__history_i_cab);

        incializarFireBase();
        recyclerview=findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Ride_History_iCab.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());



        refPonto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    final String endereco = objSnapshot.child("endereco").getValue().toString();
                    final String latitude = objSnapshot.child("latitude").getValue().toString();
                    final String longitude = objSnapshot.child("longitude").getValue().toString();
                    Log.d("TAG", "Value is " + endereco);
                    Log.d("TAG", "Value é " + latitude);
                    Log.d("TAG", "Value é " + longitude);

                   txtmall[i] = endereco;
                   txthome[i] = latitude;
                   txtprice[i] = longitude;
                   txtdate[i] = "01/01/2019";

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });



        ridehistoryModelArrayList = new ArrayList<>();

        for (int i=0;i<i1.length;i++){

            RidehistoryModel listModel = new RidehistoryModel(i1[i],i2[i],i3[i],txtmall[i],txthome[i],txtdate[i],txtprice[i]);

            ridehistoryModelArrayList.add(listModel);

        }
        ridehistoryAdapter = new RidehistoryAdapter(Ride_History_iCab.this,ridehistoryModelArrayList);
        recyclerview.setAdapter(ridehistoryAdapter);
    }
}
