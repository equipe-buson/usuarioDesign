package com.wolfsoft.kcab;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DadosFireBase {
    DatabaseReference refChild;
    DatabaseReference mRef;


    public void inicializarFireBase(Context context, String ref) {
        FirebaseApp.initializeApp(context);
        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginfire-23a07.firebaseio.com/");

    }

//    public void obtemPosicaoPontos(final ArrayList<Pontos> arrayPontos, String ref) {
//        refChild = mRef.child(ref);
//        refChild.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
//                    final String enderecoPonto = objSnapshot.child("endereco").getValue().toString();
//                    final Double latitudePonto = (Double) objSnapshot.child("latitude").getValue();
//                    final Double longitudePonto = (Double) objSnapshot.child("longitude").getValue();
//                    Pontos pontos = new Pontos(enderecoPonto,latitudePonto,longitudePonto);
//                    arrayPontos.add(pontos);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
