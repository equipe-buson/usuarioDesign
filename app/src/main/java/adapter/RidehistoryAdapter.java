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
        holder.i2.setImageResource(ridehistoryModelArrayList.get(position).getI2());
        holder.i3.setImageResource(ridehistoryModelArrayList.get(position).getI3());
        holder.txtmall.setText(ridehistoryModelArrayList.get(position).getTxtmall());
        holder.txthome.setText(ridehistoryModelArrayList.get(position).getTxthome());
        holder.txtdate.setText(ridehistoryModelArrayList.get(position).getTxtdate());
        holder.txtprice.setText(ridehistoryModelArrayList.get(position).getTxtprice());
    }


    @Override
    public int getItemCount() {
        return ridehistoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView i1,i2,i3;
        TextView txtmall,txthome,txtdate,txtprice;

        public ViewHolder(View itemView) {
            super(itemView);

            i1=itemView.findViewById(R.id.i1);
            i2=itemView.findViewById(R.id.i2);
            i3=itemView.findViewById(R.id.i3);
            txtmall=itemView.findViewById(R.id.txtmall);
            txthome=itemView.findViewById(R.id.txthome);
            txtdate=itemView.findViewById(R.id.txtdate);
            txtprice=itemView.findViewById(R.id.txtprice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posicao = getAdapterPosition();
                    rota = Integer.parseInt(ridehistoryModelArrayList.get(getAdapterPosition()).getTxthome());
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

