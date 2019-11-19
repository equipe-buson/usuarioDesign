package adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfsoft.kcab.Home_Travel;
import com.wolfsoft.kcab.R;

import java.util.ArrayList;

import model.RidehistoryModel;

/**
 * Created by wolfsoft4 on 15/9/18.
 */

public class RidehistoryAdapter extends RecyclerView.Adapter<RidehistoryAdapter.ViewHolder> {
    Context context;

    private int posicao;
    public static int rota;

    public int getPosicao() {
        return posicao;
    }


    public ArrayList<RidehistoryModel> getRidehistoryModelArrayList() {
        return ridehistoryModelArrayList;
    }

    public ArrayList<RidehistoryModel> ridehistoryModelArrayList;
    private AdapterView.OnItemClickListener listener;
    public RidehistoryAdapter(){
    }


    public RidehistoryAdapter(Context context, ArrayList<RidehistoryModel> ridehistoryModelArrayList, AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.ridehistoryModelArrayList = ridehistoryModelArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RidehistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ride_history_icab,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RidehistoryAdapter.ViewHolder holder, int position) {
        holder.i1.setImageResource(ridehistoryModelArrayList.get(position).getI1());
        holder.txtlinha.setText(ridehistoryModelArrayList.get(position).getTxtlinha());
    }


    @Override
    public int getItemCount() {
        return ridehistoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView i1;
        TextView txtlinha;

        public ViewHolder(View itemView) {
            super(itemView);

            i1=itemView.findViewById(R.id.i1);
            txtlinha=itemView.findViewById(R.id.txtlinha);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rota = Integer.parseInt(ridehistoryModelArrayList.get(getAdapterPosition()).getTxtvalor());
                    Log.d("TAG","Click na adapter:" + rota);
                    Intent intent = new Intent(context, Home_Travel.class);
                    context.startActivity(intent);

                }
            });
        }
    }
    public static int getRota() {
        return rota;
    }
}

