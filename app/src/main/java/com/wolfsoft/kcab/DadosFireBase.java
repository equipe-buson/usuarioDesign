package com.wolfsoft.kcab;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.PontosModel;

public class DadosFireBase {
    DatabaseReference refChild;
    DatabaseReference mRef;


    public void inicializarFireBase(Context context, String ref) {
        FirebaseApp.initializeApp(context);
        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginfire-23a07.firebaseio.com/");

    }
    public void populaPontos(){
        int i ;
        final ArrayList<String> refss = new ArrayList<>();
        refss.add("Blumenau-Ilhota");
        refss.add("Ilhota-Blumenau");
        refss.add("Blumenau-Gaspar");
        refss.add("Gaspar-Blumenau");
        Log.d("tag", String.valueOf(refss.size()));
        for (i = 0 ; i< refss.size(); i++) {

            Log.d("tag",refss.get(i));
            refChild = mRef.child(refss.get(i));
            final int finalI = i;
            final int finalI1 = i;
            refChild.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                        final String enderecoPonto = objSnapshot.child("endereco").getValue().toString();
                        final Double latitudePonto = (Double) objSnapshot.child("latitude").getValue();
                        final Double longitudePonto = (Double) objSnapshot.child("longitude").getValue();
                        PontosModel pontosModel = new PontosModel(enderecoPonto,latitudePonto,longitudePonto);

                        if (refss.get(finalI1) == refss.get(0)){
                            pontosModel.getPontosBlumenauIlhota().add(pontosModel);
                        }if (refss.get(finalI) == refss.get(1)){
                            pontosModel.getPontosIlhotaBlumenau().add(pontosModel);
                        }if (refss.get(finalI) == refss.get(2)){
                            pontosModel.getPontosBlumenauGaspar().add(pontosModel);
                        }if (refss.get(finalI) == refss.get(3)){
                            pontosModel.getPontosGasparBlumenau().add(pontosModel);
                        }
                    }
                    Log.d("tag", String.valueOf(new PontosModel().getPontosBlumenauGaspar().size()));
                    Log.d("tag", String.valueOf(new PontosModel().getPontosBlumenauIlhota().size()));
                    Log.d("tag", String.valueOf(new PontosModel().getPontosGasparBlumenau().size()));
                    Log.d("tag", String.valueOf(new PontosModel().getPontosIlhotaBlumenau().size()));
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("TAG", "Failed to read value.", databaseError.toException());
                }

            });
        }

    }
}
