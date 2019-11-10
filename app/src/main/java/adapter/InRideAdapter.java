package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.lib.drawroutemap.DrawMarker;
import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wolfsoft.kcab.Home_Travel;
import com.wolfsoft.kcab.R;

import java.util.ArrayList;

import model.InRideModel;
import model.RidehistoryModel;

public class InRideAdapter extends RecyclerView.Adapter<InRideAdapter.ViewHolder> {

    Context context;

    public InRideAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_in__ride_icab,parent,false);
        return new InRideAdapter.ViewHolder(view);
    }


    public ArrayList<InRideModel> inRideModelArrayList;

    public InRideAdapter(Context context, ArrayList<InRideModel> inRideModelArrayList) {
        this.context = context;
        this.inRideModelArrayList = inRideModelArrayList;
    }


    @Override
    public void onBindViewHolder(@NonNull InRideAdapter.ViewHolder holder, int position) {
        holder.duration.setText(inRideModelArrayList.get(position).getDuration());
        holder.time.setText(inRideModelArrayList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return inRideModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView duration, time;

        public ViewHolder(View itemView) {
            super(itemView);
            duration = itemView.findViewById(R.id.duration);
            time = itemView.findViewById(R.id.time);
        }
    }

}
